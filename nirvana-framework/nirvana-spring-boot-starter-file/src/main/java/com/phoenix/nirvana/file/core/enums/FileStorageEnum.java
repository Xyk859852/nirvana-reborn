package com.phoenix.nirvana.file.core.enums;

import cn.hutool.core.util.ArrayUtil;
import com.phoenix.nirvana.file.core.client.FileClient;
import com.phoenix.nirvana.file.core.client.FileClientConfig;
import com.phoenix.nirvana.file.core.client.db.DBFileClient;
import com.phoenix.nirvana.file.core.client.db.DBFileClientConfig;
import com.phoenix.nirvana.file.core.client.s3.S3FileClient;
import com.phoenix.nirvana.file.core.client.s3.S3FileClientConfig;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 文件存储器枚举
 *
 * @author xuyongkang
 */
@AllArgsConstructor
@Getter
public enum FileStorageEnum {

    DB("DBFileClientConfig", DBFileClientConfig.class, DBFileClient.class),

//    LOCAL(10, LocalFileClientConfig.class, LocalFileClient.class),
//    FTP(11, FtpFileClientConfig.class, FtpFileClient.class),
//    SFTP(12, SftpFileClientConfig.class, SftpFileClient.class),

    S3("s3FileClientConfig", S3FileClientConfig.class, S3FileClient.class),
    ;

    /**
     * 存储器
     */
    private final String configId;

    /**
     * 配置类
     */
    private final Class<? extends FileClientConfig> configClass;
    /**
     * 客户端类
     */
    private final Class<? extends FileClient> clientClass;

    public static FileStorageEnum getByConfigId(String configId) {
        return ArrayUtil.firstMatch(o -> o.getConfigId().equals(configId), values());
    }

}
