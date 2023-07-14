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
@ApiModel("用户管理员修改对象")
@Accessors(chain = true)
public class AdminUserUpdateDTO extends AdminUserBase implements Serializable {

    @ApiModelProperty("主键id")
    @NotNull(message = "用户id不能为空")
    private Long id;

    /**
     * 更新者，目前使用 SysUser 的 id 编号
     *
     */
    private Long updater;
}
