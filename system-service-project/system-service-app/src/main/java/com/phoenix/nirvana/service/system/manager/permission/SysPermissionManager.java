package com.phoenix.nirvana.service.system.manager.permission;

import com.phoenix.nirvana.common.vo.CommonResult;
import com.phoenix.nirvana.service.system.rpc.auth.permission.domain.dto.AddPermissionDTO;
import com.phoenix.nirvana.service.system.rpc.auth.permission.domain.dto.PermissionListDTO;
import com.phoenix.nirvana.service.system.rpc.auth.permission.domain.dto.UpdatePermissionDTO;
import com.phoenix.nirvana.service.system.rpc.auth.permission.domain.vo.PermissionMenuListItemVO;
import com.phoenix.nirvana.service.system.rpc.auth.permission.domain.vo.PermissionMenuTreeItemVO;
import com.phoenix.nirvana.service.system.service.permission.SysPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.phoenix.nirvana.common.vo.CommonResult.success;

@Component
public class SysPermissionManager {

    @Autowired
    SysPermissionService permissionService;

    public CommonResult<List<PermissionMenuListItemVO>> getPermissionList(PermissionListDTO permissionListDTO) {
        return success(permissionService.getPermissionList(permissionListDTO));
    }

    public CommonResult<List<PermissionMenuTreeItemVO>> getPermissionMenuTreeSuperior(Long id) {
        return success(permissionService.getPermissionMenuTreeSuperior(id));
    }

    public CommonResult<List<PermissionMenuTreeItemVO>> getPermissionMenuAllTree() {
        return success(permissionService.getPermissionMenuAllTree());
    }

    @Transactional
    public CommonResult<Boolean> addPermission(AddPermissionDTO addPermissionDTO) {
        return success(permissionService.addPermission(addPermissionDTO));
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
