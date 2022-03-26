package com.phoenix.nirvana.service.system.rpc.auth.menu.domain.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@ApiModel("修改菜单对象")
@Accessors(chain = true)
public class UpdateMenuDTO implements Serializable {

    @ApiModelProperty("父级id")
    private Long id;

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
    private String platform;

    @ApiModelProperty("图标")
    private String icon;

    @ApiModelProperty("是否使用")
    private Boolean isEnable;


}
