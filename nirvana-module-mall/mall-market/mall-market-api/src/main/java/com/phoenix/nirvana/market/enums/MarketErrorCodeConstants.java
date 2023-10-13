package com.phoenix.nirvana.market.enums;


import com.phoenix.nirvana.common.exception.ErrorCode;

/**
 * 错误码枚举类
 * <p>
 * 营销系统，使用 1-006-000-000 段
 */
public interface MarketErrorCodeConstants {

    // ========== MARKET CATEGORY 模块 ==========
    ErrorCode USER_COUPON_IS_NULL = new ErrorCode(1006001001, "优惠卷不存在");

}
