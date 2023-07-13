package com.phoenix.nirvana.process.engine.annoations;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * @author xuyongkang
 * @version 1.0
 */

@Documented
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Import({ProcessNodeRegistrar.class, ComponentScanConfig.class})
public @interface EnableProcessEngine {

    /**
     * 配置文件名称
     */
    String value();

}
