package com.phoenix.nirvana.order.service.generator.core;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.concurrent.atomic.AtomicLong;

/**
 * 号段
 *
 * @author xuyongkang
 * @version 1.0
 */
@Data
@Accessors(chain = true)
public class Segment {
    /**
     * 值
     */
    private AtomicLong value = new AtomicLong(0);
    /**
     * 最大
     */
    private volatile long max;
    /**
     * 步长
     */
    private volatile int step;
    /**
     * 号段缓存器
     */
    private SegmentBuffer buffer;

    public long getIdle() {
        return this.getMax() - getValue().get();
    }

    public Segment(SegmentBuffer buffer) {
        this.buffer = buffer;
    }

    @Override
    public String toString() {
        return "Segment{" +
                "value=" + value +
                ", max=" + max +
                ", step=" + step +
                '}';
    }
}
