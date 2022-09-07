package com.phoenix.nirvana.service.system.rpc.admin;

import com.phoenix.nirvana.common.vo.CommonResult;
import com.phoenix.nirvana.service.system.enums.ApiConstants;
import com.phoenix.nirvana.service.system.rpc.admin.domain.bo.OnlineUserBO;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = ApiConstants.NAME)
public interface OAuth2TokenApi {

    String PREFIX = ApiConstants.PREFIX + "/oauth2";

    @GetMapping(PREFIX + "/getOnlineUserByToken")
    @ApiOperation("根据token查询当前用户")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "请求token", example = "1", required = true, dataTypeClass = String.class)
    })
    CommonResult<OnlineUserBO> getOnlineUserByToken(@RequestParam(name = "token") String token);

    @GetMapping(PREFIX + "/getOnlineUserByUserName")
    @ApiOperation("根据用户名查询用户")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userName", value = "用户名称", example = "1", required = true, dataTypeClass = String.class)
    })
    CommonResult<OnlineUserBO> getOnlineUserByUserName(@RequestParam(name = "userName") String userName);
}
