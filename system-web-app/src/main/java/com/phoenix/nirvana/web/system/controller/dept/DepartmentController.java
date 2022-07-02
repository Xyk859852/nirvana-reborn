package com.phoenix.nirvana.web.system.controller.dept;

import com.phoenix.nirvana.admin.security.utils.SecurityUtils;
import com.phoenix.nirvana.common.vo.CommonResult;
import com.phoenix.nirvana.service.system.rpc.dept.domain.vo.DepartmentCascade;
import com.phoenix.nirvana.web.system.client.dept.DepartmentRpcClient;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api(tags = "部门管理模块")
@RestController
@RequestMapping("department")
public class DepartmentController {

    @Autowired
    DepartmentRpcClient departmentRpcClient;

    @ApiOperation("根据登录用户查询其子集部门")
    @GetMapping("getDepartmentsCascade")
    public CommonResult<List<DepartmentCascade>> getDepartmentsCascade() {
        return CommonResult.success(departmentRpcClient.getDepartmentsCascade(SecurityUtils.getCurrentUserId()));
    }

}
