package com.phoenix.nirvana.product.enums.spu;

/**
 * spu 上架状态枚举类
 *
 * @Description:
 * @Author: xuyongkang
 * @Date 2023/5/10 2:20 PM
 */
public enum ProductSpuPushStatusEnum {
    //上架状态[-1 已创建 0 - 上架，1 - 下架]
    CREATE("-1"),
    PUSH("0"),
    UNDER("1");

    private final String status;


    ProductSpuPushStatusEnum(String enable) {
        this.status = enable;
    }
}
