package com.phoenix.nirvana.service.system.rpc.admin;

import com.phoenix.nirvana.common.vo.CommonResult;
import com.phoenix.nirvana.service.system.enums.ApiConstants;
import com.phoenix.nirvana.service.system.rpc.admin.domain.bo.OnlineUserBO;
import com.phoenix.nirvana.service.system.rpc.auth.login.domain.vo.LoginUserInfoVO;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = ApiConstants.SYSTEM_SERVICE_APP)
public interface OAuth2TokenApi {

    String PREFIX = ApiConstants.SYSTEM_SERVICE_APP_PREFIX + "/oauth2";

    String REDIS_PREFIX = "online-token:";

    String URL_CHECK = "http://" + ApiConstants.SYSTEM_SERVICE_APP + PREFIX + "/getOnlineUserByToken";

    @GetMapping(PREFIX + "/getOnlineUserByToken")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "请求token", example = "1", required = true, dataTypeClass = String.class)
    })
    CommonResult<OnlineUserBO> getOnlineUserByToken(@RequestParam(name = "token") String token);

}
