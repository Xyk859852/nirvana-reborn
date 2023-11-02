package com.phoenix.nirvana.market.dal.mysql.dataobject.coupon;

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
import java.time.LocalDate;

/**
 * <p>
 * 优惠券表
 * </p>
 *
 * @author xuyongkang
 * @since 2023-07-17
 */
@Getter
@Setter
@TableName("market_coupon")
@ApiModel(value = "MarketCouponDO对象", description = "优惠券表")
public class MarketCouponDO extends BaseDO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键ID")
    @TableId(value = "coupon_id", type = IdType.AUTO)
    private Long couponId;

    @ApiModelProperty("优惠券名称")
    @TableField("name")
    private String name;

    @ApiModelProperty("优惠券类型，1：现金券，2：满减券，3：红包")
    @TableField("type")
    private String type;

    @ApiModelProperty("优惠券抵扣金额")
    @TableField("amount")
    private Integer amount;

    @ApiModelProperty("订单满多少金额才可以使用")
    @TableField("condition_amount")
    private Integer conditionAmount;

    @ApiModelProperty("红包需要用户购买，购买金额")
    @TableField("sale_amount")
    private Integer saleAmount;

    @ApiModelProperty("有效期开始时间")
    @TableField("valid_start_time")
    private LocalDate validStartTime;

    @ApiModelProperty("有效期结束时间")
    @TableField("valid_end_time")
    private LocalDate validEndTime;

    @ApiModelProperty("优惠券发行数量")
    @TableField("give_out_count")
    private Long giveOutCount;

    @ApiModelProperty("优惠券已经领取的数量")
    @TableField("received_count")
    private Long receivedCount;

    @ApiModelProperty("优惠券发放方式，1：可发放可领取，2：仅可发放，3：仅可领取，4：仅购买")
    @TableField("give_out_type")
    private String giveOutType;

    @ApiModelProperty("优惠券状态，1：未开始；2：发放中，3：已发完；4：已过期")
    @TableField("status")
    private String status;

    @ApiModelProperty("商家id")
    @TableField("shop_id")
    private Long shopId;

}
