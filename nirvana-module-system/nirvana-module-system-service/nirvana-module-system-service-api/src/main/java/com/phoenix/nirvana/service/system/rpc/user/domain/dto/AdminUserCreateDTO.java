package com.phoenix.nirvana.service.system.rpc.user.domain.dto;

import com.phoenix.nirvana.service.system.rpc.user.domain.base.AdminUserBase;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@ApiModel("创建管理员用户")
@Accessors(chain = true)
public class AdminUserCreateDTO extends AdminUserBase implements Serializable {

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
