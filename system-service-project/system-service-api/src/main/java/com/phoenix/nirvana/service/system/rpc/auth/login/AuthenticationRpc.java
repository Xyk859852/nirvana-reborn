package com.phoenix.nirvana.service.system.rpc.auth.login;


import com.phoenix.nirvana.common.vo.CommonResult;
import com.phoenix.nirvana.service.system.rpc.auth.login.domain.dto.AdminAuthenticationDTO;
import com.phoenix.nirvana.service.system.rpc.auth.login.domain.vo.AuthenticationLoginCodeVO;
import com.phoenix.nirvana.service.system.rpc.auth.login.domain.vo.AuthenticationUserInfoVO;
import com.phoenix.nirvana.service.system.rpc.auth.login.domain.vo.AuthenticationUserVO;

public interface AuthenticationRpc {

    /**
     * 根据token获取当前登录用户
     *
     * @param userId
     * @return
     */
    CommonResult<AuthenticationUserInfoVO> getUserInfo(Long userId);


    /**
     * 用户登录
     *
     * @param adminAuthenticationDTO
     * @return
     */
    CommonResult<AuthenticationUserVO> login(AdminAuthenticationDTO adminAuthenticationDTO);
}
