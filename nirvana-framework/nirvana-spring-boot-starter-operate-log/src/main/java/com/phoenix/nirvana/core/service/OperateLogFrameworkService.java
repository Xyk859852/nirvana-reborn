package com.phoenix.nirvana.core.service;

/**
 * 操作日志 Framework Service 接口
 *
 * @author xuyongkang
 */
public interface OperateLogFrameworkService {

    /**
     * 异步记录操作日志
     *
     * @param operateLogBO 操作日志请求
     */
    void createOperateLog(OperateLogBO operateLogBO);

}
