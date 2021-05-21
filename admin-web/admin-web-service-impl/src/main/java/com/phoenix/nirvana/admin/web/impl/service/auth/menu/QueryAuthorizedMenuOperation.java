package com.phoenix.nirvana.admin.web.impl.service.auth.menu;

import com.phoenix.nirvana.admin.web.api.auth.menu.domain.vo.MenuTreeItemVO;
import com.phoenix.nirvana.admin.web.impl.convert.SysMenuConvert;
import com.phoenix.nirvana.admin.web.impl.dataobject.SysMenuDO;
import com.phoenix.nirvana.admin.web.impl.mapper.SysMenuMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
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
