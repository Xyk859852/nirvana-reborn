package com.phoenix.nirvana.service.system.rpc.auth.online;

import com.phoenix.nirvana.common.vo.CommonResult;
import com.phoenix.nirvana.service.system.manager.online.OnlineUserManager;
import com.phoenix.nirvana.service.system.rpc.admin.OnlineUserRpc;
import com.phoenix.nirvana.service.system.rpc.admin.domain.bo.OnlineUserBO;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@DubboService
public class OnlineUserRpcImpl implements OnlineUserRpc {

    @Autowired
    private OnlineUserManager onlineUserManager;

    @Override
    public CommonResult<OnlineUserBO> getOnlineUserByToken(String token) {
        return onlineUserManager.getOnlineUserByToken(token);
    }

    @Override
    public CommonResult<OnlineUserBO> getOnlineUserByUserName(String userName) {
        return onlineUserManager.getOnlineUserByUserName(userName);
    }
}
