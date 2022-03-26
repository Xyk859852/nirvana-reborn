package com.phoenix.nirvana.service.system.rpc.admin;


import com.phoenix.nirvana.common.vo.CommonResult;
import com.phoenix.nirvana.common.vo.PageResult;
import com.phoenix.nirvana.service.system.rpc.admin.domain.dto.role.AddRoleDTO;
import com.phoenix.nirvana.service.system.rpc.admin.domain.dto.role.RolePageDTO;
import com.phoenix.nirvana.service.system.rpc.admin.domain.dto.role.UpdateRoleDTO;
import com.phoenix.nirvana.service.system.rpc.admin.domain.vo.role.RoleCascade;
import com.phoenix.nirvana.service.system.rpc.admin.domain.vo.role.RolePageItemVO;

import java.util.List;

public interface RoleRpc {

    /**
     * 根据登录用户查询其子集角色
     *
     * @param userId
     * @return
     */
    CommonResult<List<RoleCascade>> getRolesCascade(Long userId);

    /**
     * 角色列表分页查询
     *
     * @param rolePageDTO
     * @return
     */
    CommonResult<PageResult<RolePageItemVO>> getRolePageList(RolePageDTO rolePageDTO);

    /**
     * 新增角色
     *
     * @param addRoleDTO
     * @param userId
     * @return
     */
    CommonResult<Boolean> addRole(AddRoleDTO addRoleDTO, Long userId);

    /**
     * 修改角色
     *
     * @param updateRoleDTO
     * @return
     */
    CommonResult<Boolean> updateRole(UpdateRoleDTO updateRoleDTO);

    /**
     * 删除角色
     *
     * @param ids
     * @return
     */
    CommonResult<Boolean> deleteRole(List<Long> ids);
}
