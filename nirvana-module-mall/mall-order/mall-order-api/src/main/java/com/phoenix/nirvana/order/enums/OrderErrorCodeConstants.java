package com.phoenix.nirvana.order.enums;


import com.phoenix.nirvana.common.exception.ErrorCode;

/**
 * 错误码枚举类
 * <p>
 * 商品系统，使用 1-004-000-000 段
 */
public interface OrderErrorCodeConstants {

    // ========== ORDER CATEGORY 模块 ==========
    ErrorCode ORDER_AUTO_NO_GEN_ERROR = new ErrorCode(1004001001, "订单号内存段号申请失败");
    ErrorCode ORDER_NO_TYPE_ERROR = new ErrorCode(1004001002, "订单号类型错误");
    ErrorCode ORDER_ITEM_NULL = new ErrorCode(1004001003, "订单条目不能为空");
    ErrorCode ORDER_ITEM_SALE_QUANTITY_ERROR = new ErrorCode(1004001004, "订单条目销售数量要大于0");
    ErrorCode ORDER_ITEM_SALE_PRICE_ERROR = new ErrorCode(1004001005, "订单条目销售价格不能小于0");
    ErrorCode ORDER_ITEM_AMOUNT_ERROR = new ErrorCode(1004001006, "订单费用不能小于0");
    ErrorCode ORDER_AMOUNT_TYPE_PARAM_ERROR = new ErrorCode(1004001007, "订单费用类型错误");
    ErrorCode ORDER_DISCOUNT_AMOUNT_IS_NULL = new ErrorCode(1004001008, "订单优惠券抵扣金额不能为空");

}
