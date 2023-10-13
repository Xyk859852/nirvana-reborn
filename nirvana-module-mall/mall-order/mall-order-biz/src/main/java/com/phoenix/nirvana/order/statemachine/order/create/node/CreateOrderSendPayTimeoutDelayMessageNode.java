package com.phoenix.nirvana.order.statemachine.order.create.node;

import com.phoenix.nirvana.core.DefaultProducer;
import com.phoenix.nirvana.process.core.process.ProcessContext;
import com.phoenix.nirvana.process.core.process.StandardProcessor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 创建订单超时消息发送
 *
 * @Author: xuyongkang
 * @Date 2023/8/1 11:17
 */
@Slf4j
@Component
public class CreateOrderSendPayTimeoutDelayMessageNode extends StandardProcessor {

    @Autowired
    DefaultProducer defaultProducer;

    @Override
    protected void processInternal(ProcessContext context) {
        log.info("send rocket mq");
    }
}
