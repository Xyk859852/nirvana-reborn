package com.phoenix.nirvana.service.system.service.permission;

import com.phoenix.nirvana.common.exception.util.ServiceExceptionUtil;
import com.phoenix.nirvana.common.util.CollectionUtils;
import com.phoenix.nirvana.service.system.convert.permission.PermissionMenuTreeConvert;
import com.phoenix.nirvana.service.system.dal.mysql.dataobject.permission.SysPermissionDO;
import com.phoenix.nirvana.service.system.dal.mysql.mapper.permission.SysPermissionMapper;
import com.phoenix.nirvana.service.system.rpc.auth.permission.domain.dto.AddPermissionDTO;
import com.phoenix.nirvana.service.system.rpc.auth.permission.domain.dto.PermissionListDTO;
import com.phoenix.nirvana.service.system.rpc.auth.permission.domain.dto.UpdatePermissionDTO;
import com.phoenix.nirvana.service.system.rpc.auth.permission.domain.vo.PermissionMenuListItemVO;
import com.phoenix.nirvana.service.system.rpc.auth.permission.domain.vo.PermissionMenuTreeItemVO;
import com.phoenix.nirvana.service.system.rpc.auth.permission.tree.PermissionOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static com.phoenix.nirvana.service.system.enums.AdminWebCodeConstants.*;

@Service
public class SysPermissionService {

    @Autowired
    SysPermissionMapper menuMapper;

    @Resource(name = "queryAuthorizedPermissionOperation")
    PermissionOperation<PermissionMenuTreeItemVO> queryAuthorizedPermissionOperation;

    @Resource(name = "reverseQueryAuthorizedPermissionOperation")
    PermissionOperation<List<PermissionMenuTreeItemVO>> reverseQueryAuthorizedPermissionOperation;

    public List<PermissionMenuListItemVO> getPermissionList(PermissionListDTO permissionListDTO) {
        return PermissionMenuTreeConvert.INTERFACE.convert(menuMapper.selectListByPidOrLikeTitle(permissionListDTO.getKeyword(), permissionListDTO.getPid()));
    }

    public List<PermissionMenuTreeItemVO> getPermissionMenuTreeSuperior(Long id) {
        SysPermissionDO sysPermissionDO = menuMapper.selectById(id);
        if (sysPermissionDO == null) {
            throw ServiceExceptionUtil.exception(MENU_IS_NULL);
        }
        return sysPermissionDO.execute(reverseQueryAuthorizedPermissionOperation);
    }

    public List<PermissionMenuTreeItemVO> getPermissionMenuAllTree() {
        List<PermissionMenuTreeItemVO> permissionMenuTreeItemVOS = new ArrayList<>();
        List<SysPermissionDO> sysPermissionDOS = menuMapper.selectListByPid(0l);
        for (SysPermissionDO sysPermissionDO : sysPermissionDOS) {
            permissionMenuTreeItemVOS.add(sysPermissionDO.execute(queryAuthorizedPermissionOperation));
        }
        return permissionMenuTreeItemVOS;
    }

    public Boolean addPermission(AddPermissionDTO addPermissionDTO) {
        SysPermissionDO sysPermissionDO = PermissionMenuTreeConvert.INTERFACE.convert(addPermissionDTO);
        sysPermissionDO.setCreateTime(new Date());
        menuMapper.insert(sysPermissionDO);
        if (addPermissionDTO.getPid() != 0) {
            SysPermissionDO parentMenu = menuMapper.selectById(addPermissionDTO.getPid());
            parentMenu.setSubCount(parentMenu.getSubCount() + 1);
            menuMapper.updateById(parentMenu);
        }
        return true;
    }

    public Boolean updatePermission(UpdatePermissionDTO updatePermissionDTO) {
        if (updatePermissionDTO.getId().equals(updatePermissionDTO.getPid())) {
            throw ServiceExceptionUtil.exception(MENU_ID_EQUALS_PID);
        }
        if (updatePermissionDTO.getIFrame()) {
            String http = "http://", https = "https://";
            if (!(updatePermissionDTO.getUrl().toLowerCase().startsWith(http) || updatePermissionDTO.getUrl().toLowerCase().startsWith(https))) {
                throw ServiceExceptionUtil.exception(MENU_IFRAME_URL_TOP_ERROR);
            }
        }
        SysPermissionDO sysPermissionDO = menuMapper.selectById(updatePermissionDTO.getId());
        Long newPid = updatePermissionDTO.getPid();
        Long oldPid = sysPermissionDO.getPid();
        sysPermissionDO = PermissionMenuTreeConvert.INTERFACE.convert(updatePermissionDTO);
        sysPermissionDO.setUpdateTime(new Date());
        menuMapper.updateById(sysPermissionDO);
        if (!newPid.equals(oldPid)) {
            updateMenuSubCont(newPid, 1);
            updateMenuSubCont(oldPid, -1);
        }
        return true;
    }

    public Boolean deletePermission(List<Long> ids) {
        List<SysPermissionDO> sysPermissionDOS = menuMapper.selectListByIds(ids);
        sysPermissionDOS.forEach(sysMenu -> {
            if (sysMenu.getPid() != 0) {
                updateMenuSubCont(sysMenu.getPid(), -1);
            }
            recursionDeleteMenu(sysMenu.getId());
            menuMapper.deleteById(sysMenu.getId());
        });
        return true;
    }

    private void recursionDeleteMenu(Long id) {
        List<SysPermissionDO> sysPermissionDOS = menuMapper.selectListByPid(id);
        if (CollectionUtils.isAnyEmpty(sysPermissionDOS)) {
            return;
        }
        sysPermissionDOS.forEach(sysMenuDO -> recursionDeleteMenu(sysMenuDO.getId()));
        menuMapper.deleteBatchIds(sysPermissionDOS.stream().map(SysPermissionDO::getId).collect(Collectors.toSet()));
    }

    private void updateMenuSubCont(Long menuId, Integer size) {
        SysPermissionDO sysPermissionDO = menuMapper.selectById(menuId);
        if (sysPermissionDO != null) {
            sysPermissionDO.setSubCount(sysPermissionDO.getSubCount() + size);
            menuMapper.updateById(sysPermissionDO);
        }
    }
}
