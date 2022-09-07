package com.phoenix.nirvana.admin.security.core.rpc;

import com.phoenix.nirvana.admin.security.core.bo.LoginUser;
import com.phoenix.nirvana.admin.security.core.utils.SecurityFrameworkUtils;
import com.phoenix.nirvana.rpc.core.util.FeignUtils;
import feign.RequestInterceptor;
import feign.RequestTemplate;

/**
 * LoginUser 的 RequestInterceptor 实现类：Feign 请求时，将 {@link LoginUser} 设置到 header 中，继续透传给被调用的服务
 *
 * @author xuyogkang
 */
public class LoginUserRequestInterceptor implements RequestInterceptor {

    @Override
    public void apply(RequestTemplate requestTemplate) {
        LoginUser user = SecurityFrameworkUtils.getLoginUser();
        if (user != null) {
            FeignUtils.createJsonHeader(requestTemplate, "system-user", user);
        }
    }

}
