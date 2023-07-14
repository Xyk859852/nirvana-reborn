package com.phoenix.nirvana.service.system.rpc.role;


import com.phoenix.nirvana.common.vo.CommonResult;
import com.phoenix.nirvana.common.vo.PageResult;
import com.phoenix.nirvana.service.system.rpc.role.domain.dto.AddRoleDTO;
import com.phoenix.nirvana.service.system.rpc.role.domain.dto.RolePageDTO;
import com.phoenix.nirvana.service.system.rpc.role.domain.dto.UpdateRoleDTO;
import com.phoenix.nirvana.service.system.rpc.role.domain.vo.RoleCascade;
import com.phoenix.nirvana.service.system.rpc.role.domain.vo.RolePageItemVO;

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
    CommonResult<Boolean> createRole(AddRoleDTO addRoleDTO, Long userId);

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
