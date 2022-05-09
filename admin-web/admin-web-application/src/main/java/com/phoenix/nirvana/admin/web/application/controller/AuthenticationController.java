package com.phoenix.nirvana.admin.web.application.controller;

import cn.hutool.core.util.IdUtil;
import com.phoenix.nirvana.admin.security.annotaion.AnonymousAccess;
import com.phoenix.nirvana.admin.security.bean.LoginCodeEnum;
import com.phoenix.nirvana.admin.security.bean.LoginProperties;
import com.phoenix.nirvana.admin.security.bean.SecurityProperties;
import com.phoenix.nirvana.admin.security.bo.SecurityUserBO;
import com.phoenix.nirvana.admin.security.core.TokenProvider;
import com.phoenix.nirvana.admin.security.utils.SecurityUtils;
import com.phoenix.nirvana.admin.web.api.auth.login.AuthenticationService;
import com.phoenix.nirvana.admin.web.application.auth.AuthenticationRpcClient;
import com.phoenix.nirvana.cache.redis.utils.RedisUtils;
import com.phoenix.nirvana.common.vo.CommonResult;
import com.phoenix.nirvana.service.system.rpc.auth.login.domain.dto.AdminAuthenticationDTO;
import com.phoenix.nirvana.service.system.rpc.auth.login.domain.vo.AuthenticationLoginCodeVO;
import com.phoenix.nirvana.service.system.rpc.auth.login.domain.vo.AuthenticationUserInfoVO;
import com.phoenix.nirvana.service.system.rpc.auth.login.domain.vo.AuthenticationUserVO;
import com.wf.captcha.base.Captcha;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.concurrent.TimeUnit;

@Api(tags = "获取验证码-用户登录-用户登出-模块")
@Slf4j
@RestController
@RequestMapping("auth")
public class AuthenticationController {

    @Autowired
    SecurityProperties properties;

    @Autowired
    LoginProperties loginProperties;

    @Autowired
    RedisUtils redisUtils;

    @Autowired
    AuthenticationManagerBuilder authenticationManagerBuilder;

    @Autowired
    TokenProvider tokenProvider;

    @DubboReference
    AuthenticationService authenticationService;

    @Autowired
    AuthenticationRpcClient authenticationRpcClient;

    @ApiOperation("用户登录")
    @AnonymousAccess
    @PostMapping("login")
    public CommonResult<AuthenticationUserVO> login(@Validated @RequestBody AdminAuthenticationDTO adminAuthenticationDTO) {
        //旧版
//        AuthenticationUserVO authenticationUserVO = authenticationService.login(adminAuthenticationDTO);
        //新版
//        AuthenticationUserVO authenticationUserVO = authenticationRpcClient.login(adminAuthenticationDTO);

        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(adminAuthenticationDTO.getUsername(), adminAuthenticationDTO.getPassword());
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        // 生成令牌
        String token = tokenProvider.createToken(authentication);
        SecurityUserBO userBO = (SecurityUserBO) authentication.getPrincipal();
        redisUtils.set(properties.getOnlineKey() + ":" + token, userBO.getOnlineUserBO(), properties.getTokenValidityInSeconds(), TimeUnit.MILLISECONDS);
        return CommonResult.success();
    }

    @ApiOperation("根据token查询用户信息")
    @GetMapping("getUserInfo")
    public CommonResult<AuthenticationUserInfoVO> getUserInfo() {
        return CommonResult.success(authenticationRpcClient.getUserInfo(SecurityUtils.getCurrentUserId()));
    }

    @ApiOperation("获取登录验证码")
    @AnonymousAccess
    @GetMapping("getLoginCode")
    public CommonResult<AuthenticationLoginCodeVO> getLoginCode() {
        Captcha captcha = loginProperties.getCaptcha();
        String uuid = properties.getCodeKey() + IdUtil.simpleUUID();
        //当验证码类型为 arithmetic时且长度 >= 2 时，captcha.text()的结果有几率为浮点型
        String captchaValue = captcha.text();
        if (captcha.getCharType() - 1 == LoginCodeEnum.arithmetic.ordinal() && captchaValue.contains(".")) {
            captchaValue = captchaValue.split("\\.")[0];
        }
        // 保存
        log.info("登录验证码：{}", captchaValue);
        redisUtils.set(uuid, captchaValue, loginProperties.getLoginCode().getExpiration(), TimeUnit.MINUTES);
        return CommonResult.success(new AuthenticationLoginCodeVO().setImage(captcha.toBase64()).setUuId(uuid));
    }

    @ApiOperation("用户登出")
    @AnonymousAccess
    @PostMapping("logout")
    public CommonResult logout(HttpServletRequest request) {
        redisUtils.del(properties.getOnlineKey() + ":" + tokenProvider.getToken(request));
        return CommonResult.success();
    }
}
