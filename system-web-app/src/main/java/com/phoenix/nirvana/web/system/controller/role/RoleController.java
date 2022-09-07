package com.phoenix.nirvana.web.system.controller.role;

import com.phoenix.nirvana.admin.security.core.utils.SecurityFrameworkUtils;
import com.phoenix.nirvana.common.vo.CommonResult;
import com.phoenix.nirvana.common.vo.PageResult;
import com.phoenix.nirvana.service.system.rpc.role.domain.dto.AddRoleDTO;
import com.phoenix.nirvana.service.system.rpc.role.domain.dto.RolePageDTO;
import com.phoenix.nirvana.service.system.rpc.role.domain.dto.UpdateRoleDTO;
import com.phoenix.nirvana.service.system.rpc.role.domain.vo.RoleCascade;
import com.phoenix.nirvana.service.system.rpc.role.domain.vo.RolePageItemVO;
import com.phoenix.nirvana.web.system.client.role.RoleRpcClient;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "用户角色模块")
@RestController
@RequestMapping("role")
public class RoleController {

    @Autowired
    RoleRpcClient roleRpcClient;


    @ApiOperation("根据登录用户查询其子集角色")
    @GetMapping("getRolesCascade")
    public CommonResult<List<RoleCascade>> getRolesCascade() {
        return CommonResult.success(roleRpcClient.getRolesCascade(SecurityFrameworkUtils.getLoginUserId()));
    }

    @ApiOperation("角色列表分页查询")
    @GetMapping("getRolePageList")
    public CommonResult<PageResult<RolePageItemVO>> getRolePageList(RolePageDTO rolePageDTO) {
        return CommonResult.success(roleRpcClient.getRolePageList(rolePageDTO));
    }

    @ApiOperation("新增角色")
    @PostMapping("createRole")
    public CommonResult<Boolean> createRole(@RequestBody AddRoleDTO addRoleDTO) {
        Long currentUserId = SecurityFrameworkUtils.getLoginUserId();
        addRoleDTO.setCreator(currentUserId);
        addRoleDTO.setUpdater(currentUserId);
        return CommonResult.success(roleRpcClient.createRole(addRoleDTO, SecurityFrameworkUtils.getLoginUserId()));
    }

    @ApiOperation("修改角色")
    @PostMapping("updateRole")
    public CommonResult<Boolean> updateRole(@RequestBody UpdateRoleDTO updateRoleDTO) {
        Long currentUserId = SecurityFrameworkUtils.getLoginUserId();
        updateRoleDTO.setUpdater(currentUserId);
        return CommonResult.success(roleRpcClient.updateRole(updateRoleDTO));
    }

    @ApiOperation("删除角色")
    @DeleteMapping("deleteRole")
    public CommonResult<Boolean> deleteRole(@RequestBody List<Long> ids) {
        return CommonResult.success(roleRpcClient.deleteRole(ids));
    }


}
