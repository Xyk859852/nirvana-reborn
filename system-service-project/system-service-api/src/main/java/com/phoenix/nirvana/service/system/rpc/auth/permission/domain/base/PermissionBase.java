package com.phoenix.nirvana.service.system.rpc.auth.permission.domain.base;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class PermissionBase {

    @ApiModelProperty("菜单标题")
    private String title;

    @ApiModelProperty("父级id")
    private Long pid;

    @ApiModelProperty("类型 0、菜单 1、功能")
    private Integer type;

    @ApiModelProperty("排序")
    private Integer sort;

    @ApiModelProperty("地址")
    private String url;

    @ApiModelProperty("是否外链")
    private Boolean iFrame;

    @ApiModelProperty("权限编码")
    private String permCode;

    @ApiModelProperty("0、管理平台1、App")
    private Integer platForm;

    @ApiModelProperty("图标")
    private String icon;

    @ApiModelProperty("是否使用")
    private Boolean enable;
}
