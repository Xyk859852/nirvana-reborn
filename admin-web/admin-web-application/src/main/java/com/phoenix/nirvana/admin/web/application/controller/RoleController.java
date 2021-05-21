package com.phoenix.nirvana.admin.web.application.controller;

import com.phoenix.nirvana.admin.security.utils.SecurityUtils;
import com.phoenix.nirvana.admin.web.api.admin.RoleService;
import com.phoenix.nirvana.admin.web.api.admin.domain.dto.role.AddRoleDTO;
import com.phoenix.nirvana.admin.web.api.admin.domain.dto.role.RolePageDTO;
import com.phoenix.nirvana.admin.web.api.admin.domain.dto.role.UpdateRoleDTO;
import com.phoenix.nirvana.admin.web.api.admin.domain.vo.role.RoleCascade;
import com.phoenix.nirvana.admin.web.api.admin.domain.vo.role.RolePageItemVO;
import com.phoenix.nirvana.common.vo.CommonResult;
import com.phoenix.nirvana.common.vo.PageResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.*;

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

    @ApiOperation("角色列表分页查询")
    @GetMapping("getRolePageList")
    public CommonResult<PageResult<RolePageItemVO>> getRolePageList(RolePageDTO rolePageDTO) {
        return CommonResult.success(roleService.getRolePageList(rolePageDTO));
    }

    @ApiOperation("新增角色")
    @PostMapping("addRole")
    public CommonResult<Boolean> addRole(@RequestBody AddRoleDTO addRoleDTO) {
        return CommonResult.success(roleService.addRole(addRoleDTO, SecurityUtils.getCurrentUserId()));
    }

    @ApiOperation("修改角色")
    @PostMapping("updateRole")
    public CommonResult<Boolean> updateRole(@RequestBody UpdateRoleDTO updateRoleDTO) {
        return CommonResult.success(roleService.updateRole(updateRoleDTO));
    }

    @ApiOperation("修改角色")
    @PostMapping("deleteRole")
    public CommonResult<Boolean> deleteRole(@RequestBody List<Long> ids) {
        return CommonResult.success(roleService.deleteRole(ids));
    }


}
