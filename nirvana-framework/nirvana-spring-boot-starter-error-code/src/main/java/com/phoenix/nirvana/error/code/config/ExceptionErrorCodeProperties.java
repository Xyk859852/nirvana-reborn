//package com.phoenix.nirvana.error.code.config;
//
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.validation.annotation.Validated;
//
//import javax.validation.constraints.NotNull;
//
//@Configuration
//@Validated
//@ConfigurationProperties(prefix = "nirvana.error-code")
//public class ExceptionErrorCodeProperties {
//
//    @NotNull(message = "应用分组不能为空，请设置 nirvana.error-code.group 配置项，推荐直接使用 spring.application.name 配置项")
//    private String group;
//
//    private String loadingClass;
//
//
//    public String getGroup() {
//        return group;
//    }
//
//    public void setGroup(String group) {
//        this.group = group;
//    }
//
//    public String getLoadingClass() {
//        return loadingClass;
//    }
//
//    public void setLoadingClass(String loadingClass) {
//        this.loadingClass = loadingClass;
//    }
//}
