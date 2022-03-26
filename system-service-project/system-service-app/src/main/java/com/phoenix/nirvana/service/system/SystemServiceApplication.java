package com.phoenix.nirvana.service.system;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * 管理平台服务
 */
@EnableDubbo
@EnableDiscoveryClient
@MapperScan(value = "com.phoenix.nirvana.service.system.dal.mysql.mapper")// 扫描对应的 Mapper 接口
@SpringBootApplication(scanBasePackages = "com.phoenix.nirvana")
public class SystemServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(SystemServiceApplication.class, args);
    }
}
