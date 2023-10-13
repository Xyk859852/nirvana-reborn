package com.phoenix.nirvana.order.statemachine.order.create;

import com.phoenix.nirvana.common.enums.OrderStatusChangeEnum;
import com.phoenix.nirvana.order.rpc.domain.vo.OrderInfoVO;
import com.phoenix.nirvana.order.service.RocketMqService;
import com.phoenix.nirvana.order.service.bo.SendOrderStdEventBO;
import com.phoenix.nirvana.stateaction.core.AbstractStateAction;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import static com.phoenix.nirvana.common.enums.OrderStatusChangeEnum.ORDER_CREATED;

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
            rocketMqService.sendStandardOrderStatusChangeMessage(new SendOrderStdEventBO()
                    .setOrderNo(context.getOrderNo())
                    .setOrderStatusChangeEnum(ORDER_CREATED)
            );
        }
    }
}
