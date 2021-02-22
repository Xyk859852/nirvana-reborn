package com.phoenix.nirvana.admin.web.impl.service;

import com.phoenix.nirvana.admin.web.api.DepartmentService;
import com.phoenix.nirvana.admin.web.api.vo.department.DepartmentCascade;
import com.phoenix.nirvana.admin.web.impl.convert.SysDepartmentConvert;
import com.phoenix.nirvana.admin.web.impl.dataobject.SysDepartmentDO;
import com.phoenix.nirvana.admin.web.impl.dataobject.SysUserDO;
import com.phoenix.nirvana.admin.web.impl.mapper.SysDepartmentMapper;
import com.phoenix.nirvana.admin.web.impl.mapper.SysUserMapper;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@DubboService
public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    SysUserMapper userMapper;

    @Autowired
    SysDepartmentMapper departmentMapper;

    @Override
    public List<DepartmentCascade> getDepartmentsCascade(Long userId) {
        SysUserDO user = userMapper.selectById(userId);
        SysDepartmentDO department = departmentMapper.selectById(user.getDepartmentId());
        return SysDepartmentConvert.INTERFACE.convert(departmentMapper.selectListByLikeRightCode(department.getCode()));
    }
}
