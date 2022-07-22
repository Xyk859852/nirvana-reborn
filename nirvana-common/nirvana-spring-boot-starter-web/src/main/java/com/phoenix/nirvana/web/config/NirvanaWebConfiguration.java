package com.phoenix.nirvana.web.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.phoenix.nirvana.web.core.handler.GlobalExceptionHandler;
import com.phoenix.nirvana.web.core.servlet.CorsFilter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.time.LocalDateTime;

@Slf4j
@Configuration
@ConditionalOnWebApplication(type = ConditionalOnWebApplication.Type.SERVLET)
public class NirvanaWebConfiguration implements WebMvcConfigurer {

    @Bean
    @ConditionalOnMissingBean(GlobalExceptionHandler.class)
    public GlobalExceptionHandler globalExceptionHandler() {
        return new GlobalExceptionHandler();
    }


    @Bean
    @ConditionalOnMissingBean
    public FilterRegistrationBean<CorsFilter> corsFilter() {
        FilterRegistrationBean<CorsFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new CorsFilter());
        registrationBean.addUrlPatterns("/*");
        return registrationBean;
    }

    // ========== MessageConverter 相关 ==========

//    @Override
//    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
//        // 创建 FastJsonHttpMessageConverter 对象
//        FastJsonHttpMessageConverter fastJsonHttpMessageConverter = new FastJsonHttpMessageConverter();
//        //Long类型转String类型
//        SerializeConfig serializeConfig = SerializeConfig.getGlobalInstance();
//        // ToStringSerializer 是这个包 com.alibaba.fastjson.serializer.ToStringSerializer
//        serializeConfig.put(BigInteger.class, ToStringSerializer.instance);
//        serializeConfig.put(Long.class, ToStringSerializer.instance);
//        serializeConfig.put(Long.TYPE, ToStringSerializer.instance);
//        serializeConfig.put(Date.class, new SimpleDateFormatSerializer("yyyy-MM-dd HH:mm:ss"));
//        // 自定义 FastJson 配置
//        FastJsonConfig fastJsonConfig = new FastJsonConfig();
//        fastJsonConfig.setCharset(Charset.defaultCharset()); // 设置字符集
//        fastJsonConfig.setSerializeConfig(serializeConfig);
//        fastJsonConfig.setSerializerFeatures(SerializerFeature.DisableCircularReferenceDetect, // 剔除循环引用
//                SerializerFeature.WriteNonStringKeyAsString, SerializerFeature.WriteMapNullValue); // 解决 Integer 作为 Key 时，转换为 String 类型，避免浏览器报错
//        fastJsonHttpMessageConverter.setFastJsonConfig(fastJsonConfig);
//        // 设置支持的 MediaType
//        fastJsonHttpMessageConverter.setSupportedMediaTypes(Collections.singletonList(MediaType.APPLICATION_JSON));
//        // 添加到 converters 中
//        converters.add(0, fastJsonHttpMessageConverter); // 注意，添加到最开头，放在 MappingJackson2XmlHttpMessageConverter 前面
//    }

    @Bean
    public BeanPostProcessor objectMapperBeanPostProcessor() {
        return new BeanPostProcessor() {
            @Override
            public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
                if (!(bean instanceof ObjectMapper)) {
                    return bean;
                }
                ObjectMapper objectMapper = (ObjectMapper) bean;
                SimpleModule simpleModule = new SimpleModule();
                /*
                 * 1. 新增Long类型序列化规则，数值超过2^53-1，在JS会出现精度丢失问题，因此Long自动序列化为字符串类型
                 * 2. 新增LocalDateTime序列化、反序列化规则
                 */
                simpleModule
//                .addSerializer(Long.class, ToStringSerializer.instance)
//                    .addSerializer(Long.TYPE, ToStringSerializer.instance)
                        .addSerializer(LocalDateTime.class, LocalDateTimeSerializer.INSTANCE)
                        .addDeserializer(LocalDateTime.class, LocalDateTimeDeserializer.INSTANCE);

                objectMapper.registerModules(simpleModule);
//                JsonUtils.init(objectMapper);
                log.info("初始化 jackson 自动配置");
                return bean;
            }
        };
    }


}
