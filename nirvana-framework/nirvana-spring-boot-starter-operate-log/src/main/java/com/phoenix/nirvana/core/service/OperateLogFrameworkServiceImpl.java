package com.phoenix.nirvana.core.service;

import cn.hutool.core.bean.BeanUtil;
import com.phoenix.nirvana.service.system.rpc.logger.OperateLogApi;
import com.phoenix.nirvana.service.system.rpc.logger.domain.dto.OperateLogCreateReqDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;

/**
 * 操作日志 Framework Service 实现类
 * <p>
 * 基于 {@link OperateLogApi} 远程服务，记录操作日志
 *
 * @author 芋道源码
 */
@RequiredArgsConstructor
public class OperateLogFrameworkServiceImpl implements OperateLogFrameworkService {

    private final OperateLogApi operateLogApi;

    @Override
    @Async
    public void createOperateLog(OperateLogBO operateLogBO) {
        OperateLogCreateReqDTO reqDTO = BeanUtil.copyProperties(operateLogBO, OperateLogCreateReqDTO.class);
        operateLogApi.createOperateLog(reqDTO).checkError();
    }

}
