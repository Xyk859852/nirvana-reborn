package com.phoenix.nirvana.service.system.rpc.config;

import com.phoenix.nirvana.common.vo.CommonResult;
import com.phoenix.nirvana.service.system.rpc.config.domain.dto.ConfigPageDTO;

/**
 * @Description: 平台系统参数配置
 * @Author: xuyongkang
 * @Date 2023/2/20 11:46 AM
 */
public interface ConfigRpc {

    CommonResult getConfigPageResult(ConfigPageDTO configPageDTO);
}
