package com.phoenix.nirvana.order.dal.mysql.dataobject.order;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

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
public class ProductOrderDO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("订单id ")
    @TableId(value = "order_id", type = IdType.AUTO)
    private Long orderId;

    @ApiModelProperty("订单编号 ")
    @TableField("order_no")
    private String orderNo;

    @ApiModelProperty("订单类型 0正常 1 预定")
    @TableField("order_type")
    private String orderType;

    @ApiModelProperty("订单来源渠道")
    @TableField("order_source_channel")
    private String orderSourceChannel;

    @ApiModelProperty("订单支付类型")
    @TableField("order_pay_type")
    private String orderPayType;

    @ApiModelProperty("订单原价")
    @TableField("order_old_price")
    private BigDecimal orderOldPrice;

    @ApiModelProperty("订单实际价格")
    @TableField("order_real_price")
    private BigDecimal orderRealPrice;

    @ApiModelProperty("订单优惠价格")
    @TableField("order_discount_price")
    private BigDecimal orderDiscountPrice;

    @ApiModelProperty("订单配送费用")
    @TableField("delivery_fee")
    private BigDecimal deliveryFee;

    @ApiModelProperty("订单包装费用")
    @TableField("pack_fee")
    private BigDecimal packFee;

    @ApiModelProperty("订单总价")
    @TableField("order_total_price")
    private BigDecimal orderTotalPrice;

    @ApiModelProperty("订单平台服务费")
    @TableField("platform_service_fee")
    private BigDecimal platformServiceFee;

    @ApiModelProperty("订单平台补贴费")
    @TableField("platform_allowancen_fee")
    private BigDecimal platformAllowancenFee;

    @ApiModelProperty("用户打赏费用")
    @TableField("user_give_fee")
    private BigDecimal userGiveFee;

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

    @ApiModelProperty("删除标志（0代表存在 1代表删除）")
    @TableField("deleted")
    private Boolean deleted;

    @ApiModelProperty("创建者")
    @TableField("creator")
    private Long creator;

    @ApiModelProperty("预计送达时间")
    @TableField("evaluate_time")
    private String evaluateTime;

    @ApiModelProperty("订单下单时间")
    @TableField("create_time")
    private Timestamp createTime;

    @ApiModelProperty("订单支付时间")
    @TableField("pay_time")
    private Timestamp payTime;

    @ApiModelProperty("商家接单时间")
    @TableField("shop_accept_time")
    private Timestamp shopAcceptTime;

    @ApiModelProperty("骑手接单时间")
    @TableField("delivery_accept_time")
    private Timestamp deliveryAcceptTime;

    @ApiModelProperty("商家出餐时间")
    @TableField("shop_meal_time")
    private Timestamp shopMealTime;

    @ApiModelProperty("订单配送时间")
    @TableField("delivery_time")
    private Timestamp deliveryTime;

    @ApiModelProperty("订单预计完成时间")
    @TableField("estimated_complete_time")
    private Timestamp estimatedCompleteTime;

    @ApiModelProperty("订单用户确认收货时间")
    @TableField("user_confirm_time")
    private Timestamp userConfirmTime;

    @ApiModelProperty("订单完成时间")
    @TableField("complete_time")
    private Timestamp completeTime;

    @ApiModelProperty("订单取消时间")
    @TableField("cancel_time")
    private Timestamp cancelTime;

    @ApiModelProperty("更新者")
    @TableField("updater")
    private Long updater;

    @ApiModelProperty("更新时间")
    @TableField("update_time")
    private Timestamp updateTime;

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
