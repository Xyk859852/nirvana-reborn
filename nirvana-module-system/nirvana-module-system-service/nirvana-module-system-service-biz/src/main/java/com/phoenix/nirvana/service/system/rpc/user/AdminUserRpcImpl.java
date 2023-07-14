package com.phoenix.nirvana.service.system.rpc.user;

import com.phoenix.nirvana.common.vo.CommonResult;
import com.phoenix.nirvana.common.vo.PageResult;
import com.phoenix.nirvana.service.system.manager.user.AdminUserManager;
import com.phoenix.nirvana.service.system.rpc.user.domain.dto.AdminUserCreateDTO;
import com.phoenix.nirvana.service.system.rpc.user.domain.dto.AdminUserPageDTO;
import com.phoenix.nirvana.service.system.rpc.user.domain.dto.AdminUserUpdateDTO;
import com.phoenix.nirvana.service.system.rpc.user.domain.vo.AdminUserPageItemVO;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@DubboService
public class AdminUserRpcImpl implements AdminUserRpc {

    @Autowired
    AdminUserManager adminUserManager;

    @Override
    public CommonResult<PageResult<AdminUserPageItemVO>> getUserPageList(AdminUserPageDTO adminUserPageDTO) {
        return adminUserManager.getUserPageList(adminUserPageDTO);
    }

    @Override
    public CommonResult<Boolean> createAdminUser(AdminUserCreateDTO adminUserCreateDTO) {
        return adminUserManager.createAdminUser(adminUserCreateDTO);
    }

    @Override
    public CommonResult<Boolean> updateAdminUser(AdminUserUpdateDTO adminUserUpdateDTO) {
        return adminUserManager.updateAdminUser(adminUserUpdateDTO);
    }

    @Override
    public CommonResult<Boolean> deleteAdminUser(List<Long> ids) {
        return adminUserManager.deleteAdminUser(ids);
    }

}
