package com.phoenix.nirvana.service.system.rpc.auth.permission.tree;

import com.phoenix.nirvana.service.system.dal.mysql.dataobject.permission.SysPermissionDO;

public interface PermissionOperation<T> {


    /**
     * 树形数据处理
     *
     * @param menuDO
     * @return
     */
    T doExecute(SysPermissionDO menuDO);

    /**
     * 清除内部存储对象
     */
    void clean();
}
