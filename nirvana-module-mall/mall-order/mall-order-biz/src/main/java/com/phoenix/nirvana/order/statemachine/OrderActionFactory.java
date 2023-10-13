package com.phoenix.nirvana.order.statemachine;

import com.phoenix.nirvana.common.enums.OrderStatusChangeEnum;
import com.phoenix.nirvana.order.statemachine.order.create.OrderStateAction;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author zhonghuashishan
 * @version 1.0
 */
@Component
public class OrderActionFactory {

    @Resource
    private List<OrderStateAction<?>> actions;

    public OrderStateAction<?> getAction(OrderStatusChangeEnum event) {
        for (OrderStateAction<?> action : actions) {
            if (action.event() == null) {
                throw new IllegalArgumentException("event 返回值不能为空：" + action.getClass().getSimpleName());
            }
            if (action.event().equals(event)) {
                return action;
            }
        }
        return null;
    }
}
