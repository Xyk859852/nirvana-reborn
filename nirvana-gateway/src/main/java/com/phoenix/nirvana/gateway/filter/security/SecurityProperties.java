/*
 *  Copyright 2019-2020 Zheng Jie
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package com.phoenix.nirvana.gateway.filter.security;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;

/**
 * Jwt参数配置
 *
 * @author xuyongkang
 */
@Data
@Configuration
@Validated
@RefreshScope
@ConfigurationProperties(prefix = "jwt")
public class SecurityProperties {

    /**
     * Request Headers ： Authorization
     */
    @NotNull(message = "jwt header is not null")
    private String header;

    /**
     * 令牌前缀，最后留个空格 Bearer
     */
    @NotNull(message = "jwt tokenStartWith is not null")
    private String tokenStartWith;

    /**
     * 必须使用最少88位的Base64对该令牌进行编码
     */
    @NotNull(message = "jwt base64Secret is not null")
    private String base64Secret;

    /**
     * 令牌过期时间 此处单位/毫秒
     */
    @NotNull(message = "jwt tokenValidityInSeconds is not null")
    private Long tokenValidityInSeconds;

    /**
     * 在线用户 key，根据 key 查询 redis 中在线用户的数据
     */
    @NotNull(message = "jwt onlineKey is not null")
    private String onlineKey;

    /**
     * 验证码 key
     */
    @NotNull(message = "jwt codeKey is not null")
    private String codeKey;

    /**
     * token 续期检查
     */
    @NotNull(message = "jwt detect is not null")
    private Long detect;

    /**
     * 续期时间
     */
    @NotNull(message = "jwt renew is not null")
    private Long renew;

    public String getTokenStartWith() {
        return tokenStartWith + " ";
    }
}
