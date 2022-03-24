package com.phoenix.nirvana.web.config.swagger;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties("swagger")
public class SwaggerProperties {

    private String title;
    private String description;
    private String version;
    private String basePackage;

}
