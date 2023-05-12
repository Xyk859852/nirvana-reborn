package com.phoenix.nirvana.file.core.client.listener;

import com.phoenix.nirvana.file.core.client.FileClientConfig;
import com.phoenix.nirvana.file.core.client.FileClientFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.scope.refresh.RefreshScopeRefreshedEvent;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;

import java.util.Map;

/**
 * 文件客户端配置文件监听
 *
 * @Description:
 * @Author: xuyongkang
 * @Date 2023/5/12 11:47
 */
@Slf4j
@Configuration
public class FileClientRefreshListener {

    @Autowired
    Map<String, FileClientConfig> clientConfigs;

    @Autowired
    FileClientFactory fileClientFactory;

    @EventListener
    public void refresh(RefreshScopeRefreshedEvent event) {
        clientConfigs.forEach((k, v) -> fileClientFactory.createOrUpdateFileClient(k, v));
    }
}
