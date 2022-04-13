package com.phoenix.nirvana.tenant.core.db;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.extension.plugins.handler.TenantLineHandler;
import com.phoenix.nirvana.tenant.config.TenantProperties;
import com.phoenix.nirvana.tenant.core.context.TenantContextHolder;
import lombok.AllArgsConstructor;
import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.expression.LongValue;

/**
 * 基于 MyBatis Plus 多租户的功能，实现 DB 层面的多租户的功能
 *
 * @author xyk
 */
@AllArgsConstructor
public class TenantDatabaseInterceptor implements TenantLineHandler {

    private final TenantProperties properties;

    @Override
    public Expression getTenantId() {
        return new LongValue( TenantContextHolder.getRequiredTenantId());
    }

    @Override
    public boolean ignoreTable(String tableName) {
        return TenantContextHolder.isIgnore() // 情况一，全局忽略多租户
            || CollUtil.contains(properties.getIgnoreTables(), tableName); // 情况二，忽略多租户的表
    }

}
