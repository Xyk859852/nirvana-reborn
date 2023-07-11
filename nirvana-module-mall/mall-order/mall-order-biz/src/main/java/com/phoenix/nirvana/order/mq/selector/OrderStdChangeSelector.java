package com.phoenix.nirvana.order.mq.selector;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.producer.MessageQueueSelector;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageConst;
import org.apache.rocketmq.common.message.MessageQueue;
import org.springframework.messaging.MessageHeaders;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 订单状态消息选择器
 *
 * @Author: xuyongkang
 * @Date 2023/7/11 16:56
 */
@Slf4j
@Component
public class OrderStdChangeSelector implements MessageQueueSelector {

    @Override
    public MessageQueue select(List<MessageQueue> mqs, Message msg, Object arg) {
        String id = (String) ((MessageHeaders) arg).get(MessageConst.PROPERTY_ORIGIN_MESSAGE_ID);
        String orderId = (String) ((MessageHeaders) arg).get(MessageConst.PROPERTY_TAGS);
        long index = hash(orderId) % mqs.size(); // 通过一个订单id对应的消息都发送到mq queue里去
        log.info("OrderStdChangeSelector: id:{},orderId:{}", id, orderId);
        return mqs.get((int) index);
    }

    /**
     * hash
     *
     * @param orderId 订单id
     * @return hash后的值
     */
    private int hash(String orderId) {
        //解决取模可能为负数的情况
        return orderId.hashCode() & Integer.MAX_VALUE;
    }
}
