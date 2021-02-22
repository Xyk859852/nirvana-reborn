package com.phoenix.nirvana.admin.web.application.controller;

import com.phoenix.nirvana.admin.security.utils.SecurityUtils;
import com.phoenix.nirvana.admin.web.api.RoleService;
import com.phoenix.nirvana.admin.web.api.vo.department.DepartmentCascade;
import com.phoenix.nirvana.admin.web.api.vo.role.RoleCascade;
import com.phoenix.nirvana.common.vo.CommonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api(tags = "用户角色模块")
@RestController
@RequestMapping("role")
public class RoleController {

    @DubboReference
    RoleService roleService;

    @ApiOperation("根据登录用户查询其子集角色")
    @GetMapping("getRolesCascade")
    public CommonResult<List<RoleCascade>> getRolesCascade() {
        return CommonResult.success(roleService.getRolesCascade(SecurityUtils.getCurrentUserId()));
    }
}
