package com.phoenix.nirvana.service.system.manager.role;

import com.phoenix.nirvana.common.vo.CommonResult;
import com.phoenix.nirvana.common.vo.PageResult;
import com.phoenix.nirvana.service.system.rpc.role.domain.dto.AddRoleDTO;
import com.phoenix.nirvana.service.system.rpc.role.domain.dto.RolePageDTO;
import com.phoenix.nirvana.service.system.rpc.role.domain.dto.UpdateRoleDTO;
import com.phoenix.nirvana.service.system.rpc.role.domain.vo.RoleCascade;
import com.phoenix.nirvana.service.system.rpc.role.domain.vo.RolePageItemVO;
import com.phoenix.nirvana.service.system.service.role.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.phoenix.nirvana.common.vo.CommonResult.success;

@Component
public class RoleManager {

    @Autowired
    RoleService roleService;

    public CommonResult<List<RoleCascade>> getRolesCascade(Long userId) {
        return success(roleService.getRolesCascade(userId));
    }

    public CommonResult<PageResult<RolePageItemVO>> getRolePageList(RolePageDTO rolePageDTO) {
        return success(roleService.getRolePageList(rolePageDTO));
    }

    @Transactional
    public CommonResult<Boolean> createRole(AddRoleDTO addRoleDTO, Long userId) {
        return success(roleService.createRole(addRoleDTO, userId));
    }

    @Transactional
    public CommonResult<Boolean> updateRole(UpdateRoleDTO updateRoleDTO) {
        return success(roleService.updateRole(updateRoleDTO));
    }

    @Transactional
    public CommonResult<Boolean> deleteRole(List<Long> ids) {
        return success(roleService.deleteRole(ids));
    }

}
