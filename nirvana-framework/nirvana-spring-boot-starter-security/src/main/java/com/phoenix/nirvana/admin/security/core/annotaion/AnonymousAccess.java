package com.phoenix.nirvana.admin.security.core.annotaion;


import java.lang.annotation.*;

/**
 * 用于标记不需要spring security验证的请求
 */
@Inherited
@Documented
@Target({ElementType.METHOD, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface AnonymousAccess {
}
