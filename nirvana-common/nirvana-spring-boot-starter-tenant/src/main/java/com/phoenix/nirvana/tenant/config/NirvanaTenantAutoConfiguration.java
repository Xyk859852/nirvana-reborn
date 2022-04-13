package com.phoenix.nirvana.tenant.config;

import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.TenantLineInnerInterceptor;
import com.phoenix.nirvana.mybatis.core.util.MyBatisUtils;
import com.phoenix.nirvana.tenant.core.aop.TenantAspect;
import com.phoenix.nirvana.tenant.core.db.TenantDatabaseInterceptor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnProperty(prefix = "nirvana.tenant", value = "enable", matchIfMissing = true) // 允许使用 yudao.tenant.enable=false 禁用多租户
@EnableConfigurationProperties(TenantProperties.class)
public class NirvanaTenantAutoConfiguration {

    // ========== AOP ==========

    @Bean
    public TenantAspect tenantIgnoreAspect() {
        return new TenantAspect();
    }

    // ========== DB ==========

    @Bean
    public TenantLineInnerInterceptor tenantLineInnerInterceptor(TenantProperties properties,
                                                                 MybatisPlusInterceptor interceptor) {
        TenantLineInnerInterceptor inner = new TenantLineInnerInterceptor(new TenantDatabaseInterceptor(properties));
        // 添加到 interceptor 中
        // 需要加在首个，主要是为了在分页插件前面。这个是 MyBatis Plus 的规定
        MyBatisUtils.addInterceptor(interceptor, inner, 0);
        return inner;
    }
    
//
//    // ========== MQ ==========
//
//    @Bean
//    public TenantRedisMessageInterceptor tenantRedisMessageInterceptor() {
//        return new TenantRedisMessageInterceptor();
//    }
//
//    // ========== Job ==========
//
//    @Bean
//    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
//    public BeanPostProcessor jobHandlerBeanPostProcessor(TenantFrameworkService tenantFrameworkService) {
//        return new BeanPostProcessor() {
//
//            @Override
//            public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
//                if (!(bean instanceof JobHandler)) {
//                    return bean;
//                }
//                // 有 TenantJob 注解的情况下，才会进行处理
//                if (!AnnotationUtil.hasAnnotation(bean.getClass(), TenantJob.class)) {
//                    return bean;
//                }
//
//                // 使用 TenantJobHandlerDecorator 装饰
//                return new TenantJobHandlerDecorator(tenantFrameworkService, (JobHandler) bean);
//            }
//
//        };
//    }

}
