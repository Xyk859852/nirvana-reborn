package com.phoenix.nirvana.admin.web.impl.service;

import com.phoenix.nirvana.admin.web.api.RoleService;
import com.phoenix.nirvana.admin.web.api.vo.role.RoleCascade;
import com.phoenix.nirvana.admin.web.impl.convert.SysRoleConvert;
import com.phoenix.nirvana.admin.web.impl.mapper.SysRoleMapper;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@DubboService
public class RoleServiceImpl implements RoleService {

    @Autowired
    SysRoleMapper roleMapper;

    @Override
    public List<RoleCascade> getRolesCascade(Long userId) {
        return SysRoleConvert.INTERFACE.convertCascade(roleMapper.selectListByCreateId(userId));
    }
}
