package com.phoenix.nirvana.service.system.rpc.auth.login.domain.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@Accessors(chain = true)
@ApiModel("平台登录返回数据")
public class AuthenticationUserVO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "用户id")
    private Long id;

    /**
     * 用户名称
     */
    private String userName;

    /**
     * 用户密码
     */
    private String password;

    /**
     * 当前用户是否可用
     */
    private Boolean enable;

    @ApiModelProperty(value = "验证token信息")
    private String token;

}
