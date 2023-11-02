package com.phoenix.nirvana.order.builder;

import cn.hutool.core.lang.UUID;
import com.phoenix.nirvana.common.enums.OrderOperateTypeEnum;
import com.phoenix.nirvana.common.enums.OrderStatusEnum;
import com.phoenix.nirvana.market.domain.vo.CalculateOrderAmountVO;
import com.phoenix.nirvana.order.config.OrderProperties;
import com.phoenix.nirvana.order.convert.order.ProductOrderConvert;
import com.phoenix.nirvana.order.dal.mongodb.OrderOperateLogDO;
import com.phoenix.nirvana.order.dal.mysql.dataobject.ProductOrderDO;
import com.phoenix.nirvana.order.dal.mysql.dataobject.ProductOrderDiscountDO;
import com.phoenix.nirvana.order.dal.mysql.dataobject.ProductOrderItemDO;
import com.phoenix.nirvana.order.dal.mysql.dataobject.ProductOrderPayDO;
import com.phoenix.nirvana.order.rpc.domain.dto.CreateOrderDTO;
import com.phoenix.nirvana.product.rpc.sku.domain.vo.ProductSkuInfoVO;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * 构建新订单
 *
 * @Author: xuyongkang
 * @Date 2023/7/19 15:50
 */
@Data
@AllArgsConstructor
public class NewOrderBuilder {

    private CreateOrderDTO createOrder;

    private List<ProductSkuInfoVO> productSkuList;

    private CalculateOrderAmountVO calculateOrderAmount;

    private OrderProperties orderProperties;

    private FullOrderData fullOrderData;

    /**
     * 构建订单数据
     *
     * @return
     */
    public NewOrderBuilder buildOrder() {
        ProductOrderDO productOrder = ProductOrderConvert.INSTANCE.convert2DO(createOrder);
        productOrder.setOrderOldPrice(calculateOrderAmount.getTotalProductAmount());
        productOrder.setOrderDiscountPrice(calculateOrderAmount.getDiscountAmount());
        productOrder.setOrderTotalPrice(calculateOrderAmount.getTotalProductAmount());
        productOrder.setPackFee(calculateOrderAmount.getPackAmount());
        fullOrderData.setProductOrder(productOrder);
        return this;
    }

    /**
     * 构建订单明细数据
     *
     * @return
     */
    public NewOrderBuilder buildOrderItem() {
        Map<String, ProductSkuInfoVO> skuInfoMap = productSkuList.stream().collect(Collectors.toMap(ProductSkuInfoVO::getSkuCode, Function.identity()));
        List<ProductOrderItemDO> orderItemList = ProductOrderConvert.INSTANCE.convert(calculateOrderAmount.getOrderAmountDetailList());
        for (ProductOrderItemDO productOrderItem : orderItemList) {
            ProductSkuInfoVO productSkuInfo = skuInfoMap.get(productOrderItem.getProductSkuCode());
            productOrderItem.setProductDefaultImg(productSkuInfo.getSkuDefaultImg());
            productOrderItem.setProductSkuName(productSkuInfo.getSkuName());
        }
        fullOrderData.setProductOrderItem(orderItemList);
        ProductOrderPayDO productOrderPay = new ProductOrderPayDO();
        productOrderPay.setOrderPaySource(createOrder.getOrderPayType());
        productOrderPay.setOrderNo(createOrder.getOrderNo());
        productOrderPay.setPayOrderId(UUID.fastUUID().toString(true));
        productOrderPay.setOrderPayPrice(orderItemList.stream().mapToInt(ProductOrderItemDO::getProductTotalPrice).sum());
        fullOrderData.setProductOrderPay(productOrderPay);
        return this;
    }

    /**
     * 构建订单优惠数据
     *
     * @return
     */
    public NewOrderBuilder buildOrderDiscount() {
        List<ProductOrderDiscountDO> productOrderDiscountList = ProductOrderConvert.INSTANCE.convertCoupon(calculateOrderAmount.getOrderCouponDetailList());
        fullOrderData.setProductOrderDiscount(productOrderDiscountList);
        return this;
    }

    public NewOrderBuilder buildOrderOperateLog() {
        OrderOperateLogDO orderOperateLog = new OrderOperateLogDO();
        orderOperateLog.setOperateType(OrderOperateTypeEnum.NEW_ORDER.getCode());
        orderOperateLog.setOrderNo(createOrder.getOrderNo());
        orderOperateLog.setPreStatus(OrderStatusEnum.NULL.getCode());
        orderOperateLog.setCurrentStatus(OrderStatusEnum.CREATED.getCode());
        orderOperateLog.setGmtCreate(new Date());
        orderOperateLog.setGmtModified(new Date());
        fullOrderData.setOrderOperateLog(orderOperateLog);
        return this;
    }

    public FullOrderData build() {
        return fullOrderData;
    }

}
