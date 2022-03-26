package com.phoenix.nirvana.admin.web.application.auth;

import com.phoenix.nirvana.common.vo.CommonResult;
import com.phoenix.nirvana.service.system.rpc.auth.login.AuthenticationRpc;
import com.phoenix.nirvana.service.system.rpc.auth.login.domain.dto.AdminAuthenticationDTO;
import com.phoenix.nirvana.service.system.rpc.auth.login.domain.vo.AuthenticationUserInfoVO;
import com.phoenix.nirvana.service.system.rpc.auth.login.domain.vo.AuthenticationUserVO;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationRpcClient {

    @DubboReference
    private AuthenticationRpc authenticationRpc;


    public AuthenticationUserVO login(AdminAuthenticationDTO adminAuthenticationDTO) {
        CommonResult<AuthenticationUserVO> result = authenticationRpc.login(adminAuthenticationDTO);
        result.checkError();
        return result.getData();
    }

    public AuthenticationUserInfoVO getUserInfo(Long userId) {
        CommonResult<AuthenticationUserInfoVO> result = authenticationRpc.getUserInfo(userId);
        result.checkError();
        return result.getData();
    }
}
