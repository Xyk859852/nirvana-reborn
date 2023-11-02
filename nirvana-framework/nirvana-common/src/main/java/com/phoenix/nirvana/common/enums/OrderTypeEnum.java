package com.phoenix.nirvana.common.enums;

import com.phoenix.nirvana.common.core.IntArrayValuable;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 订单类型枚举
 *
 * @author xuyongkang
 * @version 1.0
 */
public enum OrderTypeEnum implements IntArrayValuable {

    NORMAL(0, "正常"),

    RESERVE(1, "预定");


    public static final int[] ARRAYS = Arrays.stream(values()).mapToInt(OrderTypeEnum::getCode).toArray();

    private Integer code;

    private String msg;

    OrderTypeEnum(Integer code, String msg) {
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
        for (OrderTypeEnum element : OrderTypeEnum.values()) {
            map.put(element.getCode(), element.getMsg());
        }
        return map;
    }

    public static OrderTypeEnum getByCode(Integer code) {
        for (OrderTypeEnum element : OrderTypeEnum.values()) {
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