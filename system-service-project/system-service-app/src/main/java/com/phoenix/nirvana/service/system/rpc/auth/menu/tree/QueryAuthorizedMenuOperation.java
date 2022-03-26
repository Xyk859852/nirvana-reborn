package com.phoenix.nirvana.service.system.rpc.auth.menu.tree;

import com.phoenix.nirvana.service.system.convert.SysMenuConvert;
import com.phoenix.nirvana.service.system.dal.mysql.dataobject.SysMenuDO;
import com.phoenix.nirvana.service.system.dal.mysql.mapper.SysMenuMapper;
import com.phoenix.nirvana.service.system.rpc.auth.menu.domain.vo.MenuTreeItemVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Scope("prototype")
public class QueryAuthorizedMenuOperation implements MenuOperation<MenuTreeItemVO> {

    @Autowired
    SysMenuMapper menuMapper;

    @Override
    public MenuTreeItemVO doExecute(SysMenuDO menuDO) {
        List<MenuTreeItemVO> menuTreeItemVOS = new ArrayList<>();
        MenuTreeItemVO treeItemVO = SysMenuConvert.INTERFACE.convertTree(menuDO);
        List<SysMenuDO> menuDOS = menuMapper.selectListByPid(menuDO.getId());
        for (SysMenuDO sysMenuDO : menuDOS) {
            MenuTreeItemVO treeItemDetail = SysMenuConvert.INTERFACE.convertTree(sysMenuDO);
            List<SysMenuDO> sysMenuDOS = menuMapper.selectListByPid(sysMenuDO.getId());
            sysMenuDO.execute(this);
            treeItemDetail.setChildren(SysMenuConvert.INTERFACE.convertTree(sysMenuDOS));
            menuTreeItemVOS.add(treeItemDetail);
        }
        treeItemVO.setChildren(menuTreeItemVOS);
        return treeItemVO;
    }

    @Override
    public void clean() {

    }
}
