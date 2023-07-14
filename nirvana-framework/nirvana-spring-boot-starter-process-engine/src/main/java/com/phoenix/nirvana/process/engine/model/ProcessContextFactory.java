package com.phoenix.nirvana.process.engine.model;

import com.phoenix.nirvana.process.core.node.ProcessorDefinition;
import com.phoenix.nirvana.process.core.process.ProcessContext;
import com.phoenix.nirvana.process.engine.instance.ProcessorInstanceCreator;
import com.phoenix.nirvana.process.engine.instance.ReflectNodeInstanceCreator;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author xuyongkang
 * @version 1.0
 */
@Slf4j
public class ProcessContextFactory {

    public static final ProcessorInstanceCreator DEFAULT_INSTANCE_CREATOR = new ReflectNodeInstanceCreator();
    private List<ProcessModel> modelList;
    private final Map<String, ProcessorDefinition> processorDefinitionMap = new ConcurrentHashMap<>();
    private final ProcessorInstanceCreator instanceCreator;

    public ProcessContextFactory(List<ProcessModel> modeList) throws Exception {
        this(modeList, DEFAULT_INSTANCE_CREATOR);
    }

    public ProcessContextFactory(List<ProcessModel> modeList, ProcessorInstanceCreator instanceCreator) throws Exception {
        this.modelList = modeList;
        this.instanceCreator = instanceCreator;
        init();
    }

    private void init() throws Exception {
        for (ProcessModel processModel : modelList) {
            processModel.check();
        }
        for (ProcessModel processModel : modelList) {
            ProcessorDefinition processorDefinition = processModel.build(instanceCreator);
            log.info("构造流程成功：\n{}", processorDefinition.toStr());
            processorDefinitionMap.put(processorDefinition.getName(), processorDefinition);
        }
    }

    public ProcessContext getContext(String name) {
        ProcessorDefinition processorDefinition = processorDefinitionMap.get(name);
        if (processorDefinition == null) {
            throw new IllegalArgumentException("流程不存在");
        }
        return new ProcessContext(processorDefinition);
    }

    public void refresh(List<ProcessModel> modeList) throws Exception {
        synchronized (this) {
            this.modelList = modeList;
            init();
        }
    }

}
