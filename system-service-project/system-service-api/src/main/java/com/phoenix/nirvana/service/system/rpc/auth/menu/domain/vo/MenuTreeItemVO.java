package com.phoenix.nirvana.service.system.rpc.auth.menu.domain.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

@Data
@ApiModel("菜单树列表对象")
@Accessors(chain = true)
public class MenuTreeItemVO implements Serializable {

    @ApiModelProperty("主键id")
    private Long id;

    @ApiModelProperty("上级ID")
    private Long pid;

    @ApiModelProperty("菜单标题")
    private String title;

    @ApiModelProperty("子节点数量")
    private Integer subCount;

    @ApiModelProperty("是否使用")
    private Boolean isEnable;

    @ApiModelProperty("子集列表")
    private List<MenuTreeItemVO> children;

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
