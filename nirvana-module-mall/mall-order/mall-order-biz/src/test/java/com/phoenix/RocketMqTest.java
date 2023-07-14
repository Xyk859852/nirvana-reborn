package com.phoenix;

/**
 * Unit test for simple App.
 */

import com.phoenix.nirvana.core.DefaultProducer;
import com.phoenix.nirvana.order.MallOrderApplication;
import com.phoenix.nirvana.order.enums.OrderNoTypeEnum;
import com.phoenix.nirvana.order.mq.message.OrderStdChangeMessage;
import com.phoenix.nirvana.order.service.generator.GenOrderNoService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE, classes = MallOrderApplication.class)
public class RocketMqTest {

    @Autowired
    DefaultProducer defaultProducer;

    @Autowired
    GenOrderNoService genOrderNoService;

    @Test
    public void test() {
        for (int i = 0; i < 10; i++) {
            OrderStdChangeMessage message = new OrderStdChangeMessage().setOrderNo("12345" + i);
            defaultProducer.send("orderStdChangeEvent-out-0", message, i + "");
        }
    }

    @Test
    public void genOrderNo() {
        String orderNo = genOrderNoService.genOrderNo(OrderNoTypeEnum.SALE_ORDER.getCode(), "123456");
        log.info("orderNo:{}", orderNo);
    }


}
