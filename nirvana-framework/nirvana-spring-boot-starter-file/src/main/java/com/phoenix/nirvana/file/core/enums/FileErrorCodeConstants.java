package com.phoenix.nirvana.file.core.enums;

import com.phoenix.nirvana.common.exception.ErrorCode;

/**
 * 文件上传模块异常CODE
 *
 * @Description:
 * @Author: xuyongkang
 * @Date 2023/5/12 11:29
 */
public interface FileErrorCodeConstants {

    ErrorCode FILE_CLIENT_NULL = new ErrorCode(1001001001, "配置编号({}) 找不到客户端");
}
