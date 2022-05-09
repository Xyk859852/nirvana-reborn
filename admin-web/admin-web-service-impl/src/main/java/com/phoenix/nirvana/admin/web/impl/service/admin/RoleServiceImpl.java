package com.phoenix.nirvana.admin.web.impl.service.admin;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.phoenix.nirvana.admin.web.api.admin.RoleService;
import com.phoenix.nirvana.admin.web.api.admin.domain.dto.role.AddRoleDTO;
import com.phoenix.nirvana.admin.web.api.admin.domain.dto.role.RolePageDTO;
import com.phoenix.nirvana.admin.web.api.admin.domain.dto.role.UpdateRoleDTO;
import com.phoenix.nirvana.admin.web.api.admin.domain.vo.role.RoleCascade;
import com.phoenix.nirvana.admin.web.api.admin.domain.vo.role.RolePageItemVO;
import com.phoenix.nirvana.admin.web.impl.convert.SysRoleConvert;
import com.phoenix.nirvana.admin.web.impl.dataobject.SysRoleDO;
import com.phoenix.nirvana.admin.web.impl.dataobject.SysRolePermissionDO;
import com.phoenix.nirvana.admin.web.impl.mapper.SysRoleMapper;
import com.phoenix.nirvana.admin.web.impl.mapper.SysRolePermissionMapper;
import com.phoenix.nirvana.common.util.CollectionUtils;
import com.phoenix.nirvana.common.vo.PageResult;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@DubboService
public class RoleServiceImpl implements RoleService {

    @Autowired
    SysRoleMapper roleMapper;

    @Autowired
    SysRolePermissionMapper rolePermissionMapper;

    @Override
    public List<RoleCascade> getRolesCascade(Long userId) {
        return SysRoleConvert.INTERFACE.convertCascade(roleMapper.selectListByCreateId(userId));
    }

    @Override
    public PageResult<RolePageItemVO> getRolePageList(RolePageDTO rolePageDTO) {
        Page<SysRoleDO> sysRolePage = roleMapper.selectPageList(rolePageDTO);
        if (CollectionUtils.isAnyEmpty(sysRolePage.getRecords())) {
            return PageResult.empty();
        }
        List<RolePageItemVO> pageItemVOS = new ArrayList<>();
        sysRolePage.getRecords().forEach(sysRoleDO -> {
            RolePageItemVO pageItemVO = SysRoleConvert.INTERFACE.convertPageItem(sysRoleDO);
            List<SysRolePermissionDO> rolePermissionDOS = rolePermissionMapper.selectListByRoleId(pageItemVO.getId());
            if (!CollectionUtils.isAnyEmpty(rolePermissionDOS)) {
                pageItemVO.setMenuIds(rolePermissionDOS.stream().map(SysRolePermissionDO::getPid).collect(Collectors.toList()));
            }
            pageItemVOS.add(pageItemVO);
        });
        return new PageResult<RolePageItemVO>()
                .setPageNo(rolePageDTO.getCurrent())
                .setPageSize(rolePageDTO.getSize())
                .setTotalCount(sysRolePage.getTotal())
                .setList(pageItemVOS);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean addRole(AddRoleDTO addRoleDTO, Long userId) {
        SysRoleDO sysRoleDO = SysRoleConvert.INTERFACE.convert(addRoleDTO);
        sysRoleDO.setCreateId(userId);
        sysRoleDO.setCreateTime(new Date());
        roleMapper.insert(sysRoleDO);
        if (!CollectionUtils.isAnyEmpty(addRoleDTO.getMenus())) {
            addRoleDTO.getMenus().forEach(menu -> rolePermissionMapper.insert(new SysRolePermissionDO().setRid(sysRoleDO.getId()).setPid(menu)));
        }
        return true;
    }

    @Override
    public Boolean updateRole(UpdateRoleDTO updateRoleDTO) {
        SysRoleDO sysRoleDO = SysRoleConvert.INTERFACE.convert(updateRoleDTO);
        sysRoleDO.setModifyTime(new Date());
        roleMapper.updateById(sysRoleDO);
        rolePermissionMapper.deleteByRoleId(sysRoleDO.getId());
        if (!CollectionUtils.isAnyEmpty(updateRoleDTO.getMenus())) {
            updateRoleDTO.getMenus().forEach(menu -> rolePermissionMapper.insert(new SysRolePermissionDO().setRid(sysRoleDO.getId()).setPid(menu)));
        }
        return true;
    }

    @Override
    public Boolean deleteRole(List<Long> ids) {
        ids.forEach(id -> {
            roleMapper.deleteById(id);
            rolePermissionMapper.deleteByRoleId(id);
        });
        return true;
    }
}
