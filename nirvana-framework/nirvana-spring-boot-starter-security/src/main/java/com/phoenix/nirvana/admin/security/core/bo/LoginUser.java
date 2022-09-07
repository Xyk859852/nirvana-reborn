package com.phoenix.nirvana.admin.security.core.bo;

import com.phoenix.nirvana.common.enums.UserTypeEnum;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(chain = true)
public class LoginUser {

    /**
     * 用户id
     */
    private Long id;

    /**
     * 用户名称
     */
    private String userName;

    /**
     * 用户类型
     *
     * 关联 {@link UserTypeEnum}
     */
    private Integer userType;

    /**
     * 用户密码
     */
    private String password;

    /**
     * 当前用户是否可用
     */
    private Boolean enable;

    /**
     * 授权范围
     */
    private List<String> scopes;
}
