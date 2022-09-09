package com.phoenix.nirvana.service.system.rpc.auth.login.domain.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@Accessors(chain = true)
@ApiModel("平台用户详细信息")
public class AuthenticationUserInfoVO implements Serializable {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "用户id")
    private Long id;

    @ApiModelProperty(value = "用户名称")
    private String name;

    @ApiModelProperty(value = "用户名称")
    private String phone;

    @ApiModelProperty(value = "头像图片")
    private String avatar;

    @ApiModelProperty(value = "用户权限")
    private AuthenticationUserRoleVO role;

    @ApiModelProperty(value = "部门Id")
    private Long deptId;

}
