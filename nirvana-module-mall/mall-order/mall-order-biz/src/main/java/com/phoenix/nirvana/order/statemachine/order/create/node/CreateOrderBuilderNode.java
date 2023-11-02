package com.phoenix.nirvana.order.statemachine.order.create.node;

import com.phoenix.nirvana.market.domain.vo.CalculateOrderAmountVO;
import com.phoenix.nirvana.order.builder.FullOrderData;
import com.phoenix.nirvana.order.builder.NewOrderBuilder;
import com.phoenix.nirvana.order.config.OrderProperties;
import com.phoenix.nirvana.order.rpc.domain.dto.CreateOrderDTO;
import com.phoenix.nirvana.process.core.process.ProcessContext;
import com.phoenix.nirvana.process.core.process.StandardProcessor;
import com.phoenix.nirvana.product.rpc.sku.domain.vo.ProductSkuInfoVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 构建创建订单落库数据
 *
 * @Author: xuyongkang
 * @Date 2023/7/19 15:41
 */
@Slf4j
@Component
public class CreateOrderBuilderNode extends StandardProcessor {

    @Autowired
    OrderProperties orderProperties;

    @Override
    protected void processInternal(ProcessContext context) {
        log.info("构建订单落库数据...");
        CreateOrderDTO createOrder = context.get("createOrderDTO");
        List<ProductSkuInfoVO> productSkuList = context.get("productSkuList");
        CalculateOrderAmountVO calculateOrderAmountVo = context.get("calculateOrderAmount");

        NewOrderBuilder newOrderBuilder = new NewOrderBuilder(createOrder, productSkuList,
                calculateOrderAmountVo, orderProperties, new FullOrderData());
        FullOrderData fullOrderData = newOrderBuilder
                .buildOrder()
                .buildOrderItem()
                .buildOrderDiscount()
                .buildOrderOperateLog()
                .build();
        context.set("fullOrderData", fullOrderData);

    }
}
