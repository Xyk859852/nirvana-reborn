package com.phoenix.nirvana.inventory.dal.mysql.dataobject.stock;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.sql.Timestamp;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

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
public class InventoryProductStockDO implements Serializable {

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

    @ApiModelProperty("创建时间")
    @TableField("create_time")
    private Timestamp createTime;

    @ApiModelProperty("更新时间")
    @TableField("update_time")
    private Timestamp updateTime;

    @ApiModelProperty("删除标志（0代表存在 1代表删除）")
    @TableField("deleted")
    private Boolean deleted;
}
