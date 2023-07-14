package com.phoenix.nirvana.service.system.manager.auth;

import com.phoenix.nirvana.cache.redis.core.RedisCache;
import com.phoenix.nirvana.common.vo.CommonResult;
import com.phoenix.nirvana.service.system.dal.redis.loginuser.LoginUserInfoRedisDAO;
import com.phoenix.nirvana.service.system.dal.redis.onlineuser.OnlineUserRedisDAO;
import com.phoenix.nirvana.service.system.rpc.admin.domain.bo.OnlineUserBO;
import com.phoenix.nirvana.service.system.rpc.auth.login.domain.dto.AdminAuthenticationDTO;
import com.phoenix.nirvana.service.system.rpc.auth.login.domain.vo.*;
import com.phoenix.nirvana.service.system.service.auth.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class AuthenticationManager {

    @Autowired
    AuthenticationService authenticationService;

    @Autowired
    RedisCache redisCache;

    @Autowired
    LoginUserInfoRedisDAO loginUserInfoRedisDao;

    @Autowired
    OnlineUserRedisDAO onlineUserRedisDAO;

    public CommonResult<AuthenticationUserVO> login(AdminAuthenticationDTO adminAuthenticationDTO) {
        AuthenticationUserVO user = authenticationService.login(adminAuthenticationDTO);
        OnlineUserBO onlineUser = new OnlineUserBO();
        onlineUser.setId(user.getId());
        onlineUser.setUserName(user.getUserName());
        onlineUser.setEnable(user.getEnable());
        onlineUser.setPassword(user.getPassword());
        onlineUserRedisDAO.set(user.getToken(), onlineUser);
        return CommonResult.success(user);
    }

    public CommonResult<LoginUserInfoVO> getUserInfo(Long userId) {
        if (loginUserInfoRedisDao.hasKey(userId)) {
            return CommonResult.success(loginUserInfoRedisDao.get(userId));
        }
        LoginUserInfoVO userInfo = authenticationService.getUserInfo(userId);
        if (userId != null) {
            loginUserInfoRedisDao.set(userInfo);
        }
        return CommonResult.success(userInfo);
    }

    public void logout(String token, Long userId) {
        loginUserInfoRedisDao.delete(userId);
        onlineUserRedisDAO.delete(token);
    }
}
