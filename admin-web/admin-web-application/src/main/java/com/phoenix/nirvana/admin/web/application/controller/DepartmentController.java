package com.phoenix.nirvana.admin.web.application.controller;

import com.phoenix.nirvana.admin.security.utils.SecurityUtils;
import com.phoenix.nirvana.admin.web.api.admin.DepartmentService;
import com.phoenix.nirvana.admin.web.api.admin.domain.vo.department.DepartmentCascade;
import com.phoenix.nirvana.common.vo.CommonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api(tags = "部门管理模块")
@RestController
@RequestMapping("department")
public class DepartmentController {

    @DubboReference
    DepartmentService departmentService;

    @ApiOperation("根据登录用户查询其子集部门")
    @GetMapping("getDepartmentsCascade")
    public CommonResult<List<DepartmentCascade>> getDepartmentsCascade() {
        return CommonResult.success(departmentService.getDepartmentsCascade(SecurityUtils.getCurrentUserId()));
    }



}
