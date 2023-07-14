package com.phoenix.nirvana.order.enums;

import com.phoenix.nirvana.common.core.IntArrayValuable;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 订单费用类型枚举
 *
 * @author xuyongkang
 * @version 1.0
 */
public enum OrderAmountTypeEnum implements IntArrayValuable {

    ORIGIN_PAY_AMOUNT(10, "订单支付原价"),
    COUPON_DISCOUNT_AMOUNT(20, "优惠券抵扣金额"),
    SHIPPING_AMOUNT(30, "运费"),
    BOX_AMOUNT(40, "包装费"),
    REAL_PAY_AMOUNT(50, "实付金额"),
    OTHER_AMOUNT(127, "其他费用");

    private Integer code;

    private String msg;

    public static final int[] ARRAYS = Arrays.stream(values()).mapToInt(OrderAmountTypeEnum::getCode).toArray();


    OrderAmountTypeEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public static Map<Integer, String> toMap() {
        Map<Integer, String> map = new HashMap<>(16);
        for (OrderAmountTypeEnum element : OrderAmountTypeEnum.values()) {
            map.put(element.getCode(), element.getMsg());
        }
        return map;
    }

    public static OrderAmountTypeEnum getByCode(Integer code) {
        for (OrderAmountTypeEnum element : OrderAmountTypeEnum.values()) {
            if (code.equals(element.getCode())) {
                return element;
            }
        }
        return null;
    }


    @Override
    public int[] array() {
        return ARRAYS;
    }
}