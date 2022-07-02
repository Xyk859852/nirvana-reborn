package com.phoenix.nirvana.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class NirvanaGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(NirvanaGatewayApplication.class, args);
    }
}
