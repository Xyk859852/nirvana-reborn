package com.phoenix.nirvana.product.enums.category;

/**
 * 商品分类的编号枚举
 */
public enum ProductCategoryIdEnum {

    /**
     * 根节点
     */
    ROOT(0l);

    private final Long id;

    ProductCategoryIdEnum(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

}
