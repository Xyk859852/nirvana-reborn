package com.phoenix.nirvana.service.system.rpc.auth.login.domain.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@Accessors(chain = true)
@ApiModel("平台登录用户按钮")
public class AuthenticationPermissionButtonVO implements Serializable {

    private static final long serialVersionUID=1L;

    @ApiModelProperty("按钮唯一标示")
    private String action;

    @ApiModelProperty("按钮名称")
    private String describe;

    @ApiModelProperty("是否选中")
    private Boolean defaultCheck;
}
