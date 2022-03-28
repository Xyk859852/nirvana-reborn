package com.phoenix.nirvana.web.system.controller.permission;

import com.phoenix.nirvana.common.vo.CommonResult;
import com.phoenix.nirvana.service.system.rpc.auth.permission.domain.dto.AddPermissionDTO;
import com.phoenix.nirvana.service.system.rpc.auth.permission.domain.dto.PermissionListDTO;
import com.phoenix.nirvana.service.system.rpc.auth.permission.domain.dto.UpdatePermissionDTO;
import com.phoenix.nirvana.service.system.rpc.auth.permission.domain.vo.PermissionMenuListItemVO;
import com.phoenix.nirvana.service.system.rpc.auth.permission.domain.vo.PermissionMenuTreeItemVO;
import com.phoenix.nirvana.web.system.client.permission.SysPermissionRpcClient;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.phoenix.nirvana.common.vo.CommonResult.success;

@Api(tags = "菜单功能")
@RestController
@RequestMapping("permission")
public class SysPermissionController {

    @Autowired
    SysPermissionRpcClient permissionRpcClient;

    @ApiOperation("菜单列表查询")
    @GetMapping("getPermissionList")
    public CommonResult<List<PermissionMenuListItemVO>> getPermissionList(PermissionListDTO permissionListDTO) {
        return success(permissionRpcClient.getPermissionList(permissionListDTO));
    }

    @ApiOperation("根据菜单id获取父级所有菜单 tree数据")
    @GetMapping("getPermissionMenuTreeSuperior")
    public CommonResult<List<PermissionMenuTreeItemVO>> getPermissionMenuTreeSuperior(Long id) {
        return success(permissionRpcClient.getPermissionMenuTreeSuperior(id));
    }

    @ApiOperation("获取所有菜单数据 tree数据")
    @GetMapping("getPermissionMenuAllTree")
    public CommonResult<List<PermissionMenuTreeItemVO>> getPermissionMenuAllTree() {
        return success(permissionRpcClient.getPermissionMenuAllTree());
    }

    @ApiOperation("新增菜单")
    @PostMapping("addPermission")
    public CommonResult<Boolean> addPermission(AddPermissionDTO addPermissionDTO) {
        return success(permissionRpcClient.addPermission(addPermissionDTO));
    }

    @ApiOperation("修改菜单")
    @PostMapping("updatePermission")
    public CommonResult<Boolean> updatePermission(UpdatePermissionDTO updatePermissionDTO) {
        return success(permissionRpcClient.updatePermission(updatePermissionDTO));
    }

    @ApiOperation("删除菜单")
    @PostMapping("deletePermission")
    public CommonResult<Boolean> deletePermission(@RequestBody List<Long> ids) {
        return success(permissionRpcClient.deletePermission(ids));
    }

}
