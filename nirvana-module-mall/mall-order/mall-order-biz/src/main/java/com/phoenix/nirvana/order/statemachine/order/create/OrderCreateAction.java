package com.phoenix.nirvana.order.statemachine.order.create;

import com.phoenix.nirvana.order.enums.OrderStatusChangeEnum;
import com.phoenix.nirvana.order.rpc.order.domain.dto.CreateOrderDTO;
import com.phoenix.nirvana.order.rpc.order.domain.vo.OrderInfoVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * 订单创建流程
 *
 * @Author: xuyongkang
 * @Date 2023/7/11 17:21
 */
@Slf4j
@Component
public class OrderCreateAction extends OrderStateAction<CreateOrderDTO> {

    @Override
    protected OrderInfoVO onStateChangeInternal(OrderStatusChangeEnum event, CreateOrderDTO context) {
        return null;
    }

    @Override
    public OrderStatusChangeEnum event() {
        return OrderStatusChangeEnum.ORDER_CREATED;
    }
}
