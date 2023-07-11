package com.phoenix.nirvana.order.service.generator;

/**
 * 号段ID生成器组件
 *
 * @author xuyongkang
 * @version 1.0
 */
public interface SegmentIDGen {

    /**
     * 生成新ID
     *
     * @param bizTag 业务标识
     * @return 返回
     */
    Long genNewNo(String bizTag);
}
