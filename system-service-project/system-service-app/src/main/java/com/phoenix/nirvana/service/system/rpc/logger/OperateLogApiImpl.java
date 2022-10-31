package com.phoenix.nirvana.service.system.rpc.logger;

import com.phoenix.nirvana.common.vo.CommonResult;
import com.phoenix.nirvana.service.system.manager.logger.OperateLogManager;
import com.phoenix.nirvana.service.system.rpc.logger.domain.dto.OperateLogCreateReqDTO;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@DubboService
@RestController
public class OperateLogApiImpl implements OperateLogApi {

    @Autowired
    OperateLogManager operateLogManager;

    @Override
    public CommonResult<Boolean> createOperateLog(OperateLogCreateReqDTO createReqDTO) {
        return operateLogManager.createOperateLog(createReqDTO);
    }
}
