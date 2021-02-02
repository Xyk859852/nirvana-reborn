package com.phoenix.nirvana.admin.web.impl;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDubbo
@EnableDiscoveryClient
@MapperScan(value = "com.phoenix.nirvana.admin.web.impl.mapper")// 扫描对应的 Mapper 接口
@SpringBootApplication(scanBasePackages = "com.phoenix.nirvana")
public class AdminWebServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(AdminWebServiceApplication.class, args);
    }
}

