package com.phoenix.nirvana.service.system.rpc.auth.login.domain.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

@Data
@Accessors(chain = true)
@ApiModel("平台登录用户菜单资源")
public class AuthenticationRolePermissionMenuVO implements Serializable {

    private static final long serialVersionUID=1L;

    @ApiModelProperty("菜单资源code唯一标示")
    private String permissionId;

    @ApiModelProperty("菜单资源名称")
    private String permissionName;

    @ApiModelProperty("菜单功能标示")
    private List<AuthenticationPermissionButtonVO> actions;
}
