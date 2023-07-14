package com.phoenix.nirvana.order.rpc.order.domain.dto;

import com.phoenix.nirvana.common.validator.InEnum;
import com.phoenix.nirvana.common.validator.Mobile;
import com.phoenix.nirvana.order.enums.OrderAmountTypeEnum;
import com.phoenix.nirvana.order.enums.OrderTypeEnum;
import com.phoenix.nirvana.order.enums.PayTypeEnum;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

/**
 * 订单创建对象
 *
 * @Author: xuyongkang
 * @Date 2023/7/11 17:22
 */
@Data
@Validated
public class CreateOrderDTO implements Serializable {

    @NotNull(message = "订单号不能为空")
    @NotEmpty(message = "订单号不能为空")
    @NotBlank(message = "订单号不能为空")
    @ApiModelProperty("订单号")
    private String orderNo;

    @InEnum(value = OrderTypeEnum.class)
    @ApiModelProperty("订单类型 0正常 1 预定")
    private Integer orderType;

    @NotNull
    @ApiModelProperty("预计送达时间")
    private String evaluateTime;

    @InEnum(value = OrderTypeEnum.class)
    @ApiModelProperty("订单来源渠道")
    private Integer orderSourceChannel;

    @InEnum(value = PayTypeEnum.class)
    @ApiModelProperty("订单支付类型")
    private Integer orderPayType;

    @NotNull(message = "商家不能为空")
    @ApiModelProperty("订单所属商家")
    private Long shopId;

    @NotNull(message = "下单人不能为空")
    @ApiModelProperty("下单人 ")
    private String userId;

    @ApiModelProperty("订单收货地址id ")
    @NotNull(message = "收货地址不能为空")
    private Long addressId;

    @Mobile
    @ApiModelProperty("下单用户手机号")
    private String userPhone;

    @ApiModelProperty("餐具份数 ")
    private String needDinnerSet;

    @ApiModelProperty("是否需要发票 0不要 1 要  ")
    private String needInvoice;

    @NotNull(message = "创建人不能为空")
    @ApiModelProperty("创建者")
    private Long creator;

    @ApiModelProperty("备注")
    private String remark;

    @ApiModelProperty("推广用户")
    private Long shareUserId;

    @ApiModelProperty("订单条目集合")
    private List<OrderItemDTO> orderItemList;

    @ApiModelProperty("订单优惠券集合")
    private List<OrderCouponDTO> orderCouponList;

    @NotNull
    @ApiModelProperty("订单费用信息集合")
    private List<OrderAmountDTO> orderAmountList;


    /**
     * 订单条目信息
     */
    @Data
    @Validated
    public static class OrderItemDTO implements Serializable {

        @NotNull(message = "商品ID不能为空")
        @ApiModelProperty("商品id")
        private Long productId;

        @NotNull(message = "商品sku编号不能为空")
        @ApiModelProperty("商品sku编号 ")
        private String skuCode;

        @NotNull(message = "商品销售价格不能为空")
        @ApiModelProperty("商品销售价格")
        private Integer salePrice;

        @NotNull(message = "销售数量不能为空")
        @ApiModelProperty("销售数量")
        private Integer saleQuantity;
    }

    /**
     * 订单优惠卷信息
     */
    @Data
    public static class OrderCouponDTO implements Serializable {

        @ApiModelProperty("商品id")
        private Long productId;

        @ApiModelProperty("优惠卷id")
        private Long couponId;

        @ApiModelProperty("优惠卷数量")
        private Integer couponCount;
    }

    /**
     * 订单费用信息
     */
    @Data
    @Validated
    public static class OrderAmountDTO implements Serializable {

        @InEnum(value = OrderAmountTypeEnum.class)
        @ApiModelProperty("费用类型")
        private Integer amountType;

        @NotNull
        @ApiModelProperty("费用金额")
        private Integer amount;
    }


}

