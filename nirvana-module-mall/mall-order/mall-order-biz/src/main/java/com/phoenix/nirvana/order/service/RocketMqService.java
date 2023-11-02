package com.phoenix.nirvana.order.service;

import com.phoenix.nirvana.order.service.bo.SendOrderStdEventBO;

/**
 * RocketMq发送消息服务
 *
 * @Author: xuyongkang
 * @Date 2023/7/11 16:02
 */
public interface RocketMqService {

    /**
     * 发送订单标准状态变更消息
     *
     * @param sendOrderStdEventDTO 发送订单标准变更消息请求
     */
    void sendStandardOrderStatusChangeMessage(SendOrderStdEventBO sendOrderStdEventDTO);


}
