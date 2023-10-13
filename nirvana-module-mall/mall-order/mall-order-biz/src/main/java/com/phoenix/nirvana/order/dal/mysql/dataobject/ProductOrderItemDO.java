package com.phoenix.nirvana.order.dal.mysql.dataobject;

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
 * 商品订单明细表
 * </p>
 *
 * @author xuyongkang
 * @since 2023-07-13
 */
@Getter
@Setter
@TableName("product_order_item")
@ApiModel(value = "ProductOrderItemDO对象", description = "商品订单明细表")
public class ProductOrderItemDO extends BaseDO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("订单明细id ")
    @TableId(value = "order_item_id", type = IdType.AUTO)
    private Long orderItemId;

    @ApiModelProperty("订单编号 ")
    @TableField("order_no")
    private String orderNo;

    @ApiModelProperty("商品id")
    @TableField("product_id")
    private Long productId;

    @ApiModelProperty("商品名称")
    @TableField("product_name")
    private String productName;

    @ApiModelProperty("商品图片")
    @TableField("product_default_img")
    private String productDefaultImg;

    @ApiModelProperty("商品sku编号 ")
    @TableField("product_sku_code")
    private String productSkuCode;

    @ApiModelProperty("商品sku名称")
    @TableField("product_sku_name")
    private String productSkuName;

    @ApiModelProperty("商品sku名称前台")
    @TableField("product_sku_name_front")
    private String productSkuNameFront;

    @ApiModelProperty("商品数量")
    @TableField("product_count")
    private Integer productCount;

    @ApiModelProperty("商品单价")
    @TableField("product_unit_price")
    private Integer productUnitPrice;

    @ApiModelProperty("商品总价")
    @TableField("product_total_price")
    private Integer productTotalPrice;

    @ApiModelProperty("订单优惠价格（包含，满减，优惠券，红包）")
    @TableField("product_discount_price")
    private Integer productDiscountPrice;

    @ApiModelProperty("订单发货物流单号 ")
    @TableField("order_logistics_no")
    private String orderLogisticsNo;

    @ApiModelProperty("订单发货物流公司 ")
    @TableField("order_logistics_name")
    private String orderLogisticsName;

    @ApiModelProperty("订单退货物流单号 ")
    @TableField("order_return_logistics_no")
    private String orderReturnLogisticsNo;

    @ApiModelProperty("订单退货物流公司 ")
    @TableField("order_return_logistics_name")
    private String orderReturnLogisticsName;

    @ApiModelProperty("订单条目状态(0正常 1退款中 2退款完成)")
    @TableField("status")
    private String status;

    @ApiModelProperty("备注")
    @TableField("remark")
    private String remark;
}
