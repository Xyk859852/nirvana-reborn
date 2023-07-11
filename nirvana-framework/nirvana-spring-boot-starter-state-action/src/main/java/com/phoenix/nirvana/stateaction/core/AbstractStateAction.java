package com.phoenix.nirvana.stateaction.core;

/**
 * T 表示入参，R表示出参
 *
 * @author xuyongkang
 * @version 1.0
 */
public abstract class AbstractStateAction<T, R, E> implements StateAction<E> {

    @Override
    @SuppressWarnings("unchecked")
    public void onStateChange(E event, Object context) {
        R r = onStateChangeInternal(event, (T) context);
        postStateChange(event, r);
    }

    /**
     * 状态变更后置操作
     *
     * @param event   事件
     * @param context 上下文
     */
    protected void postStateChange(E event, R context) {
        // 默认空实现，提供给子类一个钩子
    }

    /**
     * 状态变更操作
     *
     * @param event   事件
     * @param context 上下文
     * @return 返回标准的数据，正向是OrderInfoDTO, 逆向是AfterSaleStateMachineDTO
     */
    protected abstract R onStateChangeInternal(E event, T context);

}
