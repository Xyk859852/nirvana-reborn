package com.phoenix.nirvana.order.statemachine.order.create.node;

import com.phoenix.nirvana.common.exception.util.ServiceExceptionUtil;
import com.phoenix.nirvana.common.util.CollectionUtils;
import com.phoenix.nirvana.order.enums.OrderAmountTypeEnum;
import com.phoenix.nirvana.order.rpc.order.domain.dto.CreateOrderDTO;
import com.phoenix.nirvana.process.core.process.ProcessContext;
import com.phoenix.nirvana.process.core.process.StandardProcessor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

import static com.phoenix.nirvana.order.enums.OrderErrorCodeConstants.*;

/**
 * 创建订单校验参数
 *
 * @Author: xuyongkang
 * @Date 2023/7/13 14:58
 */
@Component
public class CreateOrderCheckNode extends StandardProcessor {

    @Override
    protected void processInternal(ProcessContext context) {
        CreateOrderDTO createOrder = context.get("createOrderDTO");
        //判断下单条目不能为空
        if (CollectionUtils.isAnyEmpty(createOrder.getOrderItemList())) {
            throw ServiceExceptionUtil.exception(ORDER_ITEM_NULL);
        }
        //判断订单条目中销售数量与销售价格是否异常
        CollectionUtils.filterList(createOrder.getOrderItemList(), (orderItem -> {
            if (orderItem.getSaleQuantity() <= 0) {
                throw ServiceExceptionUtil.exception(ORDER_ITEM_SALE_QUANTITY_ERROR);
            }
            if (orderItem.getSalePrice() < 0) {
                throw ServiceExceptionUtil.exception(ORDER_ITEM_SALE_PRICE_ERROR);
            }
            return true;
        }));
        //判断订单费用是否异常
        CollectionUtils.filterList(createOrder.getOrderAmountList(), (orderAmount -> {
            if (OrderAmountTypeEnum.getByCode(orderAmount.getAmountType()) == null) {
                throw ServiceExceptionUtil.exception(ORDER_AMOUNT_TYPE_PARAM_ERROR);
            }
            if (orderAmount.getAmount() < 0) {
                throw ServiceExceptionUtil.exception(ORDER_ITEM_AMOUNT_ERROR);
            }
            return true;
        }));
        //判断当有优惠劵时，订单支付金额也必须包含
        if (!CollectionUtils.isAnyEmpty(createOrder.getOrderCouponList())) {
            List<CreateOrderDTO.OrderAmountDTO> couponAmontList = createOrder.getOrderAmountList().stream()
                    .filter(orderAmount -> orderAmount.getAmountType().equals(OrderAmountTypeEnum.COUPON_DISCOUNT_AMOUNT.getCode())).collect(Collectors.toList());
            if (CollectionUtils.isAnyEmpty(couponAmontList)) {
                throw ServiceExceptionUtil.exception(ORDER_DISCOUNT_AMOUNT_IS_NULL);
            }
        }

    }
}
