package com.phoenix.nirvana.order.controller.order;

import com.phoenix.nirvana.common.vo.CommonResult;
import com.phoenix.nirvana.order.rpc.domain.dto.CreateOrderDTO;
import com.phoenix.nirvana.order.rpc.domain.dto.GenOrderIdDTO;
import com.phoenix.nirvana.order.rpc.domain.vo.OrderInfoVO;
import com.phoenix.nirvana.order.service.ProductOrderService;
import lombok.extern.slf4j.Slf4j;
import org.apache.skywalking.apm.toolkit.trace.Trace;
import org.apache.skywalking.apm.toolkit.trace.TraceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static com.phoenix.nirvana.common.enums.OrderAmountTypeEnum.ORIGIN_PAY_AMOUNT;

/**
 * <p>
 * 商品订单表 前端控制器
 * </p>
 *
 * @author xuyongkang
 * @since 2023-06-29
 */
@Slf4j
@RestController
@RequestMapping("/productOrder")
public class ProductOrderController {

    @Autowired
    ProductOrderService productOrderService;


    @Trace
    @GetMapping("test")
    public CommonResult create() {
        String traceId = TraceContext.traceId();
        log.info("traceId:{}", traceId);
        OrderInfoVO orderInfo = productOrderService.createOrder(createOrderRequest());
        return CommonResult.success(orderInfo);
    }

    public CreateOrderDTO createOrderRequest() {
        CreateOrderDTO createOrder = new CreateOrderDTO();
        createOrder.setOrderNo(productOrderService.genOrderId(new GenOrderIdDTO().setUserId("123456").setBusinessIdentifier(10)).getOrderNo());
        createOrder.setOrderType(0);
        createOrder.setOrderSourceChannel(0);
        createOrder.setOrderPayType(10);
        createOrder.setShopId(7l);
        createOrder.setUserId("123456");
        createOrder.setAddressId(2l);
        List<CreateOrderDTO.OrderItemDTO> orderItemList = new ArrayList<>();
        orderItemList.add(new CreateOrderDTO.OrderItemDTO().setProductId(6l).setSkuCode("6_7_8").setSaleQuantity(1));
        orderItemList.add(new CreateOrderDTO.OrderItemDTO().setProductId(6l).setSkuCode("6_7_7").setSaleQuantity(1));
        createOrder.setOrderItemList(orderItemList);
        List<CreateOrderDTO.OrderAmountDTO> orderAmountList = new ArrayList<>();
        orderAmountList.add(new CreateOrderDTO.OrderAmountDTO()
                .setAmountType(ORIGIN_PAY_AMOUNT.getCode())
                .setAmount(BigDecimal.valueOf(36000).movePointRight(4).intValue()));
        createOrder.setOrderAmountList(orderAmountList);
        return createOrder;
    }
}
