package com.phoenix.nirvana.service.system.manager.logger;

import cn.hutool.core.util.StrUtil;
import com.phoenix.nirvana.common.vo.CommonResult;
import com.phoenix.nirvana.service.system.convert.logger.OperateLogConvert;
import com.phoenix.nirvana.service.system.dal.mysql.dataobject.logger.SysOperateLogDO;
import com.phoenix.nirvana.service.system.rpc.logger.domain.dto.OperateLogCreateReqDTO;
import com.phoenix.nirvana.service.system.service.logger.OperateLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static com.phoenix.nirvana.service.system.dal.mysql.dataobject.logger.SysOperateLogDO.JAVA_METHOD_ARGS_MAX_LENGTH;
import static com.phoenix.nirvana.service.system.dal.mysql.dataobject.logger.SysOperateLogDO.RESULT_MAX_LENGTH;

@Component
public class OperateLogManager {

    @Autowired
    private OperateLogService operateLogService;

    public CommonResult<Boolean> createOperateLog(OperateLogCreateReqDTO createReqDTO) {
        SysOperateLogDO logDO = OperateLogConvert.INSTANCE.convert(createReqDTO);
        logDO.setJavaMethodArgs(StrUtil.maxLength(logDO.getJavaMethodArgs(), JAVA_METHOD_ARGS_MAX_LENGTH));
        logDO.setResultData(StrUtil.maxLength(logDO.getResultData(), RESULT_MAX_LENGTH));
        operateLogService.save(logDO);
        return CommonResult.success(true);
    }




}
