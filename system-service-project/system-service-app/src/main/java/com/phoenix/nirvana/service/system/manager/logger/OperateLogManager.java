package com.phoenix.nirvana.service.system.manager.logger;

import com.phoenix.nirvana.common.vo.CommonResult;
import com.phoenix.nirvana.service.system.rpc.logger.domain.dto.OperateLogCreateReqDTO;
import com.phoenix.nirvana.service.system.service.logger.OperateLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OperateLogManager {

    @Autowired
    private OperateLogService operateLogService;

    public CommonResult<Boolean> createOperateLog(OperateLogCreateReqDTO createReqDTO) {
        operateLogService.createOperateLog(createReqDTO);
        return CommonResult.success(true);
    }

}
