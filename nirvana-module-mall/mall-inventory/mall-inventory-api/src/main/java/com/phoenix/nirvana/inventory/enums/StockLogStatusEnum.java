package com.phoenix.nirvana.inventory.enums;

/**
 * 库存扣减日志状态
 *
 * @author xuyongkang
 * @version 1.0
 */
public enum StockLogStatusEnum {

    DEDUCTED(1, "已扣除"),
    RELEASED(2, "已释放");

    private Integer code;

    private String msg;

    StockLogStatusEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}