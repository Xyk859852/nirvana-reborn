package com.phoenix.nirvana.gateway.filter.security;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * 登录用户信息
 * <p>
 * copy from yudao-spring-boot-starter-security 的 LoginUser 类
 *
 * @author xuyongkang
 */
@Data
@Accessors(chain = true)
public class LoginUser {

    /**
     * 用户编号
     */
    private Long id;

    /**
     * 租户编号
     */
    private Long tenantId;


}
