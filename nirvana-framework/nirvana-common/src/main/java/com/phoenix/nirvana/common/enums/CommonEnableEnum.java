package com.phoenix.nirvana.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 通用启用禁用状态标识
 *
 * @Description:
 * @Author: xuyongkang
 * @Date 2023/5/11 14:27
 */
@Getter
@AllArgsConstructor
public enum CommonEnableEnum {

    ENABLE(true, "正常"),
    DISABLE(false, "停用");

    /**
     * 状态值
     */
    private final Boolean enable;
    /**
     * 状态名
     */
    private final String name;
}
