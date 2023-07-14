package com.phoenix.nirvana.order.service.generator.core;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 号段内存缓冲组件
 *
 * @author xuyongkang
 * @version 1.0
 */
@Data
@Accessors(chain = true)
public class SegmentBuffer {

    private String bizTag;

    /**
     * 双buffer
     */
    private Segment[] segments;

    /**
     * 当前的使用的segment的index
     */
    private volatile int currentPos;

    /**
     * 下一个segment是否处于可切换状态
     */
    private volatile boolean nextReady;

    /**
     * 是否初始化完成
     */
    private volatile boolean initOk;

    /**
     * 线程是否在运行中
     */
    private final AtomicBoolean threadRunning;

    private final ReadWriteLock lock;

    private volatile int step;
    private volatile int minStep;
    private volatile long updateTimestamp;

    public Segment getCurrent() {
        return segments[currentPos];
    }

    public Lock rLock() {
        return lock.readLock();
    }

    public Lock wLock() {
        return lock.writeLock();
    }

    public int nextPos() {
        return (currentPos + 1) % 2;
    }

    public void switchPos() {
        currentPos = nextPos();
    }

    public SegmentBuffer() {
        // 双缓冲机制，segment buffer里，持有两个segment，双缓冲机制，可以互相患者使用的
        segments = new Segment[]{new Segment(this), new Segment(this)};
        currentPos = 0;
        nextReady = false;
        initOk = false; // 业务标识，双缓冲里面的核心分段数据，是懒加载的，不会说一开始就从db里加载
        threadRunning = new AtomicBoolean(false);
        lock = new ReentrantReadWriteLock();
    }

    @Override
    public String toString() {
        return "SegmentBuffer{" +
                "bizCode='" + bizTag + '\'' +
                ", segments=" + Arrays.toString(segments) +
                ", currentPos=" + currentPos +
                ", nextReady=" + nextReady +
                ", initOk=" + initOk +
                ", threadRunning=" + threadRunning +
                ", lock=" + lock +
                ", step=" + step +
                ", minStep=" + minStep +
                ", updateTimestamp=" + updateTimestamp +
                '}';
    }
}
