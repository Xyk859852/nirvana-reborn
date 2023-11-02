package com.phoenix.nirvana.order.service.impl;

import com.phoenix.nirvana.core.DefaultProducer;
import com.phoenix.nirvana.order.convert.order.ProductOrderConvert;
import com.phoenix.nirvana.order.dal.mysql.dataobject.ProductOrderDO;
import com.phoenix.nirvana.order.dal.mysql.mapper.order.ProductOrderMapper;
import com.phoenix.nirvana.common.enums.OrderStatusChangeEnum;
import com.phoenix.nirvana.order.mq.message.OrderStdChangeMessage;
import com.phoenix.nirvana.order.service.RocketMqService;
import com.phoenix.nirvana.order.service.bo.SendOrderStdEventBO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.phoenix.nirvana.order.enums.RocketMqConstants.ORDER_STD_CHANGE_EVENT_TOPIC;

/**
 * Rocket 消息业务推送，搭配一致性框架使用
 *
 * @Author: xuyongkang
 * @Date 2023/7/11 16:03
 */
@Service
public class RocketMqServiceImpl implements RocketMqService {

    @Autowired
    DefaultProducer defaultProducer;

    @Autowired
    ProductOrderMapper orderMapper;

    @Override
    public void sendStandardOrderStatusChangeMessage(SendOrderStdEventBO sendOrderStdEventDTO) {
        OrderStatusChangeEnum event = sendOrderStdEventDTO.getOrderStatusChangeEnum();
        String orderNo = sendOrderStdEventDTO.getOrderNo();
        ProductOrderDO order = orderMapper.getByOrderNo(orderNo);
        OrderStdChangeMessage message = buildStdMessage(event, order);
        defaultProducer.send(ORDER_STD_CHANGE_EVENT_TOPIC, message, orderNo);
    }

    public OrderStdChangeMessage buildStdMessage(OrderStatusChangeEnum event, ProductOrderDO order) {
        OrderStdChangeMessage message = ProductOrderConvert.INSTANCE.convert(order);
        message.setStatusChange(event);
        return message;
    }

}
