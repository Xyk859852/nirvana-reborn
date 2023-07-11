package com.phoenix.nirvana.order.mq.customer;

import com.alibaba.cloud.stream.binder.rocketmq.support.RocketMQMessageConverterSupport;
import com.phoenix.nirvana.order.mq.message.OrderStdChangeMessage;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.common.message.MessageConst;
import org.springframework.context.annotation.Bean;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

import java.util.function.Consumer;

/**
 * 订单支付成功消息监听
 *
 * @Author: xuyongkang
 * @Date 2023/7/11 17:37
 */
@Slf4j
@Component
public class OrderStdChangeEventCustomer {

    @Bean
    public Consumer<Message<OrderStdChangeMessage>> orderStdChangeEvent() {
        return msg -> {
            String tagHeaderKey = RocketMQMessageConverterSupport.toRocketHeaderKey(
                    MessageConst.PROPERTY_TAGS).toString();
            log.info(Thread.currentThread().getName() + " Receive New Messages: " + msg.getPayload() + " TAG:" +
                    msg.getHeaders().get(tagHeaderKey).toString());
        };
    }

}
