package com.phoenix.nirvana.order.statemachine.order.create;

import com.phoenix.nirvana.common.enums.OrderStatusChangeEnum;
import com.phoenix.nirvana.order.rpc.domain.dto.CreateOrderDTO;
import com.phoenix.nirvana.order.rpc.domain.vo.OrderInfoVO;
import com.phoenix.nirvana.order.statemachine.StateMachineFactory;
import com.phoenix.nirvana.process.core.process.ProcessContext;
import com.phoenix.nirvana.process.engine.model.ProcessContextFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private ProcessContextFactory processContextFactory;

    @Autowired
    private StateMachineFactory stateMachineFactory;

    @Override
    protected OrderInfoVO onStateChangeInternal(OrderStatusChangeEnum event, CreateOrderDTO context) {
        // 获取流程引擎并执行
        // 又会在这里把这个request放到业务流程编排引擎context里去
        ProcessContext masterOrderCreateProcess = processContextFactory.getContext("masterOrderCreateProcess");
        masterOrderCreateProcess.set("createOrderDTO", context);
        masterOrderCreateProcess.start();
        return new OrderInfoVO().setOrderNo(context.getOrderNo());
    }

    @Override
    public OrderStatusChangeEnum event() {
        return OrderStatusChangeEnum.ORDER_CREATED;
    }
}
