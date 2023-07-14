package com.phoenix.nirvana.process.core.process;

/**
 * 可回滚的流程
 *
 * @author xuyongkang
 * @version 1.0
 */
public abstract class RollbackProcessor extends AbstractProcessor {

    /**
     * 回滚操作
     *
     * @param context 上下文
     */
    protected abstract void rollback(ProcessContext context);

}
