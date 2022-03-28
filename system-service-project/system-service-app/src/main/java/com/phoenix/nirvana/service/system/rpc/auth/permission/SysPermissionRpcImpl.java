package com.phoenix.nirvana.service.system.rpc.auth.permission;

import com.phoenix.nirvana.common.vo.CommonResult;
import com.phoenix.nirvana.service.system.manager.permission.SysPermissionManager;
import com.phoenix.nirvana.service.system.rpc.auth.permission.domain.dto.AddPermissionDTO;
import com.phoenix.nirvana.service.system.rpc.auth.permission.domain.dto.PermissionListDTO;
import com.phoenix.nirvana.service.system.rpc.auth.permission.domain.dto.UpdatePermissionDTO;
import com.phoenix.nirvana.service.system.rpc.auth.permission.domain.vo.PermissionMenuListItemVO;
import com.phoenix.nirvana.service.system.rpc.auth.permission.domain.vo.PermissionMenuTreeItemVO;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@DubboService
public class SysPermissionRpcImpl implements SysPermissionRpc {

    @Autowired
    SysPermissionManager permissionManager;

    @Override
    public CommonResult<List<PermissionMenuListItemVO>> getPermissionList(PermissionListDTO permissionListDTO) {
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
    public CommonResult<Boolean> addPermission(AddPermissionDTO addPermissionDTO) {
        return permissionManager.addPermission(addPermissionDTO);
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
