package com.phoenix.nirvana.admin.security.config;

import com.phoenix.nirvana.admin.security.core.bean.SecurityProperties;
import org.springframework.core.Ordered;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;

import javax.annotation.Resource;

/**
 * 自定义的 URL 的安全配置
 * 目的：每个 Maven Module 可以自定义规则！
 *
 * @author xuyongkang
 */
public abstract class AuthorizeRequestsCustomizer
        implements Customizer<ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry>, Ordered {

    @Resource
    private SecurityProperties securityProperties;


    @Override
    public int getOrder() {
        return 0;
    }

}
