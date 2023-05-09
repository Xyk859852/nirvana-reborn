package com.phoenix.nirvana.service.system.rpc.auth.online;

import com.phoenix.nirvana.common.vo.CommonResult;
import com.phoenix.nirvana.service.system.manager.online.OnlineUserManager;
import com.phoenix.nirvana.service.system.rpc.admin.OAuth2TokenApi;
import com.phoenix.nirvana.service.system.rpc.admin.domain.bo.OnlineUserBO;
import com.phoenix.nirvana.service.system.rpc.auth.login.domain.vo.LoginUserInfoVO;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

@Service
@DubboService
@RestController
public class OAuth2TokenApiImpl implements OAuth2TokenApi {

    @Autowired
    private OnlineUserManager onlineUserManager;

    @Override
    public CommonResult<OnlineUserBO> getOnlineUserByToken(String token) {
        return onlineUserManager.getOnlineUserByToken(token);
    }

}

