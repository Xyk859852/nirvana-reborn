package com.phoenix.nirvana.service.system.manager.user;

import com.phoenix.nirvana.common.vo.CommonResult;
import com.phoenix.nirvana.common.vo.PageResult;
import com.phoenix.nirvana.service.system.rpc.user.domain.dto.AdminUserCreateDTO;
import com.phoenix.nirvana.service.system.rpc.user.domain.dto.AdminUserPageDTO;
import com.phoenix.nirvana.service.system.rpc.user.domain.dto.AdminUserUpdateDTO;
import com.phoenix.nirvana.service.system.rpc.user.domain.vo.AdminUserPageItemVO;
import com.phoenix.nirvana.service.system.service.user.AdminUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AdminUserManager {

    @Autowired
    AdminUserService adminUserService;

    public CommonResult<PageResult<AdminUserPageItemVO>> getUserPageList(AdminUserPageDTO adminUserPageDTO) {
        return CommonResult.success(adminUserService.getUserPageList(adminUserPageDTO));
    }

    public CommonResult<Boolean> createAdminUser(AdminUserCreateDTO adminUserCreateDTO) {
        return CommonResult.success(adminUserService.createAdminUser(adminUserCreateDTO));
    }

    public CommonResult<Boolean> updateAdminUser(AdminUserUpdateDTO adminUserUpdateDTO) {
        return CommonResult.success(adminUserService.updateAdminUser(adminUserUpdateDTO));
    }

    public CommonResult<Boolean> deleteAdminUser(List<Long> ids) {
        return CommonResult.success(adminUserService.deleteAdminUser(ids));
    }
}
