package com.phoenix.nirvana.service.system.rpc.auth.login;

import com.phoenix.nirvana.cache.redis.utils.RedisUtils;
import com.phoenix.nirvana.common.vo.CommonResult;
import com.phoenix.nirvana.service.system.manager.auth.AuthenticationManager;
import com.phoenix.nirvana.service.system.rpc.auth.login.domain.vo.AuthenticationUserInfoVO;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
@DubboService
public class AuthenticationRpcImpl implements AuthenticationRpc {

    @Autowired
    RedisUtils redisUtils;

    @Autowired
    AuthenticationManager authenticationManager;


    @Override
    public CommonResult<AuthenticationUserInfoVO> getUserInfo(Long userId) {
        return authenticationManager.getUserInfo(userId);
    }
}
