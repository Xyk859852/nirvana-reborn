package com.phoenix.nirvana.service.system.service.role;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.phoenix.nirvana.common.util.CollectionUtils;
import com.phoenix.nirvana.common.vo.PageResult;
import com.phoenix.nirvana.service.system.convert.permission.SysRoleConvert;
import com.phoenix.nirvana.service.system.dal.mysql.dataobject.permission.SysRoleDO;
import com.phoenix.nirvana.service.system.dal.mysql.dataobject.permission.SysRolePermissionDO;
import com.phoenix.nirvana.service.system.dal.mysql.mapper.permission.SysRoleMapper;
import com.phoenix.nirvana.service.system.dal.mysql.mapper.permission.SysRolePermissionMapper;
import com.phoenix.nirvana.service.system.rpc.role.domain.dto.AddRoleDTO;
import com.phoenix.nirvana.service.system.rpc.role.domain.dto.RolePageDTO;
import com.phoenix.nirvana.service.system.rpc.role.domain.dto.UpdateRoleDTO;
import com.phoenix.nirvana.service.system.rpc.role.domain.vo.RoleCascade;
import com.phoenix.nirvana.service.system.rpc.role.domain.vo.RolePageItemVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoleService {

    @Autowired
    SysRoleMapper roleMapper;

    @Autowired
    SysRolePermissionMapper rolePermissionMapper;

    public List<RoleCascade> getRolesCascade(Long userId) {
        return SysRoleConvert.INTERFACE.convertCascade(roleMapper.selectListByCreateId(userId));
    }

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
                pageItemVO.setPermissions(rolePermissionDOS.stream().map(SysRolePermissionDO::getPid).collect(Collectors.toList()));
            }
            pageItemVOS.add(pageItemVO);
        });
        return new PageResult<RolePageItemVO>()
                .setPageNo(rolePageDTO.getPageNo())
                .setPageSize(rolePageDTO.getPageSize())
                .setTotalCount(sysRolePage.getTotal())
                .setData(pageItemVOS);
    }

    public Boolean createRole(AddRoleDTO addRoleDTO, Long userId) {
        SysRoleDO sysRoleDO = SysRoleConvert.INTERFACE.convert(addRoleDTO);
        sysRoleDO.setCreator(userId);
        roleMapper.insert(sysRoleDO);
        if (!CollectionUtils.isAnyEmpty(addRoleDTO.getPermissions())) {
            addRoleDTO.getPermissions().forEach(menu -> rolePermissionMapper.insert(new SysRolePermissionDO().setRid(sysRoleDO.getId()).setPid(menu)));
        }
        return true;
    }

    public Boolean updateRole(UpdateRoleDTO updateRoleDTO) {
        SysRoleDO sysRoleDO = SysRoleConvert.INTERFACE.convert(updateRoleDTO);
        roleMapper.updateById(sysRoleDO);
        rolePermissionMapper.deleteByRoleId(sysRoleDO.getId());
        if (!CollectionUtils.isAnyEmpty(updateRoleDTO.getPermissions())) {
            updateRoleDTO.getPermissions().forEach(menu -> rolePermissionMapper.insert(new SysRolePermissionDO().setRid(sysRoleDO.getId()).setPid(menu)));
        }
        return true;
    }

    public Boolean deleteRole(List<Long> ids) {
        ids.forEach(id -> {
            roleMapper.deleteById(id);
            rolePermissionMapper.deleteByRoleId(id);
        });
        return true;
    }
}
