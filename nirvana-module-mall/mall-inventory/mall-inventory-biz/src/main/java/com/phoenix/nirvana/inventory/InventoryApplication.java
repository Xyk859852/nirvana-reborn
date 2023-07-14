package com.phoenix.nirvana.inventory;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.bus.jackson.RemoteApplicationEventScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * 库存服务
 *
 * @Author: xuyongkang
 * @Date 2023/7/14 14:34
 */
@RemoteApplicationEventScan(basePackages = "com.phoenix.nirvana.inventory.mq")
@EnableDubbo(scanBasePackages = "com.phoenix.nirvana.inventory.rpc")
@EnableDiscoveryClient
@SpringBootApplication(scanBasePackages = "com.phoenix.nirvana")
public class InventoryApplication {

    public static void main(String[] args) {
        SpringApplication.run(InventoryApplication.class, args);
    }
}
