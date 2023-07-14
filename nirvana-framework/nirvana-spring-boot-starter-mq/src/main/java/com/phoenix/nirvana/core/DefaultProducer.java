package com.phoenix.nirvana.core;

import cn.hutool.core.lang.UUID;
import org.apache.rocketmq.common.message.MessageConst;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description: rocket 消息推送工具类
 * @Author: xuyongkang
 * @Date 2023/7/11 11:25
 */
@Component
public class DefaultProducer {

    @Resource
    private StreamBridge streamBridge;


    /**
     * 普通消息
     *
     * @param topic   主题
     * @param message 消息
     * @return
     */
    public boolean send(String topic, Object message) {
        return send(topic, message, "");
    }

    /**
     * 普通消息
     *
     * @param topic   主题
     * @param message 消息
     * @return
     */
    public boolean send(String topic, Object message, String tag) {
        Map<String, Object> headers = new HashMap<>();
        headers.put(MessageConst.PROPERTY_TAGS, tag);
        headers.put(MessageConst.PROPERTY_ORIGIN_MESSAGE_ID, UUID.fastUUID().toString(true));
        return send(topic, message, headers);
    }

    /**
     * 延迟消息
     *
     * @param topic   主题
     * @param message 消息
     * @param del     延迟级别 {@link RocketDelayedLevel}
     * @return
     */
    public boolean send(String topic, Object message, Integer del) {
        return send(topic, message, del, "");
    }

    /**
     * 延迟消息
     *
     * @param topic   主题
     * @param message 消息
     * @param del     延迟级别 {@link RocketDelayedLevel}
     * @return
     */
    public boolean send(String topic, Object message, Integer del, String tag) {
        Map<String, Object> headers = new HashMap<>();
        headers.put(MessageConst.PROPERTY_DELAY_TIME_LEVEL, del);
        headers.put(MessageConst.PROPERTY_TAGS, tag);
        headers.put(MessageConst.PROPERTY_ORIGIN_MESSAGE_ID, UUID.fastUUID().toString(true));
        return send(topic, message, headers);
    }

    /**
     * 延迟消息
     *
     * @param topic   主题
     * @param message 消息
     * @param headers 自定义rocketmq headers
     * @return
     */
    public boolean send(String topic, Object message, Map<String, Object> headers) {
        Message<Object> msg = new GenericMessage(message, headers);
        return streamBridge.send(topic, msg);
    }
}
