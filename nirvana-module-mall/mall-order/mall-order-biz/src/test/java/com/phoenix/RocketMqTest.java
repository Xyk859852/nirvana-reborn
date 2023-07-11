package com.phoenix;

/**
 * Unit test for simple App.
 */

import com.phoenix.nirvana.core.DefaultProducer;
import com.phoenix.nirvana.order.MallOrderApplication;
import com.phoenix.nirvana.order.mq.message.OrderStdChangeMessage;
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

    @Test
    public void test() {
        for (int i = 0; i < 10; i++) {
            OrderStdChangeMessage message = new OrderStdChangeMessage().setOrderNo("12345" + i);
            defaultProducer.send("orderStdChangeEvent-out-0", message, i + "");
        }

    }
}
