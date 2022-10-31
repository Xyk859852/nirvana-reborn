package com.phoenix.nirvana.config;

import com.phoenix.nirvana.core.aspect.OperateLogAspect;
import com.phoenix.nirvana.core.service.OperateLogFrameworkService;
import com.phoenix.nirvana.core.service.OperateLogFrameworkServiceImpl;
import com.phoenix.nirvana.service.system.rpc.logger.OperateLogApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 操作日志自动装配类
 * </p>
 *
 * @author xuyongkang
 */
@Configuration
public class NirvanaOperateLogAutoConfiguration {

    @Bean
    public OperateLogAspect operateLogAspect() {
        return new OperateLogAspect();
    }

    @Bean
    public OperateLogFrameworkService operateLogFrameworkService(OperateLogApi operateLogApi) {
        return new OperateLogFrameworkServiceImpl(operateLogApi);
    }

}
