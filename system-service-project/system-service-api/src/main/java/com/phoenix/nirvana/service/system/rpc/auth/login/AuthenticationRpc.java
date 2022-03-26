package com.phoenix.nirvana.service.system.rpc.auth.login;


import com.phoenix.nirvana.common.vo.CommonResult;
import com.phoenix.nirvana.service.system.rpc.auth.login.domain.dto.AdminAuthenticationDTO;
import com.phoenix.nirvana.service.system.rpc.auth.login.domain.vo.AuthenticationLoginCodeVO;
import com.phoenix.nirvana.service.system.rpc.auth.login.domain.vo.AuthenticationUserInfoVO;
import com.phoenix.nirvana.service.system.rpc.auth.login.domain.vo.AuthenticationUserVO;

public interface AuthenticationRpc {


    /**
     * 获取登录验证码
     *
     * @return
     */
    CommonResult<AuthenticationLoginCodeVO> getLoginCode();

    /**
     * 平台用户登录接口
     *
     * @param adminAuthenticationDTO
     * @return
     */
    CommonResult<AuthenticationUserVO> login(AdminAuthenticationDTO adminAuthenticationDTO);

    /**
     * 根据token获取当前登录用户
     *
     * @param userId
     * @return
     */
    CommonResult<AuthenticationUserInfoVO> getUserInfo(Long userId);
}
