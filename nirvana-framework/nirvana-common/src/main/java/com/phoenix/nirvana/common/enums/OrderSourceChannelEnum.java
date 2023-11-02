package com.phoenix.nirvana.common.enums;

import com.phoenix.nirvana.common.core.IntArrayValuable;

import java.util.*;

/**
 * 接入方业务线枚举
 *
 * @author xuyongkang
 * @version 1.0
 */
public enum OrderSourceChannelEnum implements IntArrayValuable {

    SELF_MALL(1, "自营商城"),
    TEST(2, "测试"),
    ;

    public static final int[] ARRAYS = Arrays.stream(values()).mapToInt(OrderSourceChannelEnum::getCode).toArray();

    private Integer code;

    private String msg;

    OrderSourceChannelEnum(Integer code, String msg) {
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
        for (OrderSourceChannelEnum element : OrderSourceChannelEnum.values()) {
            map.put(element.getCode(), element.getMsg());
        }
        return map;
    }

    public static OrderSourceChannelEnum getByCode(Integer code) {
        for (OrderSourceChannelEnum element : OrderSourceChannelEnum.values()) {
            if (code.equals(element.getCode())) {
                return element;
            }
        }
        return null;
    }

    public static Set<Integer> allowableValues() {
        Set<Integer> allowableValues = new HashSet<>(values().length);
        for (OrderSourceChannelEnum OrderSourceChannelEnum : values()) {
            allowableValues.add(OrderSourceChannelEnum.getCode());
        }
        return allowableValues;
    }


    @Override
    public int[] array() {
        return ARRAYS;
    }
}