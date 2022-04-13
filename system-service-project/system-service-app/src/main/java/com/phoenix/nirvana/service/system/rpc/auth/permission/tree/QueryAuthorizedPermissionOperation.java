package com.phoenix.nirvana.service.system.rpc.auth.permission.tree;

import com.phoenix.nirvana.service.system.convert.permission.PermissionMenuTreeConvert;
import com.phoenix.nirvana.service.system.dal.mysql.dataobject.permission.SysPermissionDO;
import com.phoenix.nirvana.service.system.dal.mysql.mapper.permission.SysPermissionMapper;
import com.phoenix.nirvana.service.system.rpc.auth.permission.domain.vo.PermissionMenuTreeItemVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Scope("prototype")
public class QueryAuthorizedPermissionOperation implements PermissionOperation<PermissionMenuTreeItemVO> {

    @Autowired
    SysPermissionMapper menuMapper;

    @Override
    public PermissionMenuTreeItemVO doExecute(SysPermissionDO menuDO) {
        List<PermissionMenuTreeItemVO> permissionMenuTreeItemVOS = new ArrayList<>();
        PermissionMenuTreeItemVO treeItemVO = PermissionMenuTreeConvert.INTERFACE.convertTree(menuDO);
        List<SysPermissionDO> menuDOS = menuMapper.selectListByPid(menuDO.getId());
        for (SysPermissionDO sysPermissionDO : menuDOS) {
            PermissionMenuTreeItemVO treeItemDetail = PermissionMenuTreeConvert.INTERFACE.convertTree(sysPermissionDO);
            List<SysPermissionDO> sysPermissionDOS = menuMapper.selectListByPid(sysPermissionDO.getId());
            sysPermissionDO.execute(this);
            treeItemDetail.setChildren(PermissionMenuTreeConvert.INTERFACE.convertTree(sysPermissionDOS));
            permissionMenuTreeItemVOS.add(treeItemDetail);
        }
        treeItemVO.setChildren(permissionMenuTreeItemVOS);
        return treeItemVO;
    }

    @Override
    public void clean() {

    }
}
