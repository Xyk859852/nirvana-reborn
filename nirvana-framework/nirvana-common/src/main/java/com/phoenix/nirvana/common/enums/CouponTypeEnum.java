package com.phoenix.nirvana.common.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * 优惠卷类型
 *
 * @author xuyongkang
 * @version 1.0
 */
public enum CouponTypeEnum {

    CASH_COUPON(1, "现金券"),

    CONDITION_COUPON(2, "满减券"),

    READ_PACKET_COUPON(3, "红包");


    private Integer code;

    private String msg;

    CouponTypeEnum(Integer code, String msg) {
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
        for (CouponTypeEnum element : CouponTypeEnum.values()) {
            map.put(element.getCode(), element.getMsg());
        }
        return map;
    }

    public static CouponTypeEnum getByCode(Integer code) {
        for (CouponTypeEnum element : CouponTypeEnum.values()) {
            if (code.equals(element.getCode())) {
                return element;
            }
        }
        return null;
    }

}