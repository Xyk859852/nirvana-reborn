package com.phoenix.nirvana.service.system.manager.online;

import com.phoenix.nirvana.common.vo.CommonResult;
import com.phoenix.nirvana.service.system.rpc.admin.domain.bo.OnlineUserBO;
import com.phoenix.nirvana.service.system.service.online.OnlineUserService;
import com.phoenix.nirvana.tenant.core.aop.Tenant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OnlineUserManager {

    @Autowired
    OnlineUserService onlineUserService;

    public CommonResult<OnlineUserBO> getOnlineUserByToken(String token) {
        return CommonResult.success(onlineUserService.getOnlineUserByToken(token));
    }

    public CommonResult<OnlineUserBO> getOnlineUserByUserName(String userName) {
        return CommonResult.success(onlineUserService.getOnlineUserByUserName(userName));
    }
}
