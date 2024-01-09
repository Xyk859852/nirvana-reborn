package com.phoenix.nirvana.web.system.controller.auth;

import cn.hutool.core.util.IdUtil;
import com.phoenix.nirvana.admin.security.core.annotaion.AnonymousAccess;
import com.phoenix.nirvana.admin.security.core.bean.LoginCodeEnum;
import com.phoenix.nirvana.admin.security.core.bean.LoginProperties;
import com.phoenix.nirvana.admin.security.core.bean.SecurityProperties;
import com.phoenix.nirvana.admin.security.core.utils.SecurityFrameworkUtils;
import com.phoenix.nirvana.cache.redis.core.RedisCache;
import com.phoenix.nirvana.common.util.servlet.ServletUtils;
import com.phoenix.nirvana.common.vo.CommonResult;
import com.phoenix.nirvana.core.annotation.OperateLog;
import com.phoenix.nirvana.service.system.rpc.auth.login.domain.dto.AdminAuthenticationDTO;
import com.phoenix.nirvana.service.system.rpc.auth.login.domain.vo.AuthenticationLoginCodeVO;
import com.phoenix.nirvana.service.system.rpc.auth.login.domain.vo.AuthenticationUserVO;
import com.phoenix.nirvana.service.system.rpc.auth.login.domain.vo.LoginUserInfoVO;
import com.phoenix.nirvana.web.system.client.auth.AuthenticationRpcClient;
import com.wf.captcha.base.Captcha;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

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
    RedisCache redisCache;

    @Resource
    AuthenticationRpcClient authenticationRpcClient;

    @ApiOperation("用户登录")
    @OperateLog(enable = false)
    @AnonymousAccess
    @PostMapping("login")
    public CommonResult<AuthenticationUserVO> login(@Validated @RequestBody AdminAuthenticationDTO adminAuthenticationDTO) {
//        String cacheCode = (String) redisUtils.get(adminAuthenticationDTO.getCodeId());
//        if (!adminAuthenticationDTO.getCode().equals(cacheCode)) {
//            throw ServiceExceptionUtil.exception(CACHE_CODE_ERROR);
//        }
        AuthenticationUserVO user = authenticationRpcClient.login(adminAuthenticationDTO);
        return CommonResult.success(user);
    }

    @ApiOperation("根据token查询用户信息")
    @GetMapping("getUserInfo")
    public CommonResult<LoginUserInfoVO> getUserInfo() {
        return CommonResult.success(authenticationRpcClient.getUserInfo(SecurityFrameworkUtils.getLoginUserId()));
    }

    @ApiOperation("获取登录验证码")
    @AnonymousAccess
    @OperateLog(enable = false)
    @GetMapping("getLoginCode")
    public CommonResult<AuthenticationLoginCodeVO> getLoginCode() {
        Captcha captcha = loginProperties.getCaptcha();
        String uuid = securityProperties.getCodeKey() + ":" + IdUtil.simpleUUID();
        //当验证码类型为 arithmetic时且长度 >= 2 时，captcha.text()的结果有几率为浮点型
        String captchaValue = captcha.text();
        if (captcha.getCharType() - 1 == LoginCodeEnum.arithmetic.ordinal() && captchaValue.contains(".")) {
            captchaValue = captchaValue.split("\\.")[0];
        }
        // 保存
        redisCache.set(uuid, captchaValue, loginProperties.getLoginCode().getExpiration(), TimeUnit.MINUTES);
        return CommonResult.success(new AuthenticationLoginCodeVO().setImage(captcha.toBase64()).setUuId(uuid));
    }

    @ApiOperation("用户登出")
    @PostMapping("logout")
    public CommonResult logout() {
        String token = SecurityFrameworkUtils.obtainAuthorization(ServletUtils.getRequest(), securityProperties.getHeader());
        authenticationRpcClient.logout(token, SecurityFrameworkUtils.getLoginUserId());
        return CommonResult.success();
    }

}
