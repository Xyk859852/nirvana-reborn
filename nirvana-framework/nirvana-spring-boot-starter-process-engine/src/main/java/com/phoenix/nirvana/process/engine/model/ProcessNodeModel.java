package com.phoenix.nirvana.process.engine.model;

import com.phoenix.nirvana.process.core.enums.InvokeMethod;
import lombok.Data;

/**
 * 流程中其中一个节点的配置信息
 *
 * @author xuyongkang
 * @version 1.0
 */
@Data
public class ProcessNodeModel {
    private String name;

    private String className;

    private String nextNode;

    private Boolean begin = false;

    private InvokeMethod invokeMethod;
}
