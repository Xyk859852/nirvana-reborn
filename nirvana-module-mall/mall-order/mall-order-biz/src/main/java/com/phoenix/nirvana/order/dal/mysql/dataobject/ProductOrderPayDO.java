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
 * 商品订单支付明细表
 * </p>
 *
 * @author xuyongkang
 * @since 2023-07-19
 */
@Getter
@Setter
@TableName("product_order_pay")
@ApiModel(value = "ProductOrderPayDO对象", description = "商品订单支付明细表")
public class ProductOrderPayDO extends BaseDO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("订单支付明细id ")
    @TableId(value = "order_pay_id", type = IdType.AUTO)
    private Long orderPayId;

    @ApiModelProperty("订单编号 ")
    @TableField("order_no")
    private String orderNo;

    @ApiModelProperty("订单支付id")
    @TableField("pay_order_id")
    private String payOrderId;

    @ApiModelProperty("订单支付金额")
    @TableField("order_pay_price")
    private Integer orderPayPrice;

    @ApiModelProperty("订单支付金额来源(10微信 20支付宝)")
    @TableField("order_pay_source")
    private Integer orderPaySource;

    @ApiModelProperty("订单支付明细状态，0未支付，1已支付，2已退款")
    @TableField("status")
    private String status;

    @ApiModelProperty("备注")
    @TableField("remark")
    private String remark;
}
