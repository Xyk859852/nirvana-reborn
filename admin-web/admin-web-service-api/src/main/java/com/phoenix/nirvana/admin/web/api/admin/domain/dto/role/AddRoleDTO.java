package com.phoenix.nirvana.admin.web.api.admin.domain.dto.role;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

@Data
@ApiModel("新增角色对象")
@Accessors(chain = true)
public class AddRoleDTO implements Serializable {

    @ApiModelProperty("角色名称")
    private String name;

    @ApiModelProperty("角色排序")
    private Integer sort;

    @ApiModelProperty("是否禁用，0使用，1禁用")
    private Boolean isEnable;

    @ApiModelProperty("描述")
    private String description;

    @ApiModelProperty("对应菜单集合")
    private List<Long> menus;
}
