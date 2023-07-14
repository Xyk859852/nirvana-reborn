package com.phoenix.nirvana.inventory.dal.mysql.dataobject.stocklog;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * <p>
 * 库存扣减日志表
 * </p>
 *
 * @author xuyongkang
 * @since 2023-07-14
 */
@Getter
@Setter
@TableName("inventory_product_stock_log")
@ApiModel(value = "InventoryProductStockLogDO对象", description = "库存扣减日志表")
public class InventoryProductStockLogDO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键")
    @TableId(value = "product_stock_log_id", type = IdType.AUTO)
    private Long productStockLogId;

    @ApiModelProperty("商品sku编号")
    @TableField("sku_code")
    private String skuCode;

    @ApiModelProperty("订单编号")
    @TableField("order_no")
    private String orderNo;

    @ApiModelProperty("原始的销售库存")
    @TableField("origin_sale_stock_quantity")
    private Long originSaleStockQuantity;

    @ApiModelProperty("原始的已销售库存")
    @TableField("origin_saled_stock_quantity")
    private Long originSaledStockQuantity;

    @ApiModelProperty("扣除后的销售库存")
    @TableField("deducted_sale_stock_quantity")
    private Long deductedSaleStockQuantity;

    @ApiModelProperty("增加后的已销售库存")
    @TableField("increased_saled_stock_quantity")
    private Long increasedSaledStockQuantity;

    @ApiModelProperty("状态，1-已扣减；2-已释放")
    @TableField("status")
    private Integer status;

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
