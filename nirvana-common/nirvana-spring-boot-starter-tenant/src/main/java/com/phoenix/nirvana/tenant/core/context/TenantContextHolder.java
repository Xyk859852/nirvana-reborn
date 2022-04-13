package com.phoenix.nirvana.tenant.core.context;

import com.alibaba.ttl.TransmittableThreadLocal;
import com.phoenix.nirvana.tenant.core.aop.Tenant;

/**
 * 多租户上下文 Holder
 *
 * @author xuyongkang
 */
public class TenantContextHolder {

    /**
     * 当前租户编号
     */
    private static final ThreadLocal<Long> TENANT_ID = new TransmittableThreadLocal<>();

    /**
     * 是否忽略租户
     */
    private static final ThreadLocal<Boolean> IGNORE = new TransmittableThreadLocal<>();

    /**
     * 获得租户编号。
     *
     * @return 租户编号
     */
    public static Long getTenantId() {
        return TENANT_ID.get();
    }

    /**
     * 获得租户编号。如果不存在，则抛出 NullPointerException 异常
     *
     * @return 租户编号
     */
    public static Long getRequiredTenantId() {
        Long tenantId = getTenantId();
        if (tenantId == null) {
            throw new NullPointerException("TenantContextHolder 不存在租户编号");
        }
        return tenantId;
    }

    public static void setTenantId(Long tenantId) {
        TENANT_ID.set(tenantId);
    }

    public static void setIgnore(Boolean ignore) {
        IGNORE.set(ignore);
    }

    /**
     * 当前是否忽略租户，默认忽略，需要租户扫描需要添加{@link Tenant 注解}
     *
     * @return 是否忽略
     */
    public static boolean isIgnore() {
        if (IGNORE.get() == null) {
            return true;
        }
        return Boolean.TRUE.equals(IGNORE.get());
    }

    public static void clear() {
        TENANT_ID.remove();
        IGNORE.remove();
    }

}
