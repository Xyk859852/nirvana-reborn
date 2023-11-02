package com.phoenix.nirvana.order.service.generator.impl;

import com.phoenix.nirvana.order.dal.mysql.dataobject.OrderAutoNoDO;
import com.phoenix.nirvana.order.dal.mysql.mapper.order.OrderAutoNoMapper;
import com.phoenix.nirvana.order.service.generator.SegmentIDGen;
import com.phoenix.nirvana.order.service.generator.core.Segment;
import com.phoenix.nirvana.order.service.generator.core.SegmentBuffer;
import com.phoenix.nirvana.order.service.generator.core.SegmentIDCache;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.concurrent.*;

/**
 * 号段ID生成器组件
 *
 * @author xuyongkang
 * @version 1.0
 */
@Slf4j
@Service
public class SegmentIDGenImpl implements SegmentIDGen {

    /**
     * 最大步长不超过100,0000
     */
    private static final int MAX_STEP = 1000000;

    /**
     * 默认一个Segment会维持的时间为15分钟
     * <p>
     * 如果在15分钟内Segment就消耗完了，则步长要扩容一倍，但不能超过MAX_STEP
     * 如果在超过15*2=30分钟才将Segment消耗完，则步长要缩容一倍，但不能低于MIN_STEP，MIN_STEP的值为数据库中初始的step字段值
     */
    private static final long SEGMENT_DURATION = 15 * 60 * 1000L;

    /**
     * 更新因子
     * <p>
     * 更新因子=2时，表示成倍扩容或者折半缩容
     */
    private static final int EXPAND_FACTOR = 2;

    /**
     * 下一次异步更新比率因子
     */
    public static final double NEXT_INIT_FACTOR = 0.9;

    private final ExecutorService threadPoolExecutor = new ThreadPoolExecutor(1, Integer.MAX_VALUE,
            60L, TimeUnit.SECONDS, new SynchronousQueue<>(), new UpdateThreadFactory());

    @Autowired
    private OrderAutoNoMapper orderAutoNoDAO;

    @Resource
    private SegmentIDCache cache;

    /**
     * 1. 如果没有初始化，报错
     * 2. 如果不包含业务code，报错
     * 3. 获取已缓存的segmentBuffer
     * 4. 如果segmentBuffer没有初始化，则初始化：
     * 4.1 double lock
     * 4.2 调用更新方法，并设置初始化
     * 4.3 异常报错
     * 5. 从buffer中获取id
     * <p>
     * 生成新的ID
     *
     * @param bizTag 业务标识
     * @return 返回
     */
    @Override
    public Long genNewNo(String bizTag) {
        // 每个cache里，都包含了bizTag对应的segment双缓冲
        // 做一个检查，cache是否初始化 过了，这个cache是否包含了bizTag
        if (!cache.isInitOk()) {
            throw new RuntimeException("not init");
        }
        if (!cache.containsKey(bizTag)) {
            throw new RuntimeException("not contains key:" + bizTag);
        }

        // 获取这个业务标识，SegmentBuffer是一个双缓冲
        SegmentBuffer buffer = cache.getValue(bizTag);

        // 多个线程并发的积压在这里，都发现此时你的双缓冲的分段数据，max、step还没初始化
        // 积压在sync加锁这里，只有一个人可以进去，就会完成db里的分段数据，加载到双缓冲里来
        // 后续的线程加锁进来，双缓冲已经完成了分段数据初始化
        if (!buffer.isInitOk()) {
            synchronized (buffer) {
                if (!buffer.isInitOk()) {
                    try {
                        updateSegmentFromDb(bizTag, buffer.getCurrent());
                        log.info("Init buffer. Update leafkey {} {} from db", bizTag, buffer.getCurrent());
                        buffer.setInitOk(true);
                    } catch (Exception e) {
                        log.warn("Init buffer {} exception", buffer.getCurrent(), e);
                        throw new RuntimeException("init error:" + bizTag);
                    }
                }
            }
        }

        return getIdFromSegmentBuffer(buffer);
    }

    /**
     * 1. 加读锁
     * 2. 如果 nextSegment没有初始化，且当前segment使用超过10%，且没有线程在处理中，则进行异步更新初始化
     * 3(out). 获取最新id，如果小于maxid，则返回
     * 4. 解开读锁
     * 5. 等待线程处理(获取的value，大于max，则等待其他线程更新完毕。最多等待10ms)
     * 6. 开写锁。
     * 7(out). 再次获取最新id，如果长于max，返回
     * 8. 如果nextok，切换缓冲区（等待下次循环），否则抛异常
     * 9. 解开写锁。
     * <p>
     * 从segment缓冲中获取id
     *
     * @param buffer 缓存
     * @return 返回
     */
    private Long getIdFromSegmentBuffer(SegmentBuffer buffer) {
        // 必须要确保这次序列号要生成
        while (true) {
            // 会把本次序列号的生成，分成读写锁，先加读锁，读锁好处，可以多线程并发读
            buffer.rLock().lock();
            try {
                final Segment segment = buffer.getCurrent();

                // 第一次基于current segment来进行序号生成的时候
                // 去把next segment做一个段的申请分段和加载分段到缓存里来
                // idle = current.max - value = 当前可用的序号值范围
                // 可用序号值的范围 < 90%，我已经用了一些序号，第一次申请序号就做一个next segment的准备
                // 10000 - 5，此时idle比例太高了，99%，除非申请了一些序号了，1200个序号，8800 < 90%比例，此时才能触发next segment初始化
                if (!buffer.isNextReady() && (segment.getIdle() < NEXT_INIT_FACTOR * segment.getStep())
                        && buffer.getThreadRunning().compareAndSet(false, true)) {
                    // 此时只有一个线程会进入到这里，来触发next segment初始化操作
                    // cas操作，确保了只有一个线程是可以把false变为true，进入进来，发起异步更新操作
                    asyncUpdate(buffer); // 异步更新操作
                }

                // Atomic递增的操作，atomic也是属于多线程并发安全的操作
                // 0~10000，先get 0，再是increment累加到1
                long value = segment.getValue().getAndIncrement();
                if (value < segment.getMax()) {
                    return value;
                }
            } finally {
                buffer.rLock().unlock();
            }

            // 如果在这里，多个线程都可能会通过一个segment获取累加的id值，>=max
            // 我们必须要停顿一下，把我们当期那的segment和另外一个备用的segment，互换
            // 对新的segment，去库里申请一个新的分段这样子，然后继续用一个新的分段去生成序号

            //获取的value，大于max，则等待其他线程更新完毕。最多等待100s
            // 超高并发的保护机制，基于这样的一个机制，可以保护如果next segment还在异步更新中
            // 瞬间大量线程卡在这儿，必须等待next segment更新完毕了以后再去进行发segment切换，继续发号
            waitAndSleep(buffer);

            // 多个线程都会发现thread running是false的，只有一个线程可以加到写锁
            buffer.wLock().lock();
            try {
                // 做一个segment互换，基于新的segment去执行后续的序号申请
                final Segment segment = buffer.getCurrent(); // 此时此刻current肯定已经满了
                long value = segment.getValue().getAndIncrement();
                if (value < segment.getMax()) {
                    return value;
                }
                // buffer的next是否ready，此时此刻，不可能是nextReady是false
                // nextReady，就是备用的segment，此时必须要去申请一个新的分段以及加载分段数据到segment里来
                if (buffer.isNextReady()) {
                    buffer.switchPos();
                    buffer.setNextReady(false);
                } else {
                    log.error("Both two segments in {} are not ready!", buffer);
                    throw new RuntimeException("next not ready");
                }
            } finally {
                buffer.wLock().unlock();
            }
        }
    }

    /**
     * 异步更新初始化
     * 1 获取下一个segment.
     * 2 更新db.
     * 3 如果更新正常，开写锁，更新nextready、running等
     * 4 如果异常。设置runing
     *
     * @param buffer 缓存
     */
    private void asyncUpdate(SegmentBuffer buffer) {
        long submitTime = System.currentTimeMillis();
        threadPoolExecutor.execute(() -> {
            long executeTime = System.currentTimeMillis();
            Segment next = buffer.getSegments()[buffer.nextPos()]; // next segment
            boolean updateOk = false;
            try {
                updateSegmentFromDb(buffer.getBizTag(), next);
                updateOk = true;
            } catch (Exception e) {
                log.warn("{} updateSegmentFromDb exception", buffer.getBizTag(), e);
            } finally {
                long finishTime = System.currentTimeMillis();
                log.info("update segment {} from db {}。st:{}, et:{}, ft:{}", buffer.getBizTag(),
                        next, submitTime, executeTime, finishTime);
                if (updateOk) {
                    buffer.wLock().lock();
                    buffer.setNextReady(true);
                    buffer.getThreadRunning().set(false);
                    buffer.wLock().unlock();
                } else {
                    buffer.getThreadRunning().set(false);
                }
            }
        });
    }

    /**
     * 自旋10000次之后，睡眠10毫秒
     */
    private void waitAndSleep(SegmentBuffer buffer) {
        int roll = 0;
        while (buffer.getThreadRunning().get()) {
            roll += 1;
            if (roll > 10000) {
                try {
                    TimeUnit.MILLISECONDS.sleep(10);
                    break;
                } catch (InterruptedException e) {
                    log.warn("Thread {} Interrupted", Thread.currentThread().getName());
                    break;
                }
            }
        }
    }

    /**
     * 1. 如果没有init
     * 1.1 更新maxId，并获取最新no
     * 1.2 将Step、MinStep 设置成db中的step
     * 2. 如果更新时间为0
     * 2.1 更新maxId，并获取最新no
     * 2.2 更新update时间
     * 2.3 将Step、MinStep 设置成db中的step
     * 3. 其他
     * 3.1 动态计算步长
     * 3.2 更新db步长等
     * 3.3 更新buffer的更新时间、步长、min步长
     * 4. 更新segment信息
     * 5. 统计数据
     * <p>
     * 从db中更新号段
     *
     * @param bizTag  业务标识
     * @param segment 段
     */
    public void updateSegmentFromDb(String bizTag, Segment segment) {
        // next segment，他之前已经初始化过一次了

        SegmentBuffer buffer = segment.getBuffer();
        OrderAutoNoDO orderAutoNoDO;
        if (!buffer.isInitOk()) {
            orderAutoNoDO = orderAutoNoDAO.updateMaxIdAndGet(bizTag);
            buffer.setStep(orderAutoNoDO.getStep());
            buffer.setMinStep(orderAutoNoDO.getStep());
        } else if (buffer.getUpdateTimestamp() == 0) {
            // 如果已经初始化过了，但是第一次初始化，更新时间戳是0，此时会在这里做一次申请segment
            // 设置一下他的时间戳是当前戳
            orderAutoNoDO = orderAutoNoDAO.updateMaxIdAndGet(bizTag);
            buffer.setUpdateTimestamp(System.currentTimeMillis());
            buffer.setStep(orderAutoNoDO.getStep());
            buffer.setMinStep(orderAutoNoDO.getStep());
        } else {
            // 当你初始化过一次，也更新过一次之后
            int nextStep = calculateNextStep(bizTag, buffer);
            orderAutoNoDO = orderAutoNoDAO.updateMaxIdByDynamicStepAndGet(bizTag, nextStep);
            buffer.setUpdateTimestamp(System.currentTimeMillis());
            buffer.setStep(nextStep); // 动态算出来的
            buffer.setMinStep(orderAutoNoDO.getStep()); // min step=数据库里查出来的默认step
        }
        // must set value before set max
        // max=10000，step=10000，段起始值是max-step=0
        long value = orderAutoNoDO.getMaxId() - buffer.getStep();
        segment.getValue().set(value); // value就是当前的分段里，他的起始序列号
        segment.setMax(orderAutoNoDO.getMaxId()); // 分段里最大的序号
        segment.setStep(buffer.getStep()); // 分段步长
        log.info("updateSegmentFromDb, bizTag: {}, cost:0, segment:{}", bizTag, segment);
    }

    /**
     * 动态步长算法，成倍扩容，折半缩容：
     * 1 如果更新间隔小于间隔阈值
     * 1.1 如果 *2 大于 阈值，不处理。不小于，则step翻倍。
     * 2 如果更新时间 小于 间隔时间*2，不处理。
     * 3 如果更新时间 大于 间隔时间*2：
     * 3.1 如果step折半小于miniStep，则折半；
     * <p>
     * 计算新的步长
     *
     * @param bizCode 业务code
     * @param buffer  缓存
     * @return 返回
     */
    private int calculateNextStep(String bizCode, SegmentBuffer buffer) {
        // 频繁切换segment之后，segment的step会自动的去进行调整
        // 这个是什么意思，如果说你step过小的话，segment里的序号在高并发之下，频繁的被用光
        // 导致segment频繁的被切换，动态的根据每次segment切换的时间自适应的调整step步长，step
        // 你后续切换segment的时候，可以间隔时间长一些，segment他的序号很快就被用光了

        // 这一次更新，距离上一次更新的时间，差距，duration
        long duration = System.currentTimeMillis() - buffer.getUpdateTimestamp();
        int nextStep = buffer.getStep(); // 拿出来当前的step，作为next step

        // 这一次距离上一次的更新时间差距 < 15分钟（阈值）
        // 如果发现两次segment更新时间，太快了，都小于15分钟了，更新太频繁，步长自适应调整
        if (duration < SEGMENT_DURATION) {
            nextStep = Math.min(MAX_STEP, nextStep * EXPAND_FACTOR); // 10000 -> 20000
        } else if (duration < SEGMENT_DURATION * EXPAND_FACTOR) {
            // 会尽可能的让你的发号时间间隔控制在15~30分钟这个范围里
            // do nothing with nextStep
        } else {
            // 如果说两次更新的时间间隔是大于了30分钟，15~30这个区间什么都没干
            // 如果说两次更新时间过长了，超过30分钟，步长太大了，发号发的很慢，此时会自动缩容，/2，10000 -> 5000
            nextStep = Math.max(buffer.getMinStep(), nextStep / EXPAND_FACTOR);
        }
        log.info("leafKey[{}], step[{}], duration[{}mins], nextStep[{}]", bizCode, buffer.getStep(),
                String.format("%.2f", ((double) duration / (1000 * 60))), nextStep);
        return nextStep;
    }

    public static class UpdateThreadFactory implements ThreadFactory {
        private static int threadInitNumber = 0;

        private static synchronized int nextThreadNum() {
            return threadInitNumber++;
        }

        @Override
        public Thread newThread(Runnable r) {
            return new Thread(r, "Thread-Segment-Update-" + nextThreadNum());
        }
    }
}
