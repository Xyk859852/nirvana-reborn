package com.phoenix.nirvana.service.system.rpc.user.domain.vo;

import com.phoenix.nirvana.service.system.rpc.user.domain.base.AdminUserBase;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@ApiModel("用户列表查询对象")
@Accessors(chain = true)
public class AdminUserPageItemVO extends AdminUserBase implements Serializable {

    @ApiModelProperty("用户id")
    private Long id;

    @ApiModelProperty("用户部门信息")
    private Department department;

    @ApiModelProperty("用户角色信息")
    private Role role;

    @ApiModelProperty("创建时间")
    private LocalDateTime createTime;

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
