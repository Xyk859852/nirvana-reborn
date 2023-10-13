package com.phoenix.nirvana.market;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.bus.jackson.RemoteApplicationEventScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * 营销服务
 *
 * @Author: xuyongkang
 * @Date 2023/7/17 15:55
 */
@RemoteApplicationEventScan(basePackages = "com.phoenix.nirvana.market.mq")
@EnableDubbo(scanBasePackages = "com.phoenix.nirvana.market.rpc")
@EnableDiscoveryClient
@SpringBootApplication(scanBasePackages = "com.phoenix.nirvana")
public class MarketApplication {

    public static void main(String[] args) {
        SpringApplication.run(MarketApplication.class, args);
    }
}
