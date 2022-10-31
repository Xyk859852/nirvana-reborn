package com.phoenix.nirvana.admin.security.core.filter;

import cn.hutool.core.util.StrUtil;
import com.phoenix.nirvana.admin.security.core.bean.SecurityProperties;
import com.phoenix.nirvana.admin.security.core.utils.SecurityFrameworkUtils;
import com.phoenix.nirvana.common.exception.ServiceException;
import com.phoenix.nirvana.common.util.json.JsonUtils;
import com.phoenix.nirvana.common.util.servlet.ServletUtils;
import com.phoenix.nirvana.common.vo.CommonResult;
import com.phoenix.nirvana.service.system.rpc.admin.OAuth2TokenApi;
import com.phoenix.nirvana.service.system.rpc.admin.domain.bo.OnlineUserBO;
import com.phoenix.nirvana.service.system.rpc.auth.login.domain.vo.LoginUserInfoVO;
import com.phoenix.nirvana.web.core.handler.GlobalExceptionHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Token 过滤器，验证 token 的有效性
 * 验证通过后，获得 {@link LoginUserInfoVO} 信息，并加入到 Spring Security 上下文
 *
 * @author xuyongkang
 */
@RequiredArgsConstructor
public class TokenAuthenticationFilter extends OncePerRequestFilter {

    private final SecurityProperties securityProperties;

    private final GlobalExceptionHandler globalExceptionHandler;

    private final OAuth2TokenApi oauth2TokenApi;

    @Override
    @SuppressWarnings("NullableProblems")
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {
        // 情况一，基于 header[login-user] 获得用户，例如说来自 Gateway 或者其它服务透传
        OnlineUserBO loginUser = buildLoginUserByHeader(request);

        // 情况二，基于 Token 获得用户
        // 注意，这里主要满足直接使用 Nginx 直接转发到 Spring Cloud 服务的场景。
        if (loginUser == null) {
            String token = SecurityFrameworkUtils.obtainAuthorization(request, securityProperties.getHeader());
            if (StrUtil.isNotEmpty(token)) {
                try {
                    // 1.1 基于 token 构建登录用户
                    loginUser = buildLoginUserByToken(token);
                } catch (Throwable ex) {
                    CommonResult<?> result = globalExceptionHandler.allExceptionHandler(request, ex);
                    ServletUtils.writeJSON(response, result);
                    return;
                }
            }
        }

        // 设置当前用户
        if (loginUser != null) {
            SecurityFrameworkUtils.setLoginUser(loginUser, request);
        }
        // 继续过滤链
        chain.doFilter(request, response);
    }

    private OnlineUserBO buildLoginUserByToken(String token) {
        try {
            // 校验访问令牌
            OnlineUserBO onlineUser = oauth2TokenApi.getOnlineUserByToken(token).getCheckedData();
            if (onlineUser == null) {
                return null;
            }
            // 用户类型不匹配，无权限
//            if (ObjectUtil.notEqual(accessToken.getUserType(), userType)) {
//                throw new AccessDeniedException("错误的用户类型");
//            }
            // 构建登录用户
            return onlineUser;
        } catch (ServiceException serviceException) {
            // 校验 Token 不通过时，考虑到一些接口是无需登录的，所以直接返回 null 即可
            return null;
        }
    }

    private OnlineUserBO buildLoginUserByHeader(HttpServletRequest request) {
        String loginUserStr = request.getHeader(SecurityFrameworkUtils.LOGIN_USER_HEADER);
        return StrUtil.isNotEmpty(loginUserStr) ? JsonUtils.parseObject(loginUserStr, OnlineUserBO.class) : null;
    }

}
