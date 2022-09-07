//package com.phoenix.nirvana.error.code.config;
//
//import com.phoenix.nirvana.error.code.loading.ExceptionErrorCodeAutoGenerator;
//import com.phoenix.nirvana.error.code.loading.ExceptionErrorCodeRemoteLoader;
//import org.springframework.boot.context.properties.EnableConfigurationProperties;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.scheduling.annotation.EnableScheduling;
//
//@Configuration
//@EnableScheduling // 开启调度任务的功能，因为 ErrorCodeRemoteLoader 通过定时刷新错误码
//@EnableConfigurationProperties(ExceptionErrorCodeProperties.class)
//public class ExceptionErrorCodeConfiguration {
//
//    @Bean
//    public ExceptionErrorCodeRemoteLoader exceptionErrorCodeRemoteLoader(ExceptionErrorCodeProperties exceptionErrorCodeProperties) {
//        return new ExceptionErrorCodeRemoteLoader(exceptionErrorCodeProperties.getGroup());
//    }
//
//    @Bean
//    public ExceptionErrorCodeAutoGenerator exceptionErrorCodeAutoGenerator(ExceptionErrorCodeProperties exceptionErrorCodeProperties) {
//        return new ExceptionErrorCodeAutoGenerator(exceptionErrorCodeProperties.getGroup()).setErrorCodeConstantsClass(exceptionErrorCodeProperties.getLoadingClass());
//    }
//
//
//
//}
