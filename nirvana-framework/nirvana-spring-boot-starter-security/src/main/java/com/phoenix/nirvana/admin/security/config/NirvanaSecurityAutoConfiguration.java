package com.phoenix.nirvana.admin.security.config;

import com.phoenix.nirvana.admin.security.core.bean.SecurityProperties;
import com.phoenix.nirvana.admin.security.core.context.TransmittableThreadLocalSecurityContextHolderStrategy;
import com.phoenix.nirvana.admin.security.core.filter.TokenAuthenticationFilter;
import com.phoenix.nirvana.admin.security.core.handler.AccessDeniedHandlerImpl;
import com.phoenix.nirvana.admin.security.core.handler.AuthenticationEntryPointImpl;
import com.phoenix.nirvana.admin.security.core.service.SecurityFrameworkService;
import com.phoenix.nirvana.admin.security.core.service.SecurityFrameworkServiceImpl;
import com.phoenix.nirvana.service.system.rpc.admin.OAuth2TokenApi;
import com.phoenix.nirvana.service.system.rpc.auth.permission.SysPermissionRpc;
import com.phoenix.nirvana.web.core.handler.GlobalExceptionHandler;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.factory.config.MethodInvokingFactoryBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.annotation.Resource;

/**
 * Spring Security 自动配置类，主要用于相关组件的配置
 * <p>
 * 注意，不能和 {@link NirvanaSecurityWebAutoConfiguration} 用一个，原因是会导致初始化报错。
 * 参见 https://stackoverflow.com/questions/53847050/spring-boot-delegatebuilder-cannot-be-null-on-autowiring-authenticationmanager 文档。
 *
 * @author xuyongkang
 */
@Configuration(proxyBeanMethods = false)
@EnableConfigurationProperties(SecurityProperties.class)
public class NirvanaSecurityAutoConfiguration {

    @Resource
    private SecurityProperties securityProperties;

    @DubboReference
    SysPermissionRpc permissionRpc;

    @DubboReference
    OAuth2TokenApi oAuth2TokenApi;

    /**
     * 认证失败处理类 Bean
     */
    @Bean
    public AuthenticationEntryPoint authenticationEntryPoint() {
        return new AuthenticationEntryPointImpl();
    }

    /**
     * 权限不够处理器 Bean
     */
    @Bean
    public AccessDeniedHandler accessDeniedHandler() {
        return new AccessDeniedHandlerImpl();
    }


    /**
     * Spring Security 加密器
     * 考虑到安全性，这里采用 BCryptPasswordEncoder 加密器
     *
     * @see <a href="http://stackabuse.com/password-encoding-with-spring-security/">Password Encoding with Spring Security</a>
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * Token 认证过滤器 Bean
     */
    @Bean
    public TokenAuthenticationFilter authenticationTokenFilter(GlobalExceptionHandler globalExceptionHandler) {
        return new TokenAuthenticationFilter(securityProperties, globalExceptionHandler, oAuth2TokenApi);
    }

    @Bean("ss") // 使用 Spring Security 的缩写，方便使用
    public SecurityFrameworkService securityFrameworkService() {
        return new SecurityFrameworkServiceImpl(permissionRpc);
    }

    /**
     * 声明调用 {@link SecurityContextHolder#setStrategyName(String)} 方法，
     * 设置使用 {@link TransmittableThreadLocalSecurityContextHolderStrategy} 作为 Security 的上下文策略
     */
    @Bean
    public MethodInvokingFactoryBean securityContextHolderMethodInvokingFactoryBean() {
        MethodInvokingFactoryBean methodInvokingFactoryBean = new MethodInvokingFactoryBean();
        methodInvokingFactoryBean.setTargetClass(SecurityContextHolder.class);
        methodInvokingFactoryBean.setTargetMethod("setStrategyName");
        methodInvokingFactoryBean.setArguments(TransmittableThreadLocalSecurityContextHolderStrategy.class.getName());
        return methodInvokingFactoryBean;
    }

}
