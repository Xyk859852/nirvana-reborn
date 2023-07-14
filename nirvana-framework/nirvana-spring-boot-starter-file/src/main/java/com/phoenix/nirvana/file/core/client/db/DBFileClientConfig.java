package com.phoenix.nirvana.file.core.client.db;

import com.phoenix.nirvana.file.core.client.FileClientConfig;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.URL;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotEmpty;

/**
 * 基于 DB 存储的文件客户端的配置类
 *
 * @author xuyongkang
 */
@Data
@Validated
@RefreshScope
@Configuration
@EqualsAndHashCode
@ConfigurationProperties(prefix = "nirvana.file.db")
@ConditionalOnProperty(prefix = "nirvana.file.db", name = "enable", havingValue = "true")
public class DBFileClientConfig implements FileClientConfig {

    /**
     * 自定义域名
     */
    @NotEmpty(message = "domain 不能为空")
    @URL(message = "domain 必须是 URL 格式")
    private String domain;

}
