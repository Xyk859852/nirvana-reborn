package com.phoenix.nirvana.admin.web.application.controller;

import com.phoenix.nirvana.admin.security.annotaion.AnonymousAccess;
import com.phoenix.nirvana.admin.web.api.auth.menu.MenuService;
import com.phoenix.nirvana.admin.web.api.auth.menu.domain.dto.AddMenuDTO;
import com.phoenix.nirvana.admin.web.api.auth.menu.domain.dto.MenuListDTO;
import com.phoenix.nirvana.admin.web.api.auth.menu.domain.dto.UpdateMenuDTO;
import com.phoenix.nirvana.admin.web.api.auth.menu.domain.vo.MenuListItemVO;
import com.phoenix.nirvana.admin.web.api.auth.menu.domain.vo.MenuTreeItemVO;
import com.phoenix.nirvana.common.vo.CommonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "菜单功能")
@RestController
@RequestMapping("menu")
public class MenuController {

    @DubboReference
    MenuService menuService;

    @ApiOperation("菜单列表查询")
    @AnonymousAccess
    @GetMapping("getMenuList")
    public CommonResult<List<MenuListItemVO>> getMenuList(MenuListDTO menuListDTO) {
        return CommonResult.success(menuService.getMenuList(menuListDTO));
    }

    @ApiOperation("根据菜单id获取父级所有菜单 tree数据")
    @AnonymousAccess
    @GetMapping("getMenuTreeSuperior")
    public CommonResult<List<MenuTreeItemVO>> getMenuTreeSuperior(Long id) {
        return CommonResult.success(menuService.getMenuTreeSuperior(id));
    }

    @ApiOperation("全部菜单树结构数据查询 tree数据")
    @AnonymousAccess
    @GetMapping("getMenuAllTree")
    public CommonResult<List<MenuTreeItemVO>> getMenuAllTree() {
        return CommonResult.success(menuService.getMenuAllTree());
    }

    @ApiOperation("新增菜单")
    @AnonymousAccess
    @PostMapping("addMenu")
    public CommonResult<Boolean> addMenu(AddMenuDTO addMenuDTO) {
        return CommonResult.success(menuService.addMenu(addMenuDTO));
    }

    @ApiOperation("修改菜单")
    @AnonymousAccess
    @PostMapping("updateMenu")
    public CommonResult<Boolean> updateMenu(UpdateMenuDTO updateMenuDTO) {
        return CommonResult.success(menuService.updateMenu(updateMenuDTO));
    }

    @ApiOperation("删除菜单")
    @AnonymousAccess
    @PostMapping("deleteMenu")
    public CommonResult<Boolean> deleteMenu(@RequestBody List<Long> ids){
        return CommonResult.success(menuService.deleteMenu(ids));
    }

}
