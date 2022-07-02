package com.phoenix.nirvana.service.system.rpc.role.domain.dto;

import com.phoenix.nirvana.service.system.rpc.role.domain.base.RoleBase;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

@Data
@ApiModel("新增角色对象")
@Accessors(chain = true)
public class AddRoleDTO extends RoleBase implements Serializable {

    /**
     * 创建者，目前使用 SysUser 的 id 编号
     *
     */
    private Long creator;

    /**
     * 更新者，目前使用 SysUser 的 id 编号
     *
     */
    private Long updater;
}
