package com.phoenix.nirvana.service.system.rpc.auth.menu.domain.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

@Data
@ApiModel("菜单列表查询对象")
@Accessors(chain = true)
public class MenuListItemVO implements Serializable {

    @ApiModelProperty("主键id")
    private Long id;

    @ApiModelProperty("上级ID")
    private Long pid;

    @ApiModelProperty("菜单标题")
    private String title;

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
    private Integer platform;

    @ApiModelProperty("图标")
    private String icon;

    @ApiModelProperty("子节点数量")
    private Integer subCount;

    @ApiModelProperty("描述")
    private String description;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @ApiModelProperty("创建时间")
    private Date createTime;

    @ApiModelProperty("是否使用")
    private Boolean isEnable;

    public Boolean getHasChildren() {
        return subCount > 0;
    }

    public Boolean getLeaf() {
        return subCount <= 0;
    }

    public String getLabel() {
        return title;
    }


}
