package com.phoenix.nirvana.admin.web;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableDubbo
@SpringBootApplication
@MapperScan(basePackages = {"com.phoenix.nirvana.admin.web.mapper"})
@EnableTransactionManagement(proxyTargetClass = true)
public class AdminWebServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(AdminWebServiceApplication.class, args);
    }
}

