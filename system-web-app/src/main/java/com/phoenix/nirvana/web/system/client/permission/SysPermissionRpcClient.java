package com.phoenix.nirvana.web.system.client.permission;

import com.phoenix.nirvana.common.vo.CommonResult;
import com.phoenix.nirvana.service.system.rpc.auth.permission.SysPermissionRpc;
import com.phoenix.nirvana.service.system.rpc.auth.permission.domain.dto.AddPermissionDTO;
import com.phoenix.nirvana.service.system.rpc.auth.permission.domain.dto.PermissionListDTO;
import com.phoenix.nirvana.service.system.rpc.auth.permission.domain.dto.UpdatePermissionDTO;
import com.phoenix.nirvana.service.system.rpc.auth.permission.domain.vo.PermissionMenuListItemVO;
import com.phoenix.nirvana.service.system.rpc.auth.permission.domain.vo.PermissionMenuTreeItemVO;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SysPermissionRpcClient {

    @DubboReference
    SysPermissionRpc permissionRpc;

    public List<PermissionMenuListItemVO> getPermissionList(PermissionListDTO permissionListDTO) {
        CommonResult<List<PermissionMenuListItemVO>> result = permissionRpc.getPermissionList(permissionListDTO);
        result.checkError();
        return result.getData();
    }

    public List<PermissionMenuTreeItemVO> getPermissionMenuTreeSuperior(Long id) {
        CommonResult<List<PermissionMenuTreeItemVO>> result = permissionRpc.getPermissionMenuTreeSuperior(id);
        result.checkError();
        return result.getData();
    }

    public List<PermissionMenuTreeItemVO> getPermissionMenuAllTree() {
        CommonResult<List<PermissionMenuTreeItemVO>> result = permissionRpc.getPermissionMenuAllTree();
        result.checkError();
        return result.getData();
    }

    public Boolean addPermission(AddPermissionDTO addPermissionDTO) {
        CommonResult<Boolean> result = permissionRpc.addPermission(addPermissionDTO);
        result.checkError();
        return result.getData();
    }

    public Boolean updatePermission(UpdatePermissionDTO updatePermissionDTO) {
        CommonResult<Boolean> result = permissionRpc.updatePermission(updatePermissionDTO);
        result.checkError();
        return result.getData();
    }

    public Boolean deletePermission(List<Long> ids) {
        CommonResult<Boolean> result = permissionRpc.deletePermission(ids);
        result.checkError();
        return result.getData();
    }
}
