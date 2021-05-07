package com.phoenix.nirvana.admin.web.api;

import com.phoenix.nirvana.admin.web.api.dto.role.AddRoleDTO;
import com.phoenix.nirvana.admin.web.api.dto.role.RolePageDTO;
import com.phoenix.nirvana.admin.web.api.dto.role.UpdateRoleDTO;
import com.phoenix.nirvana.admin.web.api.vo.role.RoleCascade;
import com.phoenix.nirvana.admin.web.api.vo.role.RolePageItemVO;
import com.phoenix.nirvana.common.vo.PageResult;

import java.util.List;

public interface RoleService {

    /**
     * 根据登录用户查询其子集角色
     *
     * @param userId
     * @return
     */
    List<RoleCascade> getRolesCascade(Long userId);

    /**
     * 角色列表分页查询
     *
     * @param rolePageDTO
     * @return
     */
    PageResult<RolePageItemVO> getRolePageList(RolePageDTO rolePageDTO);

    /**
     * 新增角色
     *
     * @param addRoleDTO
     * @param userId
     * @return
     */
    Boolean addRole(AddRoleDTO addRoleDTO, Long userId);

    /**
     * 修改角色
     *
     * @param updateRoleDTO
     * @return
     */
    Boolean updateRole(UpdateRoleDTO updateRoleDTO);

    /**
     * 删除角色
     * @param ids
     * @return
     */
    Boolean deleteRole(List<Long> ids);
}
