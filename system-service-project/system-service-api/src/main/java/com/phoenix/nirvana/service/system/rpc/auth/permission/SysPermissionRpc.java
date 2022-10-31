package com.phoenix.nirvana.service.system.rpc.auth.permission;


import com.phoenix.nirvana.common.vo.CommonResult;
import com.phoenix.nirvana.common.vo.PageResult;
import com.phoenix.nirvana.service.system.enums.ApiConstants;
import com.phoenix.nirvana.service.system.rpc.auth.permission.domain.dto.AddPermissionDTO;
import com.phoenix.nirvana.service.system.rpc.auth.permission.domain.dto.PermissionListDTO;
import com.phoenix.nirvana.service.system.rpc.auth.permission.domain.dto.UpdatePermissionDTO;
import com.phoenix.nirvana.service.system.rpc.auth.permission.domain.vo.PermissionMenuListItemVO;
import com.phoenix.nirvana.service.system.rpc.auth.permission.domain.vo.PermissionMenuTreeItemVO;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(ApiConstants.SYSTEM_SERVICE_APP)
public interface SysPermissionRpc {

    String PREFIX = ApiConstants.SYSTEM_SERVICE_APP_PREFIX + "/permission";

    /**
     * 菜单列表数据查询
     *
     * @param permissionListDTO
     * @return
     */
    @ApiOperation("菜单列表查询")
    @GetMapping("getPermissionList")
    CommonResult<PageResult<PermissionMenuListItemVO>> getPermissionList(PermissionListDTO permissionListDTO);

    /**
     * 根据 id 获取上级菜单列表 tree 树结构格式返回
     *
     * @param id
     * @return
     */
    @ApiOperation("根据菜单id获取父级所有菜单 tree数据")
    @GetMapping("getPermissionMenuTreeSuperior")
    CommonResult<List<PermissionMenuTreeItemVO>> getPermissionMenuTreeSuperior(Long id);

    /**
     * 获取所有的菜单数据，tree 树结构格式返回
     * @return
     */
    @ApiOperation("获取所有菜单数据 tree数据")
    @GetMapping("getPermissionMenuAllTree")
    CommonResult<List<PermissionMenuTreeItemVO>> getPermissionMenuAllTree();

    /**
     * 新增菜单资源
     *
     * @param addPermissionDTO
     * @return
     */
    @ApiOperation("新增菜单")
    @PostMapping("createPermission")
    CommonResult<Boolean> createPermission(AddPermissionDTO addPermissionDTO);

    /**
     * 修改菜单资源
     *
     * @param updatePermissionDTO
     * @return
     */
    @ApiOperation("修改菜单")
    @PostMapping("updatePermission")
    CommonResult<Boolean> updatePermission(UpdatePermissionDTO updatePermissionDTO);

    /**
     * 根据主键id 删除对应菜单资源。并且所关联的子集菜单也一并删除
     *
     * @param ids
     * @return
     */
    @ApiOperation("删除菜单")
    @DeleteMapping("deletePermission")
    CommonResult<Boolean> deletePermission(List<Long> ids);

    @GetMapping(PREFIX + "/has-any-permissions")
    @ApiOperation("判断是否有权限，任一一个即可")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "用户编号", example = "1", required = true, dataTypeClass = Long.class),
            @ApiImplicitParam(name = "permissions", value = "权限", example = "read,write", required = true, allowMultiple = true)
    })
    CommonResult<Boolean> hasAnyPermissions(@RequestParam("userId") Long userId,
                                            @RequestParam("permissions") String... permissions);

    @GetMapping(PREFIX + "/has-any-roles")
    @ApiOperation("判断是否有角色，任一一个即可")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "用户编号", example = "1", required = true, dataTypeClass = Long.class),
            @ApiImplicitParam(name = "roles", value = "角色数组", example = "2", required = true, allowMultiple = true)
    })
    CommonResult<Boolean> hasAnyRoles(@RequestParam("userId") Long userId,
                                      @RequestParam("roles") String... roles);
}
