package com.phoenix.nirvana.process.engine.instance;

import com.phoenix.nirvana.common.util.SpringUtil;
import com.phoenix.nirvana.process.core.process.Processor;
import com.phoenix.nirvana.process.engine.model.ProcessContextFactory;
import org.springframework.beans.BeansException;
import org.springframework.stereotype.Component;

/**
 * @author xuyongkang
 * @version 1.0
 */
@Component
public class SpringBeanInstanceCreator implements ProcessorInstanceCreator {

    @Override
    public Processor newInstance(String className, String name) throws Exception {
        Object bean;
        try {
            Class<?> clazz = Class.forName(className);
            bean = SpringUtil.getBean(clazz);
        } catch (BeansException e) {
            return ProcessContextFactory.DEFAULT_INSTANCE_CREATOR.newInstance(className, name);
        }
        if (!(bean instanceof Processor)) {
            throw new IllegalArgumentException("类" + className + "不是Processor实例");
        }
        Processor processor = (Processor) bean;
        processor.setName(name);
        return (Processor) bean;
    }
}
