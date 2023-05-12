package com.phoenix.nirvana.file.core.client;

import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.ReflectUtil;
import com.phoenix.nirvana.common.exception.util.ServiceExceptionUtil;
import com.phoenix.nirvana.file.core.enums.FileStorageEnum;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import static com.phoenix.nirvana.file.core.enums.FileErrorCodeConstants.FILE_CLIENT_NULL;

/**
 * 文件客户端的工厂实现类
 *
 * @author xuyongkang
 */
@Slf4j
public class FileClientFactoryImpl implements FileClientFactory {

    /**
     * 文件客户端 Map
     * key：配置编号
     */
    private final ConcurrentMap<String, AbstractFileClient<?>> clients = new ConcurrentHashMap<>();

    /**
     * 初始化文件客户端
     */
    public void init(Map<String, FileClientConfig> clientConfigs) {
        if (clientConfigs == null) {
            log.error("未配置文件上传客户端。。。");
            return;
        }
        log.info("文件上传客户端初始化:{}", clientConfigs);
        clientConfigs.forEach((k, v) -> {
            createOrUpdateFileClient(k, v);
        });
    }

    @Override
    public FileClient getFileClient(String configId) {
        AbstractFileClient<?> client = clients.get(configId);
        if (client == null) {
            log.error("[getFileClient][配置编号({}) 找不到客户端]", configId);
            throw ServiceExceptionUtil.exception(FILE_CLIENT_NULL, configId);
        }
        return client;
    }

    @Override
    @SuppressWarnings("unchecked")
    public <Config extends FileClientConfig> void createOrUpdateFileClient(String configId, Config config) {
        AbstractFileClient<Config> client = (AbstractFileClient<Config>) clients.get(configId);
        if (client == null) {
            client = this.createFileClient(configId, config);
            client.init();
            clients.put(client.getId(), client);
        } else {
            client.refresh(config);
        }
    }

    @SuppressWarnings("unchecked")
    private <Config extends FileClientConfig> AbstractFileClient<Config> createFileClient(String configId, Config config) {
        FileStorageEnum storageEnum = FileStorageEnum.getByConfigId(configId);
        Assert.notNull(storageEnum, String.format("文件配置(%s) 为空", storageEnum));
        // 创建客户端
        return (AbstractFileClient<Config>) ReflectUtil.newInstance(storageEnum.getClientClass(), configId, config);
    }

}
