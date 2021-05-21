package com.phoenix.nirvana.admin.web.impl.service.auth.menu;

import com.phoenix.nirvana.admin.web.impl.dataobject.SysMenuDO;

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
