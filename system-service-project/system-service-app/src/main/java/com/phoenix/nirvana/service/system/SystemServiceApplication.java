package com.phoenix.nirvana.service.system;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * 管理平台服务
 */
@EnableDubbo(scanBasePackages = "com.phoenix.nirvana.service.system.rpc")
@EnableDiscoveryClient
@SpringBootApplication(scanBasePackages = "com.phoenix.nirvana")
public class SystemServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(SystemServiceApplication.class, args);
    }
}
