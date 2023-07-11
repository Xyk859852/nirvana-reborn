package com.phoenix.nirvana.order.enums;

import lombok.Getter;

/**
 * 订单状态变化枚举
 */
@Getter
public enum OrderStatusChangeEnum {

    /**
     * 主单
     */

    // 订单已创建
    ORDER_CREATED(OrderStatusEnum.NULL, OrderStatusEnum.CREATED, OrderOperateTypeEnum.NEW_ORDER, "created"),

    // 订单预支付
    ORDER_PREPAY(OrderStatusEnum.CREATED, OrderStatusEnum.CREATED, OrderOperateTypeEnum.PRE_PAY_ORDER, false),

    // 订单已支付
    ORDER_PAID(OrderStatusEnum.CREATED, OrderStatusEnum.PAID, OrderOperateTypeEnum.PAID_ORDER, "paid"),

    // 订单已履约
    ORDER_FULFILLED(OrderStatusEnum.PAID, OrderStatusEnum.FULFILL, OrderOperateTypeEnum.PUSH_ORDER_FULFILL, "fulfill"),

    //订单已出库
    ORDER_OUT_STOCKED(OrderStatusEnum.FULFILL, OrderStatusEnum.OUT_STOCK, OrderOperateTypeEnum.ORDER_OUT_STOCK, "out_stock"),

    //订单已配送
    ORDER_DELIVERED(OrderStatusEnum.OUT_STOCK, OrderStatusEnum.DELIVERY, OrderOperateTypeEnum.ORDER_DELIVERED, "delivery"),

    //订单已签收
    ORDER_SIGNED(OrderStatusEnum.DELIVERY, OrderStatusEnum.SIGNED, OrderOperateTypeEnum.ORDER_SIGNED, "signed"),

    // 订单取消
    ORDER_CANCEL(OrderStatusEnum.CANCELLED, OrderStatusEnum.CANCELLED, OrderOperateTypeEnum.CANCEL_ORDER, false),

    //订单自动超时未支付订单取消
    ORDER_UN_PAID_AUTO_TIMEOUT_CANCELLED(OrderStatusEnum.CREATED, OrderStatusEnum.CANCELLED, OrderOperateTypeEnum.AUTO_CANCEL_ORDER, "auto_timeout_cancelled"),

    //订单未支付手动取消
    ORDER_UN_PAID_MANUAL_CANCELLED(OrderStatusEnum.CREATED, OrderStatusEnum.CANCELLED, OrderOperateTypeEnum.MANUAL_CANCEL_ORDER, "unpaid_manual_cancelled"),

    //订单已支付手动取消
    ORDER_PAID_MANUAL_CANCELLED(OrderStatusEnum.PAID, OrderStatusEnum.CANCELLED, OrderOperateTypeEnum.MANUAL_CANCEL_ORDER, "paid_manual_cancelled"),

    //订单已履约手动取消
    ORDER_FULFILLED_MANUAL_CANCELLED(OrderStatusEnum.FULFILL, OrderStatusEnum.CANCELLED, OrderOperateTypeEnum.MANUAL_CANCEL_ORDER, "fulfilled_manual_cancelled"),

    //主单完成支付已无效
    ORDER_PAID_INVALID(OrderStatusEnum.CREATED, OrderStatusEnum.INVALID, OrderOperateTypeEnum.ORDER_PAID_INVALID, "paid_invalid"),

    //虚拟订单已签收
    VIRTUAL_ORDER_SIGNED(OrderStatusEnum.PAID, OrderStatusEnum.SIGNED, OrderOperateTypeEnum.SIGN_VIRTUAL_ORDER, "virtual_order_signed"),

    /**
     * 子单
     */

    //子单已创建
    SUB_ORDER_CREATED(OrderStatusEnum.NULL, OrderStatusEnum.INVALID, OrderOperateTypeEnum.NEW_SUB_ORDER, "sub_created"),

    //子单已支付
    SUB_ORDER_PAID(OrderStatusEnum.INVALID, OrderStatusEnum.PAID, OrderOperateTypeEnum.PAID_SUB_ORDER, "sub_paid"),
    ;

    OrderStatusChangeEnum(OrderStatusEnum fromStatus, OrderStatusEnum toStatus, OrderOperateTypeEnum operateType, String tags) {
        this.fromStatus = fromStatus;
        this.toStatus = toStatus;
        this.operateType = operateType;
        this.tags = tags;
        this.sendEvent = true;
    }

    OrderStatusChangeEnum(OrderStatusEnum fromStatus, OrderStatusEnum toStatus, OrderOperateTypeEnum operateType, boolean sendEvent) {
        this.fromStatus = fromStatus;
        this.toStatus = toStatus;
        this.operateType = operateType;
        this.sendEvent = sendEvent;
    }


    private OrderStatusEnum fromStatus;
    private OrderStatusEnum toStatus;
    private OrderOperateTypeEnum operateType;
    private String tags;
    private boolean sendEvent;
}
