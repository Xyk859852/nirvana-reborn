package com.phoenix.nirvana.service.system.manager.permission;

import com.phoenix.nirvana.common.vo.CommonResult;
import com.phoenix.nirvana.common.vo.PageResult;
import com.phoenix.nirvana.service.system.rpc.auth.permission.domain.dto.AddPermissionDTO;
import com.phoenix.nirvana.service.system.rpc.auth.permission.domain.dto.PermissionListDTO;
import com.phoenix.nirvana.service.system.rpc.auth.permission.domain.dto.UpdatePermissionDTO;
import com.phoenix.nirvana.service.system.rpc.auth.permission.domain.vo.PermissionMenuListItemVO;
import com.phoenix.nirvana.service.system.rpc.auth.permission.domain.vo.PermissionMenuTreeItemVO;
import com.phoenix.nirvana.service.system.service.permission.SysPermissionService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.phoenix.nirvana.common.vo.CommonResult.success;

@Component
public class SysPermissionManager {

    @Autowired
    SysPermissionService permissionService;

    public CommonResult<PageResult<PermissionMenuListItemVO>> getPermissionList(PermissionListDTO permissionListDTO) {
        return success(permissionService.getPermissionList(permissionListDTO));
    }

    public CommonResult<List<PermissionMenuTreeItemVO>> getPermissionMenuTreeSuperior(Long id) {
        return success(permissionService.getPermissionMenuTreeSuperior(id));
    }

    public CommonResult<List<PermissionMenuTreeItemVO>> getPermissionMenuAllTree() {
        return success(permissionService.getPermissionMenuAllTree());
    }

    public CommonResult<Boolean> hasAnyPermissions(Long userId, String... permissions) {
        return success(permissionService.hasAnyPermissions(userId, permissions));
    }

    public CommonResult<Boolean> hasAnyRoles(Long userId, String... roles) {
        return success(permissionService.hasAnyRoles(userId, roles));
    }

    @Transactional
    public CommonResult<Boolean> createPermission(AddPermissionDTO addPermissionDTO) {
        return success(permissionService.createPermission(addPermissionDTO));
    }

    @Transactional
    public CommonResult<Boolean> updatePermission(UpdatePermissionDTO updatePermissionDTO) {
        return success(permissionService.updatePermission(updatePermissionDTO));
    }

    @Transactional
    public CommonResult<Boolean> deletePermission(List<Long> ids) {
        return success(permissionService.deletePermission(ids));
    }

}
