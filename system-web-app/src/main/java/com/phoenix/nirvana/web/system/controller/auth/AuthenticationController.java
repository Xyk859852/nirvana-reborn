package com.phoenix.nirvana.web.system.controller.auth;

import cn.hutool.core.util.IdUtil;
import com.phoenix.nirvana.admin.security.core.annotaion.AnonymousAccess;
import com.phoenix.nirvana.admin.security.core.bean.LoginCodeEnum;
import com.phoenix.nirvana.admin.security.core.bean.LoginProperties;
import com.phoenix.nirvana.admin.security.core.bean.SecurityProperties;
import com.phoenix.nirvana.admin.security.core.TokenProvider;
import com.phoenix.nirvana.admin.security.core.bo.LoginUser;
import com.phoenix.nirvana.admin.security.core.utils.SecurityFrameworkUtils;
import com.phoenix.nirvana.cache.redis.utils.RedisUtils;
import com.phoenix.nirvana.common.vo.CommonResult;
import com.phoenix.nirvana.service.system.rpc.admin.domain.bo.OnlineUserBO;
import com.phoenix.nirvana.service.system.rpc.auth.login.domain.dto.AdminAuthenticationDTO;
import com.phoenix.nirvana.service.system.rpc.auth.login.domain.vo.AuthenticationLoginCodeVO;
import com.phoenix.nirvana.service.system.rpc.auth.login.domain.vo.AuthenticationUserInfoVO;
import com.phoenix.nirvana.service.system.rpc.auth.login.domain.vo.AuthenticationUserVO;
import com.phoenix.nirvana.web.system.client.auth.AuthenticationRpcClient;
import com.wf.captcha.base.Captcha;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.concurrent.TimeUnit;

import static com.phoenix.nirvana.common.exception.util.ServiceExceptionUtil.exception;

@Slf4j
@Api(tags = "获取验证码-用户登录-用户登出-模块")
@RestController
@RequestMapping("auth")
public class AuthenticationController {

    @Resource
    SecurityProperties securityProperties;

    @Resource
    LoginProperties loginProperties;

    @Resource
    RedisUtils redisUtils;

    @Resource
    AuthenticationManagerBuilder authenticationManagerBuilder;


    @Resource
    TokenProvider tokenProvider;

    @Resource
    AuthenticationRpcClient authenticationRpcClient;

    @Resource
    PasswordEncoder passwordEncoder;

    @ApiOperation("用户登录")
    @AnonymousAccess
    @PostMapping("login")
    public CommonResult<AuthenticationUserVO> login(@Validated @RequestBody AdminAuthenticationDTO adminAuthenticationDTO) {
//        String cacheCode = (String) redisUtils.get(adminAuthenticationDTO.getCodeId());
//        if (!adminAuthenticationDTO.getCode().equals(cacheCode)) {
//            throw ServiceExceptionUtil.exception(CACHE_CODE_ERROR);
//        }
        log.info("login password:{}", passwordEncoder.encode(adminAuthenticationDTO.getPassword()));
        AuthenticationUserVO user = authenticationRpcClient.login(adminAuthenticationDTO);
        OnlineUserBO onlineUser = new OnlineUserBO();
        onlineUser.setId(user.getId());
        onlineUser.setUserName(user.getUserName());
        onlineUser.setEnable(user.getEnable());
        onlineUser.setPassword(user.getPassword());
        redisUtils.set(securityProperties.getOnlineKey() + ":" + user.getToken(), onlineUser, securityProperties.getTokenValidityInSeconds(), TimeUnit.MILLISECONDS);
//        redisUtils.del(adminAuthenticationDTO.getCodeId());
        return CommonResult.success(user);
    }

    @ApiOperation("根据token查询用户信息")
    @GetMapping("getUserInfo")
    public CommonResult<AuthenticationUserInfoVO> getUserInfo() {
        return CommonResult.success(authenticationRpcClient.getUserInfo(SecurityFrameworkUtils.getLoginUserId()));
    }

    @ApiOperation("获取登录验证码")
    @AnonymousAccess
    @GetMapping("getLoginCode")
    public CommonResult<AuthenticationLoginCodeVO> getLoginCode() {
        Captcha captcha = loginProperties.getCaptcha();
        String uuid = securityProperties.getCodeKey() + IdUtil.simpleUUID();
        //当验证码类型为 arithmetic时且长度 >= 2 时，captcha.text()的结果有几率为浮点型
        String captchaValue = captcha.text();
        if (captcha.getCharType() - 1 == LoginCodeEnum.arithmetic.ordinal() && captchaValue.contains(".")) {
            captchaValue = captchaValue.split("\\.")[0];
        }
        // 保存
        redisUtils.set(uuid, captchaValue, loginProperties.getLoginCode().getExpiration(), TimeUnit.MINUTES);
        return CommonResult.success(new AuthenticationLoginCodeVO().setImage(captcha.toBase64()).setUuId(uuid));
    }

    @ApiOperation("用户登出")
    @AnonymousAccess
    @PostMapping("logout")
    public CommonResult logout(HttpServletRequest request) {
        String token = SecurityFrameworkUtils.obtainAuthorization(request, securityProperties.getHeader());
        redisUtils.del(securityProperties.getOnlineKey() + ":" + token);
        return CommonResult.success();
    }

}
