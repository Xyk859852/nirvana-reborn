package com.phoenix.nirvana.service.system.rpc.role;

import com.phoenix.nirvana.common.vo.CommonResult;
import com.phoenix.nirvana.common.vo.PageResult;
import com.phoenix.nirvana.service.system.manager.role.RoleManager;
import com.phoenix.nirvana.service.system.rpc.role.domain.dto.AddRoleDTO;
import com.phoenix.nirvana.service.system.rpc.role.domain.dto.RolePageDTO;
import com.phoenix.nirvana.service.system.rpc.role.domain.dto.UpdateRoleDTO;
import com.phoenix.nirvana.service.system.rpc.role.domain.vo.RoleCascade;
import com.phoenix.nirvana.service.system.rpc.role.domain.vo.RolePageItemVO;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@DubboService
public class RoleRpcImpl implements RoleRpc {

    @Autowired
    RoleManager roleManager;

    @Override
    public CommonResult<List<RoleCascade>> getRolesCascade(Long userId) {
        return roleManager.getRolesCascade(userId);
    }

    @Override
    public CommonResult<PageResult<RolePageItemVO>> getRolePageList(RolePageDTO rolePageDTO) {
        return roleManager.getRolePageList(rolePageDTO);
    }

    @Override
    public CommonResult<Boolean> createRole(AddRoleDTO addRoleDTO, Long userId) {
        return roleManager.createRole(addRoleDTO, userId);
    }

    @Override
    public CommonResult<Boolean> updateRole(UpdateRoleDTO updateRoleDTO) {
        return roleManager.updateRole(updateRoleDTO);
    }

    @Override
    public CommonResult<Boolean> deleteRole(List<Long> ids) {
        return roleManager.deleteRole(ids);
    }
}
