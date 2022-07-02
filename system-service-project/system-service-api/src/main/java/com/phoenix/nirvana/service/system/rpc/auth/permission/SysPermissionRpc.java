package com.phoenix.nirvana.service.system.rpc.auth.permission;


import com.phoenix.nirvana.common.vo.CommonResult;
import com.phoenix.nirvana.common.vo.PageResult;
import com.phoenix.nirvana.service.system.rpc.auth.permission.domain.dto.AddPermissionDTO;
import com.phoenix.nirvana.service.system.rpc.auth.permission.domain.dto.PermissionListDTO;
import com.phoenix.nirvana.service.system.rpc.auth.permission.domain.dto.UpdatePermissionDTO;
import com.phoenix.nirvana.service.system.rpc.auth.permission.domain.vo.PermissionMenuListItemVO;
import com.phoenix.nirvana.service.system.rpc.auth.permission.domain.vo.PermissionMenuTreeItemVO;

import java.util.List;

public interface SysPermissionRpc {

    /**
     * 菜单列表数据查询
     *
     * @param permissionListDTO
     * @return
     */
    CommonResult<PageResult<PermissionMenuListItemVO>> getPermissionList(PermissionListDTO permissionListDTO);

    /**
     * 根据 id 获取上级菜单列表 tree 树结构格式返回
     *
     * @param id
     * @return
     */
    CommonResult<List<PermissionMenuTreeItemVO>> getPermissionMenuTreeSuperior(Long id);

    /**
     * 获取所有的菜单数据，tree 树结构格式返回
     * @return
     */
    CommonResult<List<PermissionMenuTreeItemVO>> getPermissionMenuAllTree();

    /**
     * 新增菜单资源
     *
     * @param addPermissionDTO
     * @return
     */
    CommonResult<Boolean> createPermission(AddPermissionDTO addPermissionDTO);

    /**
     * 修改菜单资源
     *
     * @param updatePermissionDTO
     * @return
     */
    CommonResult<Boolean> updatePermission(UpdatePermissionDTO updatePermissionDTO);

    /**
     * 根据主键id 删除对应菜单资源。并且所关联的子集菜单也一并删除
     *
     * @param ids
     * @return
     */
    CommonResult<Boolean> deletePermission(List<Long> ids);
}
