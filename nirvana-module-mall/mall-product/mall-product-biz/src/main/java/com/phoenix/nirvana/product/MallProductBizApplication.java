package com.phoenix.nirvana.product;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.bus.jackson.RemoteApplicationEventScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * mall product server
 *
 * @Description: 商城里面对应商品服务，包含品牌，分类等
 * @Author: xuyongkang
 * @Date 2023/5/10 11:48 AM
 */
@RemoteApplicationEventScan(basePackages = "com.phoenix.nirvana.product.mq")
@EnableDubbo(scanBasePackages = "com.phoenix.nirvana.product.rpc")
@EnableDiscoveryClient
@SpringBootApplication(scanBasePackages = "com.phoenix.nirvana")
public class MallProductBizApplication {

    public static void main(String[] args) {
        SpringApplication.run(MallProductBizApplication.class, args);
    }
}
