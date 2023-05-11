package com.phoenix.nirvana.service.system.service.permission;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ArrayUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.phoenix.nirvana.common.exception.util.ServiceExceptionUtil;
import com.phoenix.nirvana.common.util.CollectionUtils;
import com.phoenix.nirvana.common.vo.PageResult;
import com.phoenix.nirvana.service.system.convert.permission.PermissionMenuTreeConvert;
import com.phoenix.nirvana.service.system.dal.mysql.dataobject.permission.SysPermissionDO;
import com.phoenix.nirvana.service.system.dal.mysql.dataobject.user.SysUserDO;
import com.phoenix.nirvana.service.system.dal.mysql.mapper.permission.SysPermissionMapper;
import com.phoenix.nirvana.service.system.dal.mysql.mapper.user.SysUserMapper;
import com.phoenix.nirvana.service.system.rpc.auth.permission.domain.dto.AddPermissionDTO;
import com.phoenix.nirvana.service.system.rpc.auth.permission.domain.dto.PermissionListDTO;
import com.phoenix.nirvana.service.system.rpc.auth.permission.domain.dto.UpdatePermissionDTO;
import com.phoenix.nirvana.service.system.rpc.auth.permission.domain.vo.PermissionMenuListItemVO;
import com.phoenix.nirvana.service.system.rpc.auth.permission.domain.vo.PermissionMenuTreeItemVO;
import com.phoenix.nirvana.service.system.rpc.auth.permission.tree.PermissionOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

import static com.phoenix.nirvana.service.system.enums.AdminWebCodeConstants.*;

@Service
public class SysPermissionService {

    @Autowired
    SysPermissionMapper permissionMapper;

    @Autowired
    SysUserMapper userMapper;

    @Resource(name = "queryAuthorizedPermissionOperation")
    PermissionOperation<PermissionMenuTreeItemVO> queryAuthorizedPermissionOperation;

    @Resource(name = "reverseQueryAuthorizedPermissionOperation")
    PermissionOperation<List<PermissionMenuTreeItemVO>> reverseQueryAuthorizedPermissionOperation;

    public PageResult<PermissionMenuListItemVO> getPermissionList(PermissionListDTO permissionListDTO) {
        Page<SysPermissionDO> sysPermissionDOPage = permissionMapper.selectListByPidOrLikeTitle(permissionListDTO);
        if (CollectionUtils.isAnyEmpty(sysPermissionDOPage.getRecords())) {
            return PageResult.empty();
        }
        return new PageResult<PermissionMenuListItemVO>().setPageNo(permissionListDTO.getPageNo()).setPageSize(permissionListDTO.getPageSize()).setTotalCount(sysPermissionDOPage.getTotal()).setData(PermissionMenuTreeConvert.INTERFACE.convert(sysPermissionDOPage.getRecords()));
    }

    public List<PermissionMenuTreeItemVO> getPermissionMenuTreeSuperior(Long id) {
        SysPermissionDO sysPermissionDO = permissionMapper.selectById(id);
        if (sysPermissionDO == null) {
            throw ServiceExceptionUtil.exception(MENU_IS_NULL);
        }
        return sysPermissionDO.execute(reverseQueryAuthorizedPermissionOperation);
    }

    public List<PermissionMenuTreeItemVO> getPermissionMenuAllTree() {
        List<PermissionMenuTreeItemVO> permissionMenuTreeItemVOS = new ArrayList<>();
        List<SysPermissionDO> sysPermissionDOS = permissionMapper.selectListByPid(0l);
        for (SysPermissionDO sysPermissionDO : sysPermissionDOS) {
            permissionMenuTreeItemVOS.add(sysPermissionDO.execute(queryAuthorizedPermissionOperation));
        }
        return permissionMenuTreeItemVOS;
    }

    public Set<String> getUserPermissionCodes(Long roleId) {
        return permissionMapper.selectPermissionCodes(roleId);
    }

    public Boolean createPermission(AddPermissionDTO addPermissionDTO) {
        SysPermissionDO sysPermissionDO = PermissionMenuTreeConvert.INTERFACE.convert(addPermissionDTO);
        sysPermissionDO.setCreateTime(new Date());
        checkIFrame(addPermissionDTO.getIFrame(), addPermissionDTO.getUrl());
        permissionMapper.insert(sysPermissionDO);
        if (addPermissionDTO.getPid() != 0) {
            SysPermissionDO parentMenu = permissionMapper.selectById(addPermissionDTO.getPid());
            parentMenu.setSubCount(parentMenu.getSubCount() + 1);
            permissionMapper.updateById(parentMenu);
        }
        return true;
    }

    public Boolean updatePermission(UpdatePermissionDTO updatePermissionDTO) {
        if (updatePermissionDTO.getId().equals(updatePermissionDTO.getPid())) {
            throw ServiceExceptionUtil.exception(MENU_ID_EQUALS_PID);
        }
        checkIFrame(updatePermissionDTO.getIFrame(), updatePermissionDTO.getUrl());
        SysPermissionDO sysPermissionDO = permissionMapper.selectById(updatePermissionDTO.getId());
        Long newPid = updatePermissionDTO.getPid();
        Long oldPid = sysPermissionDO.getPid();
        sysPermissionDO = PermissionMenuTreeConvert.INTERFACE.convert(updatePermissionDTO);
        sysPermissionDO.setUpdateTime(new Date());
        permissionMapper.updateById(sysPermissionDO);
        if (!newPid.equals(oldPid)) {
            updateMenuSubCont(newPid, 1);
            updateMenuSubCont(oldPid, -1);
        }
        return true;
    }

    public Boolean deletePermission(List<Long> ids) {
        List<SysPermissionDO> sysPermissionDOS = permissionMapper.selectListByIds(ids);
        sysPermissionDOS.forEach(sysMenu -> {
            if (sysMenu.getPid() != 0) {
                updateMenuSubCont(sysMenu.getPid(), -1);
            }
            recursionDeleteMenu(sysMenu.getId());
            permissionMapper.deleteById(sysMenu.getId());
        });
        return true;
    }

    private void recursionDeleteMenu(Long id) {
        List<SysPermissionDO> sysPermissionDOS = permissionMapper.selectListByPid(id);
        if (CollectionUtils.isAnyEmpty(sysPermissionDOS)) {
            return;
        }
        sysPermissionDOS.forEach(sysMenuDO -> recursionDeleteMenu(sysMenuDO.getId()));
        permissionMapper.deleteBatchIds(sysPermissionDOS.stream().map(SysPermissionDO::getId).collect(Collectors.toSet()));
    }

    private void updateMenuSubCont(Long menuId, Integer size) {
        SysPermissionDO sysPermissionDO = permissionMapper.selectById(menuId);
        if (sysPermissionDO != null) {
            sysPermissionDO.setSubCount(sysPermissionDO.getSubCount() + size);
            permissionMapper.updateById(sysPermissionDO);
        }
    }

    private void checkIFrame(boolean iframe, String url) {
        if (iframe) {
            String http = "http://", https = "https://";
            if (!(url.toLowerCase().startsWith(http) || url.toLowerCase().startsWith(https))) {
                throw ServiceExceptionUtil.exception(MENU_IFRAME_URL_TOP_ERROR);
            }
        }
    }

    public Boolean hasAnyRoles(Long userId, String... roles) {
        return null;

    }

    public Boolean hasAnyPermissions(Long userId, String[] permissions) {
        if (ArrayUtil.isEmpty(permissions)) {
            return true;
        }
        SysUserDO userDO = userMapper.selectById(userId);
        Set<String> perCodes = getUserPermissionCodes(userDO.getRoleId());
        return Arrays.stream(permissions).anyMatch(role -> CollUtil.contains(perCodes, role));
    }
}
