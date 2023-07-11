package com.phoenix.nirvana.order.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

/**
 * 订单操作类型枚举值
 *
 * @author zhonghuashishan
 * @version 1.0
 */
@Getter
@AllArgsConstructor
public enum OrderOperateTypeEnum {


    // 新增订单
    NEW_ORDER(10, "新建订单"),

    PRE_PAY_ORDER(11, "订单预支付"),

    // 取消订单
    CANCEL_ORDER(19, "取消订单"),

    // 手工取消订单
    MANUAL_CANCEL_ORDER(20, "手工取消订单"),

    // 超时未支付-自动取消订单
    AUTO_CANCEL_ORDER(30, "超时未支付自动取消订单"),

    // 完成订单支付
    PAID_ORDER(40, "完成订单支付"),

    // 推送订单至履约
    PUSH_ORDER_FULFILL(50, "推送订单至履约"),

    // 订单已出库
    ORDER_OUT_STOCK(60, "订单已出库"),

    ORDER_DELIVERED(70, "订单已配送"),

    ORDER_SIGNED(80, "订单已签收"),

    ORDER_REJECTED(90, "订单已拒收"),

    ORDER_PAID_INVALID(100, "主单完成支付无效"),

    // 新增子订单
    NEW_SUB_ORDER(200, "新建子订单"),

    // 子订单支付
    PAID_SUB_ORDER(240, "完成子订单支付"),

    // 签收虚拟订单
    SIGN_VIRTUAL_ORDER(300, "签收虚拟订单"),
    ;

    private Integer code;
    private String msg;


    public static Map<Integer, String> toMap() {
        Map<Integer, String> map = new HashMap<>(16);
        for (OrderOperateTypeEnum element : OrderOperateTypeEnum.values()) {
            map.put(element.getCode(), element.getMsg());
        }
        return map;
    }

    public static OrderOperateTypeEnum getByCode(Integer code) {
        for (OrderOperateTypeEnum element : OrderOperateTypeEnum.values()) {
            if (code.equals(element.getCode())) {
                return element;
            }
        }
        return null;
    }
}