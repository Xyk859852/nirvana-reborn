package com.phoenix.nirvana.web.system;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * 管理平台端服务接口暴露
 */
@EnableDubbo
@EnableDiscoveryClient
@SpringBootApplication(scanBasePackages = "com.phoenix.nirvana")
public class SystemWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(SystemWebApplication.class, args);
    }
}
