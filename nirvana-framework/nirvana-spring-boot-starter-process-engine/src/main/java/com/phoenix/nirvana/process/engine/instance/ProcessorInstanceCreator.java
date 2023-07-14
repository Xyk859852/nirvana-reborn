package com.phoenix.nirvana.process.engine.instance;


import com.phoenix.nirvana.process.core.process.Processor;

/**
 * 流程节点实例化器
 *
 * @author xuyongkang
 * @version 1.0
 */
public interface ProcessorInstanceCreator {

    /**
     * 创建实例
     *
     * @param className 类名称
     * @param name      节点id
     * @return 实例化对象
     */
    Processor newInstance(String className, String name) throws Exception;

}
