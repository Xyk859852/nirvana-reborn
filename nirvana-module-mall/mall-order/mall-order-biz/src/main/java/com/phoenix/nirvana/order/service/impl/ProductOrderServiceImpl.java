package com.phoenix.nirvana.order.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.phoenix.nirvana.common.enums.OrderStatusChangeEnum;
import com.phoenix.nirvana.common.enums.OrderStatusEnum;
import com.phoenix.nirvana.order.dal.mysql.dataobject.ProductOrderDO;
import com.phoenix.nirvana.order.dal.mysql.mapper.order.ProductOrderMapper;
import com.phoenix.nirvana.order.rpc.domain.dto.CreateOrderDTO;
import com.phoenix.nirvana.order.rpc.domain.dto.GenOrderIdDTO;
import com.phoenix.nirvana.order.rpc.domain.vo.GenOrderIdVO;
import com.phoenix.nirvana.order.rpc.domain.vo.OrderInfoVO;
import com.phoenix.nirvana.order.service.ProductOrderService;
import com.phoenix.nirvana.order.service.generator.GenOrderNoService;
import com.phoenix.nirvana.order.statemachine.StateMachineFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 商品订单表 服务实现类
 * </p>
 *
 * @author xuyongkang
 * @since 2023-06-29
 */
@Slf4j
@Service
public class ProductOrderServiceImpl extends ServiceImpl<ProductOrderMapper, ProductOrderDO> implements ProductOrderService {

    @Autowired
    private GenOrderNoService genOrderNoService;

    // 状态机工厂，是组件，专门是用来创建状态机的
    @Autowired
    private StateMachineFactory stateMachineFactory;

    @Override
    public GenOrderIdVO genOrderId(GenOrderIdDTO genOrderIdDTO) {
        return new GenOrderIdVO().setOrderNo(genOrderNoService.genOrderNo(genOrderIdDTO.getBusinessIdentifier(), genOrderIdDTO.getUserId()));
    }


    @Override
    public OrderInfoVO createOrder(CreateOrderDTO createOrder) {
        log.info("开始下单：{}", createOrder);
        // 状态机流转
        // 分析一下这块的代码，StateMachineFactory，状态机工厂，工厂设计模式，来创建出来订单状态机
        // 刚开始初始化订单状态机的时候，订单初始的状态state，是null
        // 通过状态机fire触发了一个状态流转，把订单状态从null，流转到了created状态，基于状态机触发了状态的流转，把request对象作为数据传递
        // 状态机进行了状态流转的时候，必然会触发说你的状态流转到created以后，要执行一个action动作，每个state -> action，猜测一下
        // 如果执行了created action，此时就必然会导致生单业务流程编排和触发
        // 最终一致性框架，业务流程编排框架，我们之前用了2周时间，已经分析的极为的透彻了
        // 研究一下，状态机到底是如何来实现的，状态机我们没有自研，而是用的是开源的框架，

        // 基于squirrel框架，直接就可以拿到订单状态机，发生什么事件，会导致状态如何流转，调用他的什么方法
        // 全部都定义好了
        StateMachineFactory.OrderStateMachine orderStateMachine = stateMachineFactory.getOrderStateMachine(OrderStatusEnum.NULL);
        // 我们还把创建订单request对象传递进来了，这个对象，是需要后面来进行使用的
        // 状态机，初始的状态是null，创建事件，触发了以后，会从null -> created，流转之后，会在这里触发状态机里的方法的执行
        orderStateMachine.fire(OrderStatusChangeEnum.ORDER_CREATED, createOrder);

        // 基于状态机触发主单创建事件，触发null->created流转，触发created action，触发生单业务流程编排
        // 每次生单完成了，post里，把这个订单状态变更，推送到消息总线里去，mq把你的所有状态变更，都推送过去这样子
        // created action，post逻辑，判断一下是否要进行拆单，对每个子单跑一下子单生单业务流程编排
        return new OrderInfoVO().setOrderNo(createOrder.getOrderNo());
    }
}
