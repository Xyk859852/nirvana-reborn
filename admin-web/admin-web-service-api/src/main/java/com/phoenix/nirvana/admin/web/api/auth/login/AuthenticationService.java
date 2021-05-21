package com.phoenix.nirvana.admin.web.api.auth.login;

import com.phoenix.nirvana.admin.web.api.auth.login.domain.dto.AdminAuthenticationDTO;
import com.phoenix.nirvana.admin.web.api.auth.login.domain.vo.AuthenticationLoginCodeVO;
import com.phoenix.nirvana.admin.web.api.auth.login.domain.vo.AuthenticationUserInfoVO;
import com.phoenix.nirvana.admin.web.api.auth.login.domain.vo.AuthenticationUserVO;

public interface AuthenticationService {


    /**
     * 获取登录验证码
     *
     * @return
     */
    AuthenticationLoginCodeVO getLoginCode();

    /**
     * 平台用户登录接口
     *
     * @param adminAuthenticationDTO
     * @return
     */
    AuthenticationUserVO login(AdminAuthenticationDTO adminAuthenticationDTO);

    /**
     * 根据token获取当前登录用户
     *
     * @param userId
     * @return
     */
    AuthenticationUserInfoVO getUserInfo(Long userId);
}
