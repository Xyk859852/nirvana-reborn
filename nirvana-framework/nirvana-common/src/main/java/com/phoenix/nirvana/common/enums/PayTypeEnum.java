package com.phoenix.nirvana.common.enums;

import com.phoenix.nirvana.common.core.IntArrayValuable;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 支付类型枚举
 *
 * @author xuyongkang
 * @version 1.0
 */
public enum PayTypeEnum implements IntArrayValuable {

    WECHAT_PAY(10, "微信支付"),
    ALI_PAY(20, "支付宝支付");

    public static final int[] ARRAYS = Arrays.stream(values()).mapToInt(PayTypeEnum::getCode).toArray();


    private Integer code;

    private String msg;

    PayTypeEnum(Integer code, String msg) {
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
        for (PayTypeEnum element : PayTypeEnum.values()) {
            map.put(element.getCode(), element.getMsg());
        }
        return map;
    }

    public static PayTypeEnum getByCode(Integer code) {
        for (PayTypeEnum element : PayTypeEnum.values()) {
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