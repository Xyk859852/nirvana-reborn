package com.phoenix.nirvana.common.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * 订单号类型枚举
 *
 * @author xuyongkang
 * @version 1.0
 */
public enum OrderNoTypeEnum {

    SALE_ORDER(10, "正向订单号"),

    AFTER_SALE(20, "售后单号");


    private Integer code;

    private String msg;

    OrderNoTypeEnum(Integer code, String msg) {
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
        for (OrderNoTypeEnum element : OrderNoTypeEnum.values()) {
            map.put(element.getCode(), element.getMsg());
        }
        return map;
    }

    public static OrderNoTypeEnum getByCode(Integer code) {
        for (OrderNoTypeEnum element : OrderNoTypeEnum.values()) {
            if (code.equals(element.getCode())) {
                return element;
            }
        }
        return null;
    }

}