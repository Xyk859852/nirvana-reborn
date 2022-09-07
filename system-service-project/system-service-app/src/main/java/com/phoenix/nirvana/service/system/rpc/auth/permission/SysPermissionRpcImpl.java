package com.phoenix.nirvana.service.system.rpc.auth.permission;

import com.phoenix.nirvana.common.vo.CommonResult;
import com.phoenix.nirvana.common.vo.PageResult;
import com.phoenix.nirvana.service.system.manager.permission.SysPermissionManager;
import com.phoenix.nirvana.service.system.rpc.auth.permission.domain.dto.AddPermissionDTO;
import com.phoenix.nirvana.service.system.rpc.auth.permission.domain.dto.PermissionListDTO;
import com.phoenix.nirvana.service.system.rpc.auth.permission.domain.dto.UpdatePermissionDTO;
import com.phoenix.nirvana.service.system.rpc.auth.permission.domain.vo.PermissionMenuListItemVO;
import com.phoenix.nirvana.service.system.rpc.auth.permission.domain.vo.PermissionMenuTreeItemVO;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@Service
@DubboService
@RestController
public class SysPermissionRpcImpl implements SysPermissionRpc {

    @Autowired
    SysPermissionManager permissionManager;

    @Override
    public CommonResult<PageResult<PermissionMenuListItemVO>> getPermissionList(PermissionListDTO permissionListDTO) {
        return permissionManager.getPermissionList(permissionListDTO);
    }

    @Override
    public CommonResult<List<PermissionMenuTreeItemVO>> getPermissionMenuTreeSuperior(Long id) {
        return permissionManager.getPermissionMenuTreeSuperior(id);
    }

    @Override
    public CommonResult<List<PermissionMenuTreeItemVO>> getPermissionMenuAllTree() {
        return permissionManager.getPermissionMenuAllTree();
    }

    @Override
    public CommonResult<Boolean> hasAnyPermissions(Long userId, String... permissions) {
        return permissionManager.hasAnyPermissions(userId, permissions);
    }

    @Override
    public CommonResult<Boolean> hasAnyRoles(Long userId, String... roles) {
        return permissionManager.hasAnyRoles(userId, roles);
    }

    @Override
    public CommonResult<Boolean> createPermission(AddPermissionDTO addPermissionDTO) {
        return permissionManager.createPermission(addPermissionDTO);
    }

    @Override
    public CommonResult<Boolean> updatePermission(UpdatePermissionDTO updatePermissionDTO) {
        return permissionManager.updatePermission(updatePermissionDTO);
    }

    @Override
    public CommonResult<Boolean> deletePermission(List<Long> ids) {
        return permissionManager.deletePermission(ids);
    }
}
