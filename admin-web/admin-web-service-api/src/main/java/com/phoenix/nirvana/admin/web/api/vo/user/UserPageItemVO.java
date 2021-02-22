package com.phoenix.nirvana.admin.web.api.vo.user;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@Accessors(chain = true)
public class UserPageItemVO implements Serializable {

    @ApiModelProperty("用户id")
    private Long id;

    @ApiModelProperty("用户名称")
    private String userName;

    @ApiModelProperty("用户手机号")
    private String phone;

    @ApiModelProperty("用户性别")
    private String sex;

    @ApiModelProperty("用户邮箱")
    private String email;

    @ApiModelProperty("用户部门信息")
    private Department department;

    @ApiModelProperty("用户角色信息")
    private Role role;

    @ApiModel("部门")
    @Data
    @Accessors(chain = true)
    public static class Department implements Serializable {

        @ApiModelProperty(value = "部门编号", required = true, example = "1")
        private Long id;

        @ApiModelProperty(value = "部门名称", required = true, example = "研发部")
        private String name;

    }

    @ApiModel("角色")
    @Data
    @Accessors(chain = true)
    public static class Role implements Serializable {

        @ApiModelProperty(value = "角色编号", required = true, example = "1")
        private Long id;

        @ApiModelProperty(value = "角色名", required = true, example = "码神")
        private String name;

    }
}
