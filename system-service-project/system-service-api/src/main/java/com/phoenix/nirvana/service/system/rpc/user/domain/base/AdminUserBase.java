package com.phoenix.nirvana.service.system.rpc.user.domain.base;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class AdminUserBase {

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
    private Long deptId;

    @ApiModelProperty("岗位id")
    @NotNull(message = "岗位不能为空")
    private Long postId;

    @ApiModelProperty("角色id")
    @NotNull(message = "部门不能为空")
    private Long roleId;
}
