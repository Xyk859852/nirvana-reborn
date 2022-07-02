package com.phoenix.nirvana.service.system.service.dept;

import com.phoenix.nirvana.service.system.convert.dept.SysDepartmentConvert;
import com.phoenix.nirvana.service.system.dal.mysql.dataobject.dept.SysDepartmentDO;
import com.phoenix.nirvana.service.system.dal.mysql.dataobject.user.SysUserDO;
import com.phoenix.nirvana.service.system.dal.mysql.mapper.dept.SysDepartmentMapper;
import com.phoenix.nirvana.service.system.dal.mysql.mapper.user.SysUserMapper;
import com.phoenix.nirvana.service.system.rpc.dept.domain.vo.DepartmentCascade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentService {

    @Autowired
    SysUserMapper userMapper;

    @Autowired
    SysDepartmentMapper departmentMapper;

    public List<DepartmentCascade> getDepartmentsCascade(Long userId) {
        SysUserDO user = userMapper.selectById(userId);
        SysDepartmentDO department = departmentMapper.selectById(user.getDeptId());
        return SysDepartmentConvert.INTERFACE.convert(departmentMapper.selectListByLikeRightCode(department.getCode()));
    }
}
