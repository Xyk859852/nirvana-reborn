package com.phoenix.nirvana.process.engine.instance;


import com.phoenix.nirvana.process.core.process.Processor;

/**
 * 基于反射创建实例
 *
 * @author xuyongkang
 * @version 1.0
 */
public class ReflectNodeInstanceCreator implements ProcessorInstanceCreator {

    @Override
    public Processor newInstance(String className, String name) throws Exception {
        Class<?> clazz = Class.forName(className);
        Object o = clazz.newInstance();
        if (!(o instanceof Processor)) {
            throw new IllegalArgumentException("类" + className + "不是Processor实例");
        }
        Processor processor = (Processor) o;
        processor.setName(name);
        return (Processor) o;
    }
}