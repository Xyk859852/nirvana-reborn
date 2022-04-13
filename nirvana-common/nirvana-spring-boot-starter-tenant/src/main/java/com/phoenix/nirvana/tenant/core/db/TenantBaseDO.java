package com.phoenix.nirvana.tenant.core.db;

import com.phoenix.nirvana.mybatis.core.dataobject.BaseDO;
import lombok.Data;

/**
 * 拓展多租户的 BaseDO 基类
 *
 * @author 芋道源码
 */
@Data
public abstract class TenantBaseDO extends BaseDO {

    /**
     * 多租户编号
     */
    private Long tenantId;

}
