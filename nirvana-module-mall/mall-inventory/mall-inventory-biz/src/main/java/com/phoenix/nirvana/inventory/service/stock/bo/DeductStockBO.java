package com.phoenix.nirvana.inventory.service.stock.bo;

import com.phoenix.nirvana.inventory.dal.mysql.dataobject.stock.InventoryProductStockDO;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 扣减库存内部流转对象
 *
 * @Author: xuyongkang
 * @Date 2023/7/14 15:52
 */
@Data
@AllArgsConstructor
public class DeductStockBO {

    /**
     * 订单ID
     */
    private String orderNo;

    /**
     * 商品skuCode
     */
    private String skuCode;

    /**
     * 销售数量
     */
    private Integer saleQuantity;

    /**
     * sku库存数据
     */
    private InventoryProductStockDO productStockDO;
}
