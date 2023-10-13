package com.phoenix.nirvana.order.mq.message;

import com.phoenix.nirvana.common.enums.OrderStatusChangeEnum;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 订单状态流转消息实体
 *
 * @Description:
 * @Author: xuyongkang
 * @Date 2023/7/11 16:37
 */
@Data
@Accessors(chain = true)
public class OrderStdChangeMessage implements Serializable {

    /**
     * 订单编号
     */
    private String orderNo;

    /**
     * 订单类型 0正常 1 预定
     */
    private String orderType;

    /**
     * 订单来源渠道
     */
    private String orderSourceChannel;

    /**
     * 订单支付类型
     */
    private String orderPayType;

    /**
     * 订单原价
     */
    private BigDecimal orderOldPrice;

    /**
     * 订单总价
     */
    private BigDecimal orderTotalPrice;

    /**
     * 预计送达时间
     */
    private String evaluateTime;

    /**
     * 订单下单时间
     */
    private LocalDateTime createTime;

    /**
     * 订单支付时间
     */
    private LocalDateTime payTime;

    /**
     * 商家接单时间
     */
    private LocalDateTime shopAcceptTime;

    /**
     * 骑手接单时间
     */
    private LocalDateTime deliveryAcceptTime;

    /**
     * 商家出餐时间
     */
    private LocalDateTime shopMealTime;

    /**
     * 订单配送时间
     */
    private LocalDateTime deliveryTime;

    /**
     * 订单预计完成时间
     */
    private LocalDateTime estimatedCompleteTime;

    /**
     * 订单用户确认收货时间
     */
    private LocalDateTime userConfirmTime;

    /**
     * 订单完成时间
     */
    private LocalDateTime completeTime;

    /**
     * 订单取消时间
     */
    private LocalDateTime cancelTime;

    /**
     * 订单状态变更枚举
     */
    private OrderStatusChangeEnum statusChange;
}
