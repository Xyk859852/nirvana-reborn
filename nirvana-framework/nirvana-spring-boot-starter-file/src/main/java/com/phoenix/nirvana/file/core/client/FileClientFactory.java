package com.phoenix.nirvana.file.core.client;

public interface FileClientFactory {

    /**
     * 获得文件客户端
     *
     * @param configId 配置名称
     * @return 文件客户端
     */
    FileClient getFileClient(String configId);

    /**
     * 创建文件客户端
     *
     * @param configId 配置名称
     * @param config   文件配置
     */
    <Config extends FileClientConfig> void createOrUpdateFileClient(String configId, Config config);

}
