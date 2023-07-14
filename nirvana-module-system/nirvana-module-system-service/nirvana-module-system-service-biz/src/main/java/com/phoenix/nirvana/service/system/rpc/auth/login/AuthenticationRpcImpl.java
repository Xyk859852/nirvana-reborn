package com.phoenix.nirvana.service.system.rpc.auth.login;

import com.phoenix.nirvana.cache.redis.core.RedisCache;
import com.phoenix.nirvana.common.vo.CommonResult;
import com.phoenix.nirvana.service.system.manager.auth.AuthenticationManager;
import com.phoenix.nirvana.service.system.rpc.admin.OAuth2TokenApi;
import com.phoenix.nirvana.service.system.rpc.auth.login.domain.dto.AdminAuthenticationDTO;
import com.phoenix.nirvana.service.system.rpc.auth.login.domain.vo.AuthenticationUserVO;
import com.phoenix.nirvana.service.system.rpc.auth.login.domain.vo.LoginUserInfoVO;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
@DubboService
public class AuthenticationRpcImpl implements AuthenticationRpc {

    @Autowired
    RedisCache redisCache;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    OAuth2TokenApi oAuth2TokenApi;

    @Override
    public CommonResult<AuthenticationUserVO> login(AdminAuthenticationDTO adminAuthenticationDTO) {
        return authenticationManager.login(adminAuthenticationDTO);
    }

    @Override
    public CommonResult<LoginUserInfoVO> getUserInfo(Long userId) {
        return authenticationManager.getUserInfo(userId);
    }

    @Override
    public void logout(String token, Long userId) {
        authenticationManager.logout(token, userId);
    }
}
