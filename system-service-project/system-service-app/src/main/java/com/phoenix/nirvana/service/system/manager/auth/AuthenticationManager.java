package com.phoenix.nirvana.service.system.manager.auth;

import com.phoenix.nirvana.common.vo.CommonResult;
import com.phoenix.nirvana.service.system.rpc.auth.login.domain.dto.AdminAuthenticationDTO;
import com.phoenix.nirvana.service.system.rpc.auth.login.domain.vo.*;
import com.phoenix.nirvana.service.system.service.auth.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class AuthenticationManager {

    @Autowired
    private AuthenticationService authenticationService;


    public CommonResult<AuthenticationUserVO> login(AdminAuthenticationDTO adminAuthenticationDTO) {
        return CommonResult.success(authenticationService.login(adminAuthenticationDTO));
    }

    public CommonResult<AuthenticationUserInfoVO> getUserInfo(Long userId) {
        return CommonResult.success(authenticationService.getUserInfo(userId));
    }
}
