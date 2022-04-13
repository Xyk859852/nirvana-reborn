package com.phoenix.nirvana.service.system.rpc.admin.domain.dto.user;

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
public class AdminUserUpdateDTO implements Serializable {

    @ApiModelProperty("用户id")
    @NotNull(message = "用户id不能为空")
    private Long id;

    @ApiModelProperty("用户名")
    @NotNull(message = "用户名不能为空")
    @NotBlank(message = "用户名不能为空")
    private String userName;

    @ApiModelProperty("用户手机号")
    @NotNull(message = "手机号不能为空")
    @NotBlank(message = "手机号不能为空")
    private String phone;

    @ApiModelProperty("邮箱")
    private String email;

    @ApiModelProperty("用户性别")
    private String sex;

    @ApiModelProperty("部门id")
    @NotNull(message = "部门不能为空")
    private Long departmentId;

    @ApiModelProperty("角色id")
    @NotNull(message = "部门不能为空")
    private Long roleId;
}