package com.phoenix.nirvana.order.statemachine;

import com.phoenix.nirvana.common.enums.OrderStatusChangeEnum;
import com.phoenix.nirvana.common.enums.OrderStatusEnum;
import com.phoenix.nirvana.common.exception.ServiceException;
import com.phoenix.nirvana.order.statemachine.order.create.OrderStateAction;
import org.springframework.stereotype.Component;
import org.squirrelframework.foundation.fsm.StateMachineBuilderFactory;
import org.squirrelframework.foundation.fsm.UntypedStateMachineBuilder;
import org.squirrelframework.foundation.fsm.annotation.StateMachineParameters;
import org.squirrelframework.foundation.fsm.impl.AbstractUntypedStateMachine;

import javax.annotation.Resource;

/**
 * 状态机工厂
 *
 * @author zhonghuashishan
 * @version 1.0
 */
@Component
public class StateMachineFactory {

    private final UntypedStateMachineBuilder orderStateMachineBuilder;
//    private final UntypedStateMachineBuilder afterSaleStateMachineBuilder;

    @Resource
    private OrderActionFactory orderActionFactory;
//    @Resource
//    private AfterSaleActionFactory afterSaleActionFactory;

    // 初始化的逻辑
    private StateMachineFactory() {
        // StateMachineBuilderFactory，他是squirrel框架的类
        // 基于我们自定义的订单状态机的class，构建了一个订单状态机的builder
        // 状态机Builder工厂 -> 创建出来订单状态机Builder
        this.orderStateMachineBuilder = StateMachineBuilderFactory.create(OrderStateMachine.class);

        // 针对这个builder构建了一个他的状态流转的链条
        // 在进行状态流转的时候，每次状态流转，会触发什么东西，由谁来进行状态流转的处理
        // 我们必须在这里，去构建订单状态流转的链条，会从什么状态流转到什么状态，流转的过程中，会触发什么事件
        // 触发了事件以后由谁来进行处理
        // OrderStatusChangeEnum包含了订单状态流转所有的事件，每个事件都会导致订单状态流转
        for (OrderStatusChangeEnum event : OrderStatusChangeEnum.values()) {
            // 如果说发生了order_created事件，会导致状态流转，from null状态流转到created状态
            // 状态机，他的状态流转，是由一个一个事件去触发的，触发订单这里一个一个事件的时候，会导致订单状态机里的状态会发生一次流转
            // 发生流转了之后，call method，调用一个什么方法，状态机里自定义的一个方法，通过自定义方法去处理状态流转
            this.orderStateMachineBuilder.externalTransition().from(event.getFromStatus())
                    .to(event.getToStatus()).on(event).callMethod("onStateChange");
        }

//        // 是在初始化和构建订单逆向状态流转的状态机
//        this.afterSaleStateMachineBuilder = StateMachineBuilderFactory.create(AfterSaleStateMachine.class);
//        for (AfterSaleStateMachineChangeEnum event : AfterSaleStateMachineChangeEnum.values()) {
//            this.afterSaleStateMachineBuilder.externalTransition().from(event.getFromStatus())
//                    .to(event.getToStatus()).on(event).callMethod("onStateChange");
//        }
    }

    /**
     * 获取订单状态机
     *
     * @param initState 初始状态
     * @return 状态机
     */
    public OrderStateMachine getOrderStateMachine(OrderStatusEnum initState) {
        OrderStateMachine orderStateMachine = this.orderStateMachineBuilder.newUntypedStateMachine(initState);
        orderStateMachine.setOrderActionFactory(orderActionFactory);
        return orderStateMachine;
    }

    /**
     * 获取售后状态机
     *
     * @param initState 初始状态
     * @return 状态机
     */
//    public AfterSaleStateMachine getAfterSaleStateMachine(AfterSaleStatusEnum initState) {
//        AfterSaleStateMachine afterSaleStateMachine = this.afterSaleStateMachineBuilder.newUntypedStateMachine(initState);
//        afterSaleStateMachine.setOrderActionFactory(afterSaleActionFactory);
//        return afterSaleStateMachine;
//    }

    /**
     * 状态机父类
     * 先继承自我们自定义的基类，里面包含一些基础性的通用型的功能和方法
     * 让他继续继承自squirrel框架提供的基类
     */
    public static abstract class BaseStateMachine<S, E> extends AbstractUntypedStateMachine {
        private final ThreadLocal<Exception> exceptionThreadLocal = new ThreadLocal<>();

        /**
         * 状态流传
         */
        public void onStateChange(S fromStatus, S toState, E event, Object context) {
            try {
                // 状态机已经触发了一个事件，触发了以后，导致状态，state，从from里转到to去
                // 发生了什么事件，event，from，to，传递进来的订单创建request对象会作为context传递进来
                onStateChangeInternal(fromStatus, toState, event, context);
            } catch (Exception e) {
                exceptionThreadLocal.set(e);
            }
        }

        /**
         * 正常情况下状态机：调用fire(event,context) 方法，会调用onStateChange方法。
         * <p>
         * 假如onStateChange方法抛出业务异常，这里会被状态机接管，然后使用一个Squirrel-Foundation内部的异常
         * TransitionException对我们的业务异常进行包装。然后抛出TransitionException异常。
         * <p>
         * 我们一般的情景：在SpringBoot中调用状态机开始状态流转，调用了fire方法，接着得到一个TransitionException异常，
         * 显然不是我们想要的结果。我们希望onStateChange方法抛出的如果是业务异常BaseBizException，则fire方法抛出的也是业务异常。
         * <p>
         * 所以这里采用了一种方式，在onStateChange方法中使用ThreadLocal将状态保存起来，
         * 那么fire方法就无法检测到我们实际业务代码是否抛出了异常，此时等fire方法返回的时候，我们再判断ThreadLocal中是否有异常，
         * 如果有就直接抛出，这样就可以实现我们所需要的结果。
         */
        @Override
        public void fire(Object event, Object context) {
            super.fire(event, context); // 调用父类的fire，很明显干什么，触发一个事件
            // 就会从这个事件指定的null -> created，触发onStateChange方法
            Exception exception = exceptionThreadLocal.get();
            if (exception != null) {
                exceptionThreadLocal.remove();
                if (exception instanceof ServiceException) {
                    throw (ServiceException) exception;
                } else {
                    throw new RuntimeException(exception);
                }
            }
        }

        /**
         * 状态机装填流转核心逻辑
         */
        protected abstract void onStateChangeInternal(S fromStatus, S toState, E event, Object context);

    }

    /**
     * 订单状态机
     * 这个注解，@StateMachineParameters，是squirrel框架提供的，描述订单状态机的
     * 用来对你的状态机进行一些基础性的定义，状态流转，状态是什么类型，字符串、integer、自定义对象、枚举？
     * 定义一个自己的状态机的，必须定义你的状态机里，他的状态是什么类型的，OrderStatusEnum
     * 在你的状态再进行流转的时候，会发生状态流转的事件，他是什么类型的，用的都是我们自己定义的OrderStatusChange事件枚举类型
     * contextType，在状态流转的时候，必然会有一个上下文的概念流转的时候传递数据，上下文提供数据
     * <p>
     * 自定义状态机必须继承squirrel框架的基类
     */
    @StateMachineParameters(stateType = OrderStatusEnum.class, eventType = OrderStatusChangeEnum.class, contextType = Object.class)
    public static class OrderStateMachine extends BaseStateMachine<OrderStatusEnum, OrderStatusChangeEnum> {

        // 一看是什么东西，状态在流转的时候，流转到不同的状态的时候，就需要不同的action来触发
        // 比如说流转到created状态，created action来执行你的状态动作，生单业务流程编排
        private OrderActionFactory orderActionFactory;

        public void setOrderActionFactory(OrderActionFactory orderActionFactory) {
            this.orderActionFactory = orderActionFactory;
        }

        @Override
        public void onStateChange(OrderStatusEnum fromStatus, OrderStatusEnum toState, OrderStatusChangeEnum event, Object context) {
            super.onStateChange(fromStatus, toState, event, context);
        }

        @Override
        public void onStateChangeInternal(OrderStatusEnum fromStatus, OrderStatusEnum toState, OrderStatusChangeEnum event, Object context) {
            // 真正去触发我们的订单创建事件，状态流转，处理动作的时候，会到这儿来
            // 触发了状态流转之后，会找到一个动作，action，action的话是从OrderAtionFactory里获取出来的
            OrderStateAction<?> action = orderActionFactory.getAction(event);
            if (action != null) {
                action.onStateChange(event, context);
            }
        }
    }

    /**
     * 售后状态机
     */
//    @StateMachineParameters(stateType = AfterSaleStatusEnum.class, eventType = AfterSaleStateMachineChangeEnum.class, contextType = Object.class)
//    public static class AfterSaleStateMachine extends BaseStateMachine<AfterSaleStatusEnum, AfterSaleStateMachineChangeEnum> {
//        private AfterSaleActionFactory afterSaleActionFactory;
//
//        public void setOrderActionFactory(AfterSaleActionFactory afterSaleActionFactory) {
//            this.afterSaleActionFactory = afterSaleActionFactory;
//        }
//
//        @Override
//        public void onStateChange(AfterSaleStatusEnum fromStatus, AfterSaleStatusEnum toState, AfterSaleStateMachineChangeEnum event, Object context) {
//            super.onStateChange(fromStatus, toState, event, context);
//        }
//
//        @Override
//        public void onStateChangeInternal(AfterSaleStatusEnum fromStatus, AfterSaleStatusEnum toState, AfterSaleStateMachineChangeEnum event, Object context) {
//            AfterSaleStateAction<?> action = afterSaleActionFactory.getAction(event);
//            if (action != null) {
//                action.onStateChange(event, context);
//            }
//        }
//    }
}
