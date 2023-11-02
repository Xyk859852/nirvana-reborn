package com.phoenix.nirvana.order.statemachine.order.create.node;

import com.phoenix.nirvana.market.domain.dto.CalculateOrderAmountDTO;
import com.phoenix.nirvana.market.domain.vo.CalculateOrderAmountVO;
import com.phoenix.nirvana.order.convert.order.ProductOrderConvert;
import com.phoenix.nirvana.order.remote.rpc.MarketRpcRemote;
import com.phoenix.nirvana.order.remote.rpc.ProductSkuInfoRpcRemote;
import com.phoenix.nirvana.order.remote.rpc.ProductSpuInfoRpcRemote;
import com.phoenix.nirvana.order.rpc.domain.dto.CreateOrderDTO;
import com.phoenix.nirvana.process.core.process.ProcessContext;
import com.phoenix.nirvana.process.core.process.StandardProcessor;
import com.phoenix.nirvana.product.rpc.sku.domain.dto.ListProductSkuDTO;
import com.phoenix.nirvana.product.rpc.sku.domain.vo.ProductSkuInfoVO;
import com.phoenix.nirvana.product.rpc.spu.domain.vo.ProductSpuInfoVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * 创建新订单金额计算
 *
 * @Author: xuyongkang
 * @Date 2023/7/16 09:31
 */
@Slf4j
@Component
public class CreateOrderCalculateAmountNode extends StandardProcessor {


    @Autowired
    ProductSkuInfoRpcRemote productSkuInfoRpcRemote;

    @Autowired
    MarketRpcRemote marketRpcRemote;

    @Autowired
    ProductSpuInfoRpcRemote productSpuInfoRpcRemote;


    @Override
    protected void processInternal(ProcessContext context) {
        log.info("订单金额开始计算...");
        CreateOrderDTO createOrder = context.get("createOrderDTO");
        List<CreateOrderDTO.OrderItemDTO> orderItemList = createOrder.getOrderItemList();
        List<String> skuCodes = orderItemList.stream().map(CreateOrderDTO.OrderItemDTO::getSkuCode).collect(Collectors.toList());
        List<ProductSkuInfoVO> productSkuList = productSkuInfoRpcRemote.listProductSku(new ListProductSkuDTO().setSkuCodes(skuCodes));
        Set<Long> productIds = productSkuList.stream().map(ProductSkuInfoVO::getProductId).collect(Collectors.toSet());
        List<ProductSpuInfoVO> productSpuInfoList = productSpuInfoRpcRemote.listProductSpuByIds(productIds);
        Map<Long, ProductSpuInfoVO> productSpuInfoMap = productSpuInfoList.stream().collect(Collectors.toMap(ProductSpuInfoVO::getProductId, Function.identity()));
        Map<String, ProductSkuInfoVO> skuCodeMap = productSkuList.stream().collect(Collectors.toMap(ProductSkuInfoVO::getSkuCode, Function.identity()));
        CalculateOrderAmountDTO calculateOrderAmount = ProductOrderConvert.INSTANCE.convert2Market(createOrder);
        calculateOrderAmount.getOrderItemList().forEach(orderItem -> {
            orderItem.setSalePrice(skuCodeMap.get(orderItem.getSkuCode()).getNewPrice());
            orderItem.setPackPrice(productSpuInfoMap.get(orderItem.getProductId()).getPackFee());
        });
        CalculateOrderAmountVO calculateOrderAmountVo = marketRpcRemote.calculateOrderAmount(calculateOrderAmount);
        context.set("productSkuList", productSkuList);
        context.set("calculateOrderAmount", calculateOrderAmountVo);

    }
}
