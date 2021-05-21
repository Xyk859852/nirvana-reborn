package com.phoenix.nirvana.admin.web.api.admin.domain.vo.role;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@ApiModel("角色列表返回")
@Accessors(chain = true)
public class RolePageItemVO implements Serializable {

    @ApiModelProperty("id")
    private Long id;

    @ApiModelProperty("角色名称")
    private String name;

    @ApiModelProperty("排序")
    private String sort;

    @ApiModelProperty("是否禁用")
    private Boolean isEnable;

    @ApiModelProperty("是否禁用")
    private String description;

    @ApiModelProperty("创建时间")
    private Date createTime;

    @ApiModelProperty("菜单资源id集合")
    private List<Long> menuIds = new ArrayList<>();
}
