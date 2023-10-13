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
import java.time.LocalDateTime;

/**
 * <p>
 * 商品订单表
 * </p>
 *
 * @author xuyongkang
 * @since 2023-06-29
 */
@Getter
@Setter
@TableName("product_order")
@ApiModel(value = "ProductOrderDO对象", description = "商品订单表")
public class ProductOrderDO extends BaseDO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("订单id ")
    @TableId(value = "order_id", type = IdType.AUTO)
    private Long orderId;

    @ApiModelProperty("订单编号 ")
    @TableField("order_no")
    private String orderNo;

    @ApiModelProperty("订单类型 0正常 1 预定")
    @TableField("order_type")
    private Integer orderType;

    @ApiModelProperty("订单来源渠道")
    @TableField("order_source_channel")
    private Integer orderSourceChannel;

    @ApiModelProperty("订单支付类型")
    @TableField("order_pay_type")
    private Integer orderPayType;

    @ApiModelProperty("订单原价")
    @TableField("order_old_price")
    private Integer orderOldPrice;

    @ApiModelProperty("订单实际价格")
    @TableField("order_real_price")
    private Integer orderRealPrice;

    @ApiModelProperty("订单优惠价格")
    @TableField("order_discount_price")
    private Integer orderDiscountPrice;

    @ApiModelProperty("订单配送费用")
    @TableField("delivery_fee")
    private Integer deliveryFee;

    @ApiModelProperty("订单包装费用")
    @TableField("pack_fee")
    private Integer packFee;

    @ApiModelProperty("订单总价")
    @TableField("order_total_price")
    private Integer orderTotalPrice;

    @ApiModelProperty("订单平台服务费")
    @TableField("platform_service_fee")
    private Integer platformServiceFee;

    @ApiModelProperty("订单平台补贴费")
    @TableField("platform_allowancen_fee")
    private Integer platformAllowancenFee;

    @ApiModelProperty("用户打赏费用")
    @TableField("user_give_fee")
    private Integer userGiveFee;

    @ApiModelProperty("分销商id")
    @TableField("tenant_id")
    private Long tenantId;

    @ApiModelProperty("订单所属商家 ")
    @TableField("shop_id")
    private Long shopId;

    @ApiModelProperty("订单所属商家名称")
    @TableField("shop_name")
    private String shopName;

    @ApiModelProperty("商家主图")
    @TableField("shop_main_img")
    private String shopMainImg;

    @ApiModelProperty("订单所属商家序号")
    @TableField("shop_order_number")
    private Integer shopOrderNumber;

    @ApiModelProperty("下单人 ")
    @TableField("user_id")
    private Long userId;

    @ApiModelProperty("订单收货地址id ")
    @TableField("address_id")
    private Long addressId;

    @ApiModelProperty("下单用户手机号")
    @TableField("user_phone")
    private String userPhone;

    @ApiModelProperty("餐具份数 ")
    @TableField("need_dinner_set")
    private String needDinnerSet;

    @ApiModelProperty("是否需要发票 0不要 1 要  ")
    @TableField("need_invoice")
    private String needInvoice;

    @ApiModelProperty("订单状态")
    @TableField("status")
    private String status;

    @ApiModelProperty("预计送达时间")
    @TableField("evaluate_time")
    private String evaluateTime;

    @ApiModelProperty("订单支付时间")
    @TableField("pay_time")
    private LocalDateTime payTime;

    @ApiModelProperty("商家接单时间")
    @TableField("shop_accept_time")
    private LocalDateTime shopAcceptTime;

    @ApiModelProperty("骑手接单时间")
    @TableField("delivery_accept_time")
    private LocalDateTime deliveryAcceptTime;

    @ApiModelProperty("商家出餐时间")
    @TableField("shop_meal_time")
    private LocalDateTime shopMealTime;

    @ApiModelProperty("订单配送时间")
    @TableField("delivery_time")
    private LocalDateTime deliveryTime;

    @ApiModelProperty("订单预计完成时间")
    @TableField("estimated_complete_time")
    private LocalDateTime estimatedCompleteTime;

    @ApiModelProperty("订单用户确认收货时间")
    @TableField("user_confirm_time")
    private LocalDateTime userConfirmTime;

    @ApiModelProperty("订单完成时间")
    @TableField("complete_time")
    private LocalDateTime completeTime;

    @ApiModelProperty("订单取消时间")
    @TableField("cancel_time")
    private LocalDateTime cancelTime;

    @ApiModelProperty("备注")
    @TableField("remark")
    private String remark;

    @ApiModelProperty("取消订单备注")
    @TableField("cancel_order_remark")
    private String cancelOrderRemark;

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

    @ApiModelProperty("订单支付id")
    @TableField("pay_order_id")
    private String payOrderId;

    @ApiModelProperty("订单申请审核状态")
    @TableField("apply_status")
    private String applyStatus;

    @ApiModelProperty("订单流程变动状态")
    @TableField("move_status")
    private String moveStatus;

    @ApiModelProperty("推广用户")
    @TableField("share_user_id")
    private Long shareUserId;

    @ApiModelProperty("提货码")
    @TableField("fetch_code")
    private String fetchCode;
}
