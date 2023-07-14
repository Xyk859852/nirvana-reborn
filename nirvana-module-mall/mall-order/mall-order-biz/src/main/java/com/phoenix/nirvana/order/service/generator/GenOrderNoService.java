package com.phoenix.nirvana.order.service.generator;

/**
 * 订单号生成业务
 *
 * @Description:
 * @Author: xuyongkang
 * @Date 2023/7/6 14:45
 */
public interface GenOrderNoService {

    /**
     * 生成订单号
     *
     * @param orderNoType 订单号类型
     * @param userId      用户ID
     * @return 订单ID
     */
    String genOrderNo(Integer orderNoType, String userId);
}
