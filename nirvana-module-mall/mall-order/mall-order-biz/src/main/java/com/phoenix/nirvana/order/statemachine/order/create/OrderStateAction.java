package com.phoenix.nirvana.order.statemachine.order.create;

import com.phoenix.nirvana.order.enums.OrderStatusChangeEnum;
import com.phoenix.nirvana.order.rpc.order.domain.vo.OrderInfoVO;
import com.phoenix.nirvana.order.service.mq.RocketMqService;
import com.phoenix.nirvana.order.service.mq.bo.SendOrderStdEventBO;
import com.phoenix.nirvana.stateaction.core.AbstractStateAction;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 订单状态流转
 *
 * @Author: xuyongkang
 * @Date 2023/7/11 10:58
 */
@Slf4j
public abstract class OrderStateAction<T> extends AbstractStateAction<T, OrderInfoVO, OrderStatusChangeEnum> {

    @Autowired
    RocketMqService rocketMqService;

    @Override
    protected void postStateChange(OrderStatusChangeEnum event, OrderInfoVO context) {
        if (context == null) {
            return;
        }
        if (event.isSendEvent()) {
            rocketMqService.sendStandardOrderStatusChangeMessage(new SendOrderStdEventBO());
        }
    }
}
