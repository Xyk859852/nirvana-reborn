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

}
