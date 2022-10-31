package com.phoenix.nirvana.service.system.rpc.auth.login;


import com.phoenix.nirvana.common.vo.CommonResult;
import com.phoenix.nirvana.service.system.enums.ApiConstants;
import com.phoenix.nirvana.service.system.rpc.auth.login.domain.dto.AdminAuthenticationDTO;
import com.phoenix.nirvana.service.system.rpc.auth.login.domain.vo.LoginUserInfoVO;
import com.phoenix.nirvana.service.system.rpc.auth.login.domain.vo.AuthenticationUserVO;
import org.springframework.cloud.openfeign.FeignClient;

public interface AuthenticationRpc {


    /**
     * 用户登录
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
    CommonResult<LoginUserInfoVO> getUserInfo(Long userId);

    void logout(String token, Long userId);
}
