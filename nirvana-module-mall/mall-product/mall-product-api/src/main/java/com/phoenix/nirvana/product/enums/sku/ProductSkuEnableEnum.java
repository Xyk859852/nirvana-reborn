package com.phoenix.nirvana.product.enums.sku;

/**
 * sku 启用禁用枚举类
 *
 * @Description:
 * @Author: xuyongkang
 * @Date 2023/5/10 2:20 PM
 */
public enum ProductSkuEnableEnum {

    TRUE("0"),
    FALSE("1");

    private final String enable;


    ProductSkuEnableEnum(String enable) {
        this.enable = enable;
    }
}
