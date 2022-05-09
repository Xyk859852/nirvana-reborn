package com.phoenix.nirvana.web.system.controller.user;

import com.phoenix.nirvana.admin.security.utils.SecurityUtils;
import com.phoenix.nirvana.common.vo.CommonResult;
import com.phoenix.nirvana.common.vo.PageResult;
import com.phoenix.nirvana.service.system.rpc.user.domain.dto.AdminUserCreateDTO;
import com.phoenix.nirvana.service.system.rpc.user.domain.dto.AdminUserPageDTO;
import com.phoenix.nirvana.service.system.rpc.user.domain.dto.AdminUserUpdateDTO;
import com.phoenix.nirvana.service.system.rpc.user.domain.vo.AdminUserPageItemVO;
import com.phoenix.nirvana.web.system.client.user.AdminUserRpcClient;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "管理员用户模块")
@RestController
@RequestMapping("/system/user")
public class AdminUserController {

    @Autowired
    AdminUserRpcClient adminUserRpcClient;


    @ApiOperation("管理员列表分页查询")
    @GetMapping("getUserPageList")
    public CommonResult<PageResult<AdminUserPageItemVO>> getUserPageList(@Validated AdminUserPageDTO adminUserPageDTO) {
        return CommonResult.success(adminUserRpcClient.getUserPageList(adminUserPageDTO));
    }

    @ApiOperation("管理员用户新增")
    @PostMapping("createAdminUser")
    public CommonResult createAdminUser(@Validated AdminUserCreateDTO adminUserCreateDTO) {
        Long currentUserId = SecurityUtils.getCurrentUserId();
        adminUserCreateDTO.setCreator(currentUserId);
        adminUserCreateDTO.setUpdater(currentUserId);
        return CommonResult.success(adminUserRpcClient.createAdminUser(adminUserCreateDTO));
    }

    @ApiOperation("管理员用户更新")
    @PostMapping("updateAdminUser")
    public CommonResult updateAdminUser(@Validated AdminUserUpdateDTO adminUserUpdateDTO) {
        Long currentUserId = SecurityUtils.getCurrentUserId();
        adminUserUpdateDTO.setUpdater(currentUserId);
        return CommonResult.success(adminUserRpcClient.updateAdminUser(adminUserUpdateDTO));
    }

    @ApiOperation("管理员用户删除")
    @DeleteMapping("deleteAdminUser")
    public CommonResult deleteAdminUser(@RequestBody List<Long> ids) {
        return CommonResult.success(adminUserRpcClient.deleteAdminUser(ids));
    }


}

