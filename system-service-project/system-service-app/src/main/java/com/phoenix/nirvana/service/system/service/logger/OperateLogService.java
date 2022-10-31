package com.phoenix.nirvana.service.system.service.logger;

import cn.hutool.core.util.StrUtil;
import com.phoenix.nirvana.service.system.convert.logger.OperateLogConvert;
import com.phoenix.nirvana.service.system.dal.mysql.dataobject.logger.SysOperateLogDO;
import com.phoenix.nirvana.service.system.dal.mysql.mapper.logger.SysOperateLogMapper;
import com.phoenix.nirvana.service.system.rpc.logger.domain.dto.OperateLogCreateReqDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.phoenix.nirvana.service.system.dal.mysql.dataobject.logger.SysOperateLogDO.JAVA_METHOD_ARGS_MAX_LENGTH;
import static com.phoenix.nirvana.service.system.dal.mysql.dataobject.logger.SysOperateLogDO.RESULT_MAX_LENGTH;

/**
 * <p>
 * 操作日志记录 服务实现类
 * </p>
 *
 * @author xuyongkang
 * @since 2022-10-20
 */
@Service
public class OperateLogService {

    @Autowired
    SysOperateLogMapper operateLogMapper;

    public Boolean createOperateLog(OperateLogCreateReqDTO createReqDTO) {
        SysOperateLogDO logDO = OperateLogConvert.INSTANCE.convert(createReqDTO);
        logDO.setJavaMethodArgs(StrUtil.maxLength(logDO.getJavaMethodArgs(), JAVA_METHOD_ARGS_MAX_LENGTH));
        logDO.setResultData(StrUtil.maxLength(logDO.getResultData(), RESULT_MAX_LENGTH));
        operateLogMapper.insert(logDO);
        return null;
    }

}
