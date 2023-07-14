package com.phoenix.nirvana.common.enums;

/**
 * <p>
 * redis 分布式锁key
 * </p>
 *
 * @author xuyongkang
 */
public class RedisLockKeyConstants {

    /**
     * 订单支付
     */
    public static final String ORDER_PAY_KEY = "#ORDER_PAY_KEY:";

    public static final String ORDER_FULFILL_KEY = "#ORDER_FULFILL_KEY:";

    public static final String FULFILL_KEY = "#FULFILL_KEY:";

    public static final String ORDER_AFTER_FULFILL_EVENT_KEY = "#ORDER_AFTER_FULFILL_EVENT_KEY:";

    public static final String REFUND_KEY = "#REFUND_KEY:";

    public static final String CANCEL_KEY = "#CANCEL_KEY:";

    /**
     * 缺品请求锁
     */
    public static final String LACK_REQUEST_KEY = "#LACK_REQUEST_KEY:";

    /**
     * 新增商品库存锁
     */
    public static final String ADD_PRODUCT_STOCK_KEY = "#ADD_PRODUCT_STOCK_KEY:";

    /**
     * 扣减商品库存锁（保证mysql+redis库存扣减的原子性）
     */
    public static final String DEDUCT_PRODUCT_STOCK_KEY = "#ORDER_DEDUCT_PRODUCT_STOCK_KEY:";


    /**
     * 释放商品库存锁（保证mysql+redis库存释放的原子性）
     */
    public static final String RELEASE_PRODUCT_STOCK_KEY = "#RELEASE_PRODUCT_STOCK_KEY:";

    /**
     * 调整库存存锁
     */
    public static final String MODIFY_PRODUCT_STOCK_KEY = "#MODIFY_PRODUCT_STOCK_KEY:";

    /**
     * 释放优惠券锁
     */
    public static final String RELEASE_COUPON_KEY = "#RELEASE_COUPON_KEY:";

    /**
     * 更新用户积分锁
     */
    public static final String UPDATE_USER_POINT_KEY = "#UPDATE_USER_POINT_KEY:";
}
