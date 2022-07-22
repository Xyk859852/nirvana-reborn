package com.phoenix.nirvana.config;

import com.alibaba.cloud.stream.binder.rocketmq.convert.RocketMQMessageConverter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.converter.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author xuyongkang
 * @version 1.0
 * @description: rocket mq 配置
 * @date 2022/7/20 2:37 PM
 */
@Configuration
public class NirvanaMQAutoConfiguration {

    /**
     * 覆盖 {@link RocketMQMessageConverter} 的配置，去掉 fastjson 的转换器，解决不兼容的问题
     */
    @Bean
    @ConditionalOnMissingBean(RocketMQMessageConverter.class)
    public CompositeMessageConverter rocketMQMessageConverter() {
        List<MessageConverter> messageConverters = new ArrayList<>();
        ByteArrayMessageConverter byteArrayMessageConverter = new ByteArrayMessageConverter();
        byteArrayMessageConverter.setContentTypeResolver(null);
        messageConverters.add(byteArrayMessageConverter);
        messageConverters.add(new StringMessageConverter());
        messageConverters.add(new MappingJackson2MessageConverter());
        return new CompositeMessageConverter(messageConverters);
    }
}
