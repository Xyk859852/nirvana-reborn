package com.phoenix.nirvana.service.system.rpc.auth.permission.domain.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@ApiModel("菜单列表查询对象")
@Accessors(chain = true)
public class PermissionListDTO implements Serializable {

    @ApiModelProperty("模糊查询")
    private String keyword;

    @ApiModelProperty("上级id")
    private Long pid = 0L;
}