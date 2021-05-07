package com.phoenix.nirvana.web.config;

import com.alibaba.fastjson.serializer.SerializeConfig;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.serializer.ToStringSerializer;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import com.phoenix.nirvana.web.core.handler.GlobalExceptionHandler;
import com.phoenix.nirvana.web.core.servlet.CorsFilter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.math.BigInteger;
import java.nio.charset.Charset;
import java.util.Collections;
import java.util.List;

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

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        // 创建 FastJsonHttpMessageConverter 对象
        FastJsonHttpMessageConverter fastJsonHttpMessageConverter = new FastJsonHttpMessageConverter();
        //Long类型转String类型
        SerializeConfig serializeConfig = SerializeConfig.globalInstance;
        // ToStringSerializer 是这个包 com.alibaba.fastjson.serializer.ToStringSerializer
        serializeConfig.put(BigInteger.class, ToStringSerializer.instance);
        serializeConfig.put(Long.class, ToStringSerializer.instance);
        serializeConfig.put(Long.TYPE, ToStringSerializer.instance);
        // 自定义 FastJson 配置
        FastJsonConfig fastJsonConfig = new FastJsonConfig();
        fastJsonConfig.setCharset(Charset.defaultCharset()); // 设置字符集
        fastJsonConfig.setSerializeConfig(serializeConfig);
        fastJsonConfig.setSerializerFeatures(SerializerFeature.DisableCircularReferenceDetect, // 剔除循环引用
                SerializerFeature.WriteNonStringKeyAsString, SerializerFeature.WriteMapNullValue); // 解决 Integer 作为 Key 时，转换为 String 类型，避免浏览器报错
        fastJsonHttpMessageConverter.setFastJsonConfig(fastJsonConfig);
        // 设置支持的 MediaType
        fastJsonHttpMessageConverter.setSupportedMediaTypes(Collections.singletonList(MediaType.APPLICATION_JSON));
        // 添加到 converters 中
        converters.add(0, fastJsonHttpMessageConverter); // 注意，添加到最开头，放在 MappingJackson2XmlHttpMessageConverter 前面
    }


}
