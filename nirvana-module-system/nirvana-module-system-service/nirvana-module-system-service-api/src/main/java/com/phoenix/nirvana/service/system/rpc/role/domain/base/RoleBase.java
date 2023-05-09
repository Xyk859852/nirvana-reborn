package com.phoenix.nirvana.service.system.rpc.role.domain.base;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

@Data
@Accessors(chain = true)
public class RoleBase implements Serializable {

    @ApiModelProperty("角色名称")
    private String name;

    @ApiModelProperty("角色排序")
    private Integer sort;

    @ApiModelProperty("是否禁用，0使用，1禁用")
    private Boolean enable;

    @ApiModelProperty("描述")
    private String description;

    @ApiModelProperty("对应菜单集合")
    private List<Long> permissions;
}
