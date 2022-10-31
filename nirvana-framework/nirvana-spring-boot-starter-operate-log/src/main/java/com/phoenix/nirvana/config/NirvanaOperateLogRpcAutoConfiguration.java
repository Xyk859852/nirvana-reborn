package com.phoenix.nirvana.config;

import com.phoenix.nirvana.service.system.rpc.logger.OperateLogApi;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * 操作日志 OperateLogApi Feign调用
 * <p>
 *
 * @author xuyongkang
 */
@Configuration
@EnableAspectJAutoProxy(proxyTargetClass = true)
@EnableFeignClients(clients = OperateLogApi.class)
public class NirvanaOperateLogRpcAutoConfiguration {
}
