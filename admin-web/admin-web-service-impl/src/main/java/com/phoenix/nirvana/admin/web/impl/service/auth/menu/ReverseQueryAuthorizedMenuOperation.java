package com.phoenix.nirvana.admin.web.impl.service.auth.menu;

import com.phoenix.nirvana.admin.web.api.auth.menu.domain.vo.MenuTreeItemVO;
import com.phoenix.nirvana.admin.web.impl.convert.SysMenuConvert;
import com.phoenix.nirvana.admin.web.impl.dataobject.SysMenuDO;
import com.phoenix.nirvana.admin.web.impl.mapper.SysMenuMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;


@Component
public class ReverseQueryAuthorizedMenuOperation implements MenuOperation<List<MenuTreeItemVO>> {

    @Autowired
    SysMenuMapper menuMapper;

    private MenuTreeItemVO sourceMenuTreeItem;

    @Override
    public List<MenuTreeItemVO> doExecute(SysMenuDO menuDO) {
        if (menuDO.getPid() == 0l) {
            List<MenuTreeItemVO> itemVOList = SysMenuConvert.INTERFACE.convertTree(menuMapper.selectListByPid(0l));
            MenuTreeItemVO menuTree = itemVOList.stream().filter(item -> item.getId().equals(menuDO.getId())).findFirst().get();
            itemVOList.remove(menuTree);
            itemVOList.add(0, this.sourceMenuTreeItem);
            return itemVOList;
        }
        SysMenuDO parentMenu = menuMapper.selectById(menuDO.getPid());
        if (this.sourceMenuTreeItem == null) {
            this.sourceMenuTreeItem = SysMenuConvert.INTERFACE.convertTree(parentMenu);
            this.sourceMenuTreeItem.setChildren(Arrays.asList(SysMenuConvert.INTERFACE.convertTree(menuDO)));
        } else {
            MenuTreeItemVO copyMenuTreeItemVO = this.sourceMenuTreeItem;
            MenuTreeItemVO parentTree = SysMenuConvert.INTERFACE.convertTree(parentMenu);
            this.sourceMenuTreeItem = parentTree.setChildren(Arrays.asList(copyMenuTreeItemVO));
        }
        return parentMenu.execute(this);
    }

    /**
     * 清除上次记录
     */
    public void clean() {
        this.sourceMenuTreeItem = null;
    }


}
