package com.phoenix.nirvana.process.core.utils;


import com.phoenix.nirvana.process.core.node.ProcessorNode;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author xuyongkang
 * @version 1.0
 */
public class ProcessorUtils {

    private static ExecutorService DEFAULT_POOL = new ThreadPoolExecutor(0, Integer.MAX_VALUE,
            60, TimeUnit.MICROSECONDS, new SynchronousQueue<>());

    public static boolean hasRing(ProcessorNode node) {
        return hasRing(node, new HashSet<>());
    }

    private static boolean hasRing(ProcessorNode node, Set<String> idSet) {
        Map<String, ProcessorNode> nextNodes = node.getNextNodes();
        if (nextNodes == null || nextNodes.isEmpty()) {
            return false;
        } else {
            idSet.add(node.getName());
            boolean ret = false;
            for (Map.Entry<String, ProcessorNode> entry : nextNodes.entrySet()) {
                ProcessorNode value = entry.getValue();
                if (idSet.contains(value.getName())) {
                    return true;
                } else {
                    idSet.add(value.getName());
                    ret = ret || hasRing(value, new HashSet<>(idSet));
                }
            }
            return ret;
        }
    }

    public static void executeAsync(Runnable runnable) {
        DEFAULT_POOL.execute(runnable);
    }

}
