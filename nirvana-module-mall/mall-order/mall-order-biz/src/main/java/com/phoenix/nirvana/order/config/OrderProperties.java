package com.phoenix.nirvana.order.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author zhonghuashishan
 * @version 1.0
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "nirvana.order")
public class OrderProperties {

    /**
     * 订单超时支付时间限制 单位毫秒，默认30分钟
     */
    private Integer expireTime = 30 * 60 * 1000;


    public static final Integer ORDER_EXPIRE_TIME = 30 * 60 * 1000;


}