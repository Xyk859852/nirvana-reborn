package com.phoenix.nirvana.service.system.rpc.auth.menu.tree;

import com.phoenix.nirvana.service.system.dal.mysql.dataobject.SysMenuDO;

public interface MenuOperation<T> {


    /**
     * 树形数据处理
     *
     * @param menuDO
     * @return
     */
    T doExecute(SysMenuDO menuDO);

    /**
     * 清除内部存储对象
     */
    void clean();
}
