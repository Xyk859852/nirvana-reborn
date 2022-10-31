package com.phoenix.nirvana.service.system.rpc.logger;

import com.phoenix.nirvana.common.vo.CommonResult;
import com.phoenix.nirvana.service.system.enums.ApiConstants;
import com.phoenix.nirvana.service.system.rpc.logger.domain.dto.OperateLogCreateReqDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

@FeignClient(name = ApiConstants.SYSTEM_SERVICE_APP)
@Api(tags = "RPC 服务 - 操作日志")
public interface OperateLogApi {

    String PREFIX = ApiConstants.SYSTEM_SERVICE_APP_PREFIX + "/operate-log";

    @PostMapping(PREFIX + "/create")
    CommonResult<Boolean> createOperateLog(@Valid @RequestBody OperateLogCreateReqDTO createReqDTO);

}