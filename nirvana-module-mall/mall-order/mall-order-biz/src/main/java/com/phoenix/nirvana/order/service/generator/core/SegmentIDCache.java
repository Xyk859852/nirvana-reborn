package com.phoenix.nirvana.order.service.generator.core;

import com.phoenix.nirvana.order.dal.mysql.mapper.order.OrderAutoNoMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Slf4j
@Component
public class SegmentIDCache implements ApplicationListener<ContextRefreshedEvent> {

    @Resource
    private OrderAutoNoMapper orderAutoNoMapper;

    // 多线程写和读的可见性，用了volatile来保证这个东西
    private volatile boolean initOk = false;

    // 每个业务标识都有一个双缓冲
    private final Map<String, SegmentBuffer> cache = new ConcurrentHashMap<>();

    // spring容器回调这个方法
    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        checkAndInit();
    }

    /**
     * 初始化数据
     * 1. 初始化cache
     */
    private void checkAndInit() {
        if (!initOk) {
            synchronized (this) {
                if (!initOk) {
                    log.info("Init ...");
                    // 确保加载到kv后才初始化成功
                    updateCacheFromDb(); // 从数据库里加载序列号到缓存里，才算初始化成功了
                    initOk = true;
                    log.info("Init Ok ...");
                }
            }
        }
    }

    public boolean isInitOk() {
        return initOk;
    }

    public boolean containsKey(String bizCode) {
        checkAndInit();
        return cache.containsKey(bizCode);
    }

    public SegmentBuffer getValue(String bizCode) {
        checkAndInit();
        return cache.get(bizCode);
    }

    /**
     * 更新缓存key
     * 1. 获取所有biz
     * 2. 维护cache的增加，初始化segment
     * 3. 维护cache的删除。
     */
    private void updateCacheFromDb() {
        log.info("update cache from db");
        try {
            List<String> dbBizCodes = orderAutoNoMapper.listAllBizTag();
            if (CollectionUtils.isEmpty(dbBizCodes)) {
                return;
            }
            List<String> cacheBiz = new ArrayList<>(cache.keySet());
            Set<String> insertBizSet = new HashSet<>(dbBizCodes);
            Set<String> removeBizSet = new HashSet<>(cacheBiz);
            //db中新加的tags灌进cache
            for (String tmp : cacheBiz) {
                insertBizSet.remove(tmp);
            }
            for (String bizCode : insertBizSet) {
                SegmentBuffer buffer = new SegmentBuffer();
                buffer.setBizTag(bizCode);
                Segment segment = buffer.getCurrent();
                segment.setValue(new AtomicLong(0));
                segment.setMax(0);
                segment.setStep(0);
                cache.put(bizCode, buffer);
                log.info("Add bizCode {} from db to IdCache, SegmentBuffer {}", bizCode, buffer);
            }

            // 把db里有的业务标识，从原来的cache里的业务标识里删除
            // 剩下的，removeBizSet里剩下的，就是db里没有的业务标识
            for (String tmp : dbBizCodes) {
                removeBizSet.remove(tmp);
            }
            for (String tag : removeBizSet) {
                cache.remove(tag);
                log.info("Remove tag {} from IdCache", tag);
            }
        } catch (Exception e) {
            log.warn("update cache from db exception", e);
        } finally {
            log.info("updateCacheFromDb,cost:{}", 0);
        }
    }
}
