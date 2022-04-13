package com.phoenix.nirvana.tenant.core.aop;

import java.lang.annotation.*;

/**
 * 需要租户扫描，标记指定方法进行租户字段自动拼接
 *
 * @author xuyongkang
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface Tenant {
}
