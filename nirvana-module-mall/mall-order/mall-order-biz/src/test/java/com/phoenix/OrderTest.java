package com.phoenix;

import com.phoenix.nirvana.order.MallOrderApplication;
import com.phoenix.nirvana.order.rpc.domain.dto.CreateOrderDTO;
import com.phoenix.nirvana.order.rpc.domain.dto.GenOrderIdDTO;
import com.phoenix.nirvana.order.rpc.domain.vo.OrderInfoVO;
import com.phoenix.nirvana.order.service.ProductOrderService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static com.phoenix.nirvana.common.enums.OrderAmountTypeEnum.ORIGIN_PAY_AMOUNT;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = MallOrderApplication.class)
public class OrderTest {

    @Autowired
    ProductOrderService productOrderService;

    @Test
    public void createOrderTest() {
        OrderInfoVO orderInfo = productOrderService.createOrder(createOrderRequest());
        log.info("orderInfo:{}", orderInfo);
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
