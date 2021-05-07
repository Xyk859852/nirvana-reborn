package com.phoenix.nirvana.admin.web.application.controller;

import com.phoenix.nirvana.admin.web.api.AdminUserService;
import com.phoenix.nirvana.admin.web.api.dto.user.AdminUserCreateDTO;
import com.phoenix.nirvana.admin.web.api.dto.user.AdminUserPageDTO;
import com.phoenix.nirvana.admin.web.api.dto.user.AdminUserUpdateDTO;
import com.phoenix.nirvana.admin.web.api.vo.user.UserPageItemVO;
import com.phoenix.nirvana.common.vo.CommonResult;
import com.phoenix.nirvana.common.vo.PageResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "管理员用户模块")
@RestController
@RequestMapping("user")
public class AdminUserController {

    @DubboReference
    AdminUserService adminUserService;


    @ApiOperation("管理员列表分页查询")
    @GetMapping("getUserPageList")
    public CommonResult<PageResult<UserPageItemVO>> getUserPageList(@Validated AdminUserPageDTO adminUserPageDTO) {
        return CommonResult.success(adminUserService.getUserPageList(adminUserPageDTO));
    }

    @ApiOperation("管理员用户新增")
    @PostMapping("addAdminUser")
    public CommonResult addAdminUser(@Validated AdminUserCreateDTO adminUserCreateDTO) {
        adminUserService.addAdminUser(adminUserCreateDTO);
        return CommonResult.success();
    }

    @ApiOperation("管理员用户更新")
    @PostMapping("updateAdminUser")
    public CommonResult updateAdminUser(@Validated AdminUserUpdateDTO adminUserUpdateDTO) {
        adminUserService.updateAdminUser(adminUserUpdateDTO);
        return CommonResult.success();
    }

    @ApiOperation("管理员用户删除")
    @DeleteMapping("deleteAdminUser")
    public CommonResult deleteAdminUser(@RequestBody List<Long> ids) {
        adminUserService.deleteAdminUser(ids);
        return CommonResult.success(true);
    }


}

