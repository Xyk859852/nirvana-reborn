package com.phoenix.nirvana.admin.security.config;

import com.phoenix.nirvana.admin.security.core.rpc.LoginUserRequestInterceptor;
import com.phoenix.nirvana.service.system.rpc.admin.OAuth2TokenApi;
import com.phoenix.nirvana.service.system.rpc.auth.permission.SysPermissionRpc;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Security 使用到 Feign 的配置项
 *
 * @author xuyongkang
 */
@Configuration(proxyBeanMethods = false)
//@EnableFeignClients(clients = {SysPermissionRpc.class , OAuth2TokenApi.class})
public class NirvanaSecurityRpcAutoConfiguration {

    @Bean
    public LoginUserRequestInterceptor loginUserRequestInterceptor() {
        return new LoginUserRequestInterceptor();
    }

}
