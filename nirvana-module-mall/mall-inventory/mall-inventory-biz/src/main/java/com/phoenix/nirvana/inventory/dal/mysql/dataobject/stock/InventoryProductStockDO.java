package com.phoenix.nirvana.inventory.dal.mysql.dataobject.stock;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.phoenix.nirvana.mybatis.core.dataobject.BaseDO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * <p>
 * 库存中心的商品库存表
 * </p>
 *
 * @author xuyongkang
 * @since 2023-07-14
 */
@Getter
@Setter
@TableName("inventory_product_stock")
@ApiModel(value = "InventoryProductStockDO对象", description = "库存中心的商品库存表")
public class InventoryProductStockDO extends BaseDO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键")
    @TableId(value = "product_stock_id", type = IdType.AUTO)
    private Long productStockId;

    @ApiModelProperty("商品sku编号")
    @TableField("sku_code")
    private String skuCode;

    @ApiModelProperty("销售库存")
    @TableField("sale_stock_quantity")
    private Long saleStockQuantity;

    @ApiModelProperty("已销售库存")
    @TableField("saled_stock_quantity")
    private Long saledStockQuantity;

}
