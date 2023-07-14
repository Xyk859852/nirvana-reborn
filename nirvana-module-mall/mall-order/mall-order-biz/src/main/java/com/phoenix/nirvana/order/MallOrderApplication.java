package com.phoenix.nirvana.order;


import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.bus.jackson.RemoteApplicationEventScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * mall order server
 *
 * @Description: 商城里面对应订单服务，下订单，订单取消，订单查询等
 * @Author: xuyongkang
 * @Date 2023/6/29 14:21
 */
//@EnableProcessEngine("nirvana-reborn-order-process.xml")
@RemoteApplicationEventScan(basePackages = "com.phoenix.nirvana.order.mq")
@EnableDubbo(scanBasePackages = "com.phoenix.nirvana.order.rpc")
@EnableDiscoveryClient
@SpringBootApplication(scanBasePackages = "com.phoenix.nirvana")
public class MallOrderApplication {

    public static void main(String[] args) {
        SpringApplication.run(MallOrderApplication.class, args);
    }

}
