package com.phoenix.nirvana.order.builder;

import com.phoenix.nirvana.order.dal.mongodb.OrderOperateLogDO;
import com.phoenix.nirvana.order.dal.mysql.dataobject.ProductOrderDO;
import com.phoenix.nirvana.order.dal.mysql.dataobject.ProductOrderDiscountDO;
import com.phoenix.nirvana.order.dal.mysql.dataobject.ProductOrderItemDO;
import com.phoenix.nirvana.order.dal.mysql.dataobject.ProductOrderPayDO;
import lombok.Data;

import java.util.List;

/**
 * 订单全量信息
 *
 * @Author: xuyongkang
 * @Date 2023/7/19 16:17
 */
@Data
public class FullOrderData {

    /**
     * 订单详情信息
     */
    ProductOrderDO productOrder;

    /**
     * 订单条支付信息
     */
    ProductOrderPayDO productOrderPay;

    /**
     * 订单操作日志
     */
    OrderOperateLogDO orderOperateLog;

    /**
     * 订单条目明细信息
     */
    List<ProductOrderItemDO> productOrderItem;

    /**
     * 订单优惠满减信息
     */
    List<ProductOrderDiscountDO> productOrderDiscount;
}
