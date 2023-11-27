package com.phoenix.nirvana.service.system.enums;


import com.phoenix.nirvana.common.enums.RpcConstants;

/**
 * API 相关的枚举
 *
 * @author xuyongkang
 */
public class ApiConstants {

    /**
     * 服务名
     * <p>
     * 注意，需要保证和 spring.application.name 保持一致
     */
    public static final String SYSTEM_SERVICE_APP = "system-service-biz";

    public static final String SYSTEM_SERVICE_APP_PREFIX = RpcConstants.RPC_API_PREFIX + "/system";

    public static final String VERSION = "1.0.0";

}
