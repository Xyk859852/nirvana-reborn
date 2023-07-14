package com.phoenix.nirvana.inventory.rpc.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 锁定商品库存入参
 *
 * @author xuyongkang
 * @version 1.0
 */
@Data
public class DeductProductStockDTO implements Serializable {

    private static final long serialVersionUID = 8229493558996271243L;


    /**
     * 订单号
     */
    private String orderNo;

    /**
     * 用户ID
     */
    private String userId;

    /**
     * 卖家ID
     */
    private Long shopId;

    /**
     * 订单条目
     */
    private List<OrderItemDTO> orderItemList;

    @Data
    public static class OrderItemDTO implements Serializable {

        private static final long serialVersionUID = 6870559288334853954L;

        /**
         * 商品sku编号
         */
        private String skuCode;

        /**
         * 销售数量
         */
        private Integer saleQuantity;

    }

}