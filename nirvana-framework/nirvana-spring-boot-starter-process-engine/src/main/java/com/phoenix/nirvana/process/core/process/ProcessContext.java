package com.phoenix.nirvana.process.core.process;

import com.phoenix.nirvana.process.core.enums.InvokeMethod;
import com.phoenix.nirvana.process.core.node.ProcessorDefinition;
import com.phoenix.nirvana.process.core.node.ProcessorNode;
import com.phoenix.nirvana.process.core.utils.ProcessorUtils;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * 流程上下文
 *
 * @author xuyongkang
 * @version 1.0
 */
@Slf4j
public class ProcessContext {

    private final Map<String, Object> params = new HashMap<>();

    private final ProcessorDefinition processorDefinition;

    private final Stack<RollbackProcessor> rollbackProcessors = new Stack<>();

    public ProcessContext(ProcessorDefinition processorDefinition) {
        this.processorDefinition = processorDefinition;
    }

    public void set(String key, Object value) {
        params.put(key, value);
    }

    @SuppressWarnings("unchecked")
    public <T> T get(String key) {
        return (T) params.get(key);
    }

    /**
     * 启动
     */
    public void start() {
        process(processorDefinition.getFirst());
    }

    private void process(ProcessorNode node) {
        if (node == null) {
            return;
        }
        Processor processor = node.getProcessor();
        try {
            if (processor instanceof RollbackProcessor) {
                rollbackProcessors.push((RollbackProcessor) processor);
            }
            processor.process(this);
        } catch (Exception e) {
            // 回滚前面的所有可回滚节点，按照所有节点的顺序倒叙回滚
            RollbackProcessor rollbackProcessor;
            while (!rollbackProcessors.empty()) {
                rollbackProcessor = rollbackProcessors.pop();
                try {
                    rollbackProcessor.rollback(this);
                } catch (Exception e1) {
                    // 如果回滚过程中也抛异常了，此时暂时没有什么好的办法处理。
                    log.warn("流程节点在回滚过程中抛出异常，process={}, context={}",
                            rollbackProcessor.getName(), params.values());
                }
            }
            processor.caughtException(this, e);
            // 流程出现问题的时候，需要将异常往外抛，让业务方处理.
            throw e;
        }
        ProcessorNode nextNode = null;
        Map<String, ProcessorNode> nextNodes = node.getNextNodes();
        if (processor instanceof DynamicProcessor) {
            // 动态计算出下一个节点
            DynamicProcessor dynamicProcessor = (DynamicProcessor) processor;
            String nextNodeId = dynamicProcessor.nextNodeId(this);
            if (!nextNodes.containsKey(nextNodeId)) {
                throw new IllegalArgumentException("DynamicProcess can not find next node with id:" + nextNodeId);
            }
            nextNode = nextNodes.get(nextNodeId);
        } else {
            // 不是动态节点，每个节点只有一个后继节点
            if (!nextNodes.isEmpty()) {
                nextNode = nextNodes.values().stream().findAny().get();
            }
        }

        InvokeMethod invokeMethod = node.getInvokeMethod();
        if (invokeMethod.equals(InvokeMethod.SYNC)) {
            process(nextNode);
        } else {
            ProcessorNode finalNextNode = nextNode;
            ProcessorUtils.executeAsync(() -> process(finalNextNode));
        }
    }
}
