package com.phoenix.nirvana.inventory.enums;


import com.phoenix.nirvana.common.exception.ErrorCode;

/**
 * 错误码枚举类
 * <p>
 * 库存系统，使用 1-005-000-000 段
 */
public interface InventoryErrorCodeConstants {

    // ========== ORDER CATEGORY 模块 ==========
    ErrorCode DEDUCT_PRODUCT_STOCK_ORDER_NO_ERROR = new ErrorCode(1005001001, "订单号不能为空");
    ErrorCode DEDUCT_PRODUCT_STOCK_ORDER_ITERM_ERROR = new ErrorCode(1005001002, "订单商品条目不能为空");
    ErrorCode DEDUCT_PRODUCT_SKU_STOCK_CANNOT_ACQUIRE_ERROR = new ErrorCode(1005001003, "无法获取扣减库存锁");
    ErrorCode PRODUCT_SKU_STOCK_NOT_FOUND_ERROR = new ErrorCode(1005001004, "商品库存记录不存在");
    ErrorCode RELEASE_PRODUCT_SKU_STOCK_ERROR = new ErrorCode(1005001005, "释放库存失败");




}
