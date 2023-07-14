package com.phoenix.nirvana.service.system.rpc.auth.permission.domain.dto;

import com.phoenix.nirvana.service.system.rpc.auth.permission.domain.base.PermissionBase;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@ApiModel("新增菜单对象")
@Accessors(chain = true)
public class AddPermissionDTO extends PermissionBase implements Serializable {


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
