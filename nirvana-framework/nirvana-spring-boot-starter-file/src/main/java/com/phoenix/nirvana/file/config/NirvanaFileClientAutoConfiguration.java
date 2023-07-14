package com.phoenix.nirvana.file.config;

import com.phoenix.nirvana.file.core.client.FileClientConfig;
import com.phoenix.nirvana.file.core.client.FileClientFactory;
import com.phoenix.nirvana.file.core.client.FileClientFactoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;

import java.util.Map;

/**
 * Nirvana file 客户端自动注入配置
 *
 * @Description:
 * @Author: xuyongkang
 * @Date 2023/5/12 11:37
 */
public class NirvanaFileClientAutoConfiguration {

    @Autowired(required = false)
    private Map<String, FileClientConfig> clientConfigs;

    @Bean(name = "fileClientFactory")
    public FileClientFactory fileClientFactory() {
        FileClientFactoryImpl factory = new FileClientFactoryImpl();
        factory.init(clientConfigs);
        return factory;
    }

}
