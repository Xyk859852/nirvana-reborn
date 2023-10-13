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
 * 商品订单优惠明细表
 * </p>
 *
 * @author xuyongkang
 * @since 2023-07-19
 */
@Getter
@Setter
@TableName("product_order_discount")
@ApiModel(value = "ProductOrderDiscountDO对象", description = "商品订单优惠明细表")
public class ProductOrderDiscountDO extends BaseDO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("订单优惠明细id ")
    @TableId(value = "order_discount_id", type = IdType.AUTO)
    private Long orderDiscountId;

    @ApiModelProperty("订单编号 ")
    @TableField("order_no")
    private String orderNo;

    @ApiModelProperty("优惠类型 ")
    @TableField("order_type")
    private String couponType;

    @ApiModelProperty("优惠类型名称 ")
    @TableField("order_type_name")
    private String couponTypeName;

    @ApiModelProperty("优惠关联id（如红包编号，无则默认0） ")
    @TableField("discount_relation_id")
    private Long discountRelationId;

    @ApiModelProperty("优惠金额")
    @TableField("discount_price")
    private Integer discountPrice;

    @ApiModelProperty("购买金额（红包才需要购买）")
    @TableField("sale_price")
    private Integer salePrice;

    @ApiModelProperty("订单优惠明细状态")
    @TableField("status")
    private String status;

    @ApiModelProperty("备注")
    @TableField("remark")
    private String remark;
}
