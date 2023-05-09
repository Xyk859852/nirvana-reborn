package com.phoenix.nirvana.service.system.rpc.auth.permission.tree;

import com.phoenix.nirvana.service.system.convert.permission.PermissionMenuTreeConvert;
import com.phoenix.nirvana.service.system.dal.mysql.dataobject.permission.SysPermissionDO;
import com.phoenix.nirvana.service.system.dal.mysql.mapper.permission.SysPermissionMapper;
import com.phoenix.nirvana.service.system.rpc.auth.permission.domain.vo.PermissionMenuTreeItemVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
@Scope("prototype")
public class ReverseQueryAuthorizedPermissionOperation implements PermissionOperation<List<PermissionMenuTreeItemVO>> {

    @Autowired
    SysPermissionMapper menuMapper;

    private PermissionMenuTreeItemVO sourceMenuTreeItem;

    @Override
    public List<PermissionMenuTreeItemVO> doExecute(SysPermissionDO menuDO) {
        if (menuDO.getPid() == 0l) {
            List<PermissionMenuTreeItemVO> itemVOList = PermissionMenuTreeConvert.INTERFACE.convertTree(menuMapper.selectListByPid(0l));
            PermissionMenuTreeItemVO menuTree = itemVOList.stream().filter(item -> item.getId().equals(menuDO.getId())).findFirst().get();
            itemVOList.remove(menuTree);
            itemVOList.add(0, this.sourceMenuTreeItem);
            return itemVOList;
        }
        SysPermissionDO parentMenu = menuMapper.selectById(menuDO.getPid());
        if (this.sourceMenuTreeItem == null) {
            this.sourceMenuTreeItem = PermissionMenuTreeConvert.INTERFACE.convertTree(parentMenu);
            this.sourceMenuTreeItem.setChildren(Arrays.asList(PermissionMenuTreeConvert.INTERFACE.convertTree(menuDO)));
        } else {
            PermissionMenuTreeItemVO copyPermissionMenuTreeItemVO = this.sourceMenuTreeItem;
            PermissionMenuTreeItemVO parentTree = PermissionMenuTreeConvert.INTERFACE.convertTree(parentMenu);
            this.sourceMenuTreeItem = parentTree.setChildren(Arrays.asList(copyPermissionMenuTreeItemVO));
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

