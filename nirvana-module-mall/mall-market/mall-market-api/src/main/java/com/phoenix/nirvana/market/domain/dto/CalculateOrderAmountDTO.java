package com.phoenix.nirvana.market.domain.dto;

import com.phoenix.nirvana.common.enums.OrderTypeEnum;
import com.phoenix.nirvana.common.validator.InEnum;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

/**
 * 订单金额计算请求对象
 *
 * @Author: xuyongkang
 * @Date 2023/7/17 15:47
 */
@Data
public class CalculateOrderAmountDTO implements Serializable {


    @NotNull(message = "订单号不能为空")
    @NotEmpty(message = "订单号不能为空")
    @NotBlank(message = "订单号不能为空")
    @ApiModelProperty("订单号")
    private String orderNo;

    @InEnum(value = OrderTypeEnum.class)
    @ApiModelProperty("订单类型 0正常 1 预定")
    private Integer orderType;

    @NotNull(message = "商家不能为空")
    @ApiModelProperty("订单所属商家")
    private Long shopId;

    @NotNull(message = "下单人不能为空")
    @ApiModelProperty("下单人 ")
    private String userId;

    @ApiModelProperty("订单收货地址id ")
    @NotNull(message = "收货地址不能为空")
    private Long addressId;

    @ApiModelProperty("订单条目集合")
    private List<OrderItemDTO> orderItemList;

    @ApiModelProperty("订单优惠券集合")
    private List<OrderCouponDTO> orderCouponList;


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

        @ApiModelProperty("包装价格")
        private Integer packPrice;

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


}
