package com.phoenix.nirvana.market.domain.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author xuyongkang
 * @version 1.0
 */
@Data
public class CalculateOrderAmountVO implements Serializable {

    /**
     * 订单总金额
     */
    private Integer totalProductAmount;

    /**
     * 订单满减总金额
     */
    private Integer conditionAmount;

    /**
     * 订单红包总金额
     */
    private Integer redPaketAmount;

    /**
     * 订单优惠卷总金额
     */
    private Integer discountAmount;

    /**
     * 订单运费总金额
     */
    private Integer shippingAmount;

    /**
     * 订单包装总金额
     */
    private Integer packAmount;

    /**
     * 订单条目费用信息
     */
    private List<OrderItemAmountDetailVO> orderAmountDetailList;

    /**
     * 订单优惠信息
     */
    private List<OrderCouponDetailVO> orderCouponDetailList;


    /**
     * 营销计算出来的订单条目费用信息
     */
    @Data
    public static class OrderItemAmountDetailVO implements Serializable {


        /**
         * 订单编号
         */
        private String orderNo;

        /**
         * 产品id
         */
        private Long productId;

        /**
         * 产品类型
         */
        private Integer productType;

        /**
         * sku编码
         */
        private String skuCode;

        /**
         * 销售数量
         */
        private Integer packPrice;

        /**
         * 销售数量
         */
        private Integer saleQuantity;

        /**
         * 销售单价
         */
        private Integer salePrice;

        /**
         * 优惠金额
         */
        private Integer discountsAmount;

        /**
         * 收费金额
         */
        private Integer productTotalPrice;

    }


    /**
     * 营销计算出来的优惠信息
     */
    @Data
    public static class OrderCouponDetailVO implements Serializable {

        /**
         * 订单编号
         */
        private String orderNo;

        /**
         * 优惠类型
         */
        private String couponType;

        /**
         * 优惠类型名称
         */
        private String couponTypeName;

        /**
         * 优惠关联id（如红包编号，无则默认0）
         */
        private Long discountRelationId;

        /**
         * 优惠金额
         */
        private Integer discountPrice;

        /**
         * 购买金额（红包才需要购买）
         */
        private Integer salePrice;

    }

}