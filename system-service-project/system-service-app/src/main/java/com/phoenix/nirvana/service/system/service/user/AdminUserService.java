package com.phoenix.nirvana.service.system.service.user;

import com.phoenix.nirvana.common.exception.util.ServiceExceptionUtil;
import com.phoenix.nirvana.common.util.CollectionUtils;
import com.phoenix.nirvana.common.vo.PageResult;
import com.phoenix.nirvana.service.system.convert.user.SysUserConvert;
import com.phoenix.nirvana.service.system.dal.mysql.dataobject.dept.SysDepartmentDO;
import com.phoenix.nirvana.service.system.dal.mysql.dataobject.permission.SysRoleDO;
import com.phoenix.nirvana.service.system.dal.mysql.dataobject.user.SysUserDO;
import com.phoenix.nirvana.service.system.dal.mysql.mapper.dept.SysDepartmentMapper;
import com.phoenix.nirvana.service.system.dal.mysql.mapper.permission.SysRoleMapper;
import com.phoenix.nirvana.service.system.dal.mysql.mapper.user.SysUserMapper;
import com.phoenix.nirvana.service.system.rpc.user.domain.dto.AdminUserCreateDTO;
import com.phoenix.nirvana.service.system.rpc.user.domain.dto.AdminUserPageDTO;
import com.phoenix.nirvana.service.system.rpc.user.domain.dto.AdminUserUpdateDTO;
import com.phoenix.nirvana.service.system.rpc.user.domain.vo.AdminUserPageItemVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import static com.phoenix.nirvana.common.util.CollectionUtils.convertList;
import static com.phoenix.nirvana.common.util.CollectionUtils.convertMap;
import static com.phoenix.nirvana.service.system.enums.AdminWebCodeConstants.USER_PHONE_EXIST;

@Service
public class AdminUserService {

    @Autowired
    SysUserMapper sysUserMapper;

    @Autowired
    SysDepartmentMapper departmentMapper;

    @Autowired
    SysRoleMapper roleMapper;

    @Autowired
    PasswordEncoder passwordEncoder;

    public PageResult<AdminUserPageItemVO> getUserPageList(AdminUserPageDTO adminUserPageDTO) {
        PageResult<SysUserDO> pageResult = sysUserMapper.selectPageList(adminUserPageDTO);
        if (CollectionUtils.isAnyEmpty(pageResult.getList())) {
            return new PageResult(pageResult.getTotalCount());
        }
        List<AdminUserPageItemVO> userPageItemVOS = new ArrayList<>();
        Collection<Long> deptIds = convertList(pageResult.getList(), SysUserDO::getDeptId);
        List<SysDepartmentDO> departments = departmentMapper.selectBatchIds(deptIds);
        Collection<Long> roleIds = convertList(pageResult.getList(), SysUserDO::getRoleId);
        List<SysRoleDO> roles = roleMapper.selectBatchIds(roleIds);
        Map<Long, SysDepartmentDO> departmentMap = convertMap(departments, SysDepartmentDO::getId);
        Map<Long, SysRoleDO> roleMap = convertMap(roles, SysRoleDO::getId);
        pageResult.getList().forEach(user -> {
            AdminUserPageItemVO pageItemVO = SysUserConvert.INTERFACE.convertPageItem(user);
            pageItemVO.setDepartment(SysUserConvert.INTERFACE.convertPageDeptItem(departmentMap.get(user.getDeptId())));
            pageItemVO.setRole((SysUserConvert.INTERFACE.convertPageRoleItem(roleMap.get(user.getRoleId()))));
            userPageItemVOS.add(pageItemVO);
        });
        return new PageResult(userPageItemVOS, pageResult.getTotalCount(),
                pageResult.getPageNo(), pageResult.getPageSize(),pageResult.getTotalPage());
    }

    public Boolean createAdminUser(AdminUserCreateDTO adminUserCreateDTO) {
        checkPhoneExist(adminUserCreateDTO.getPhone(), null);
        SysUserDO userDO = SysUserConvert.INTERFACE.convert(adminUserCreateDTO);
        userDO.setPassword(passwordEncoder.encode(adminUserCreateDTO.getPhone().substring(5)));
        return sysUserMapper.insert(userDO) > 0;
    }

    public Boolean updateAdminUser(AdminUserUpdateDTO adminUserUpdateDTO) {
        checkPhoneExist(adminUserUpdateDTO.getPhone(), adminUserUpdateDTO.getId());
        SysUserDO userDO = SysUserConvert.INTERFACE.convert(adminUserUpdateDTO);
        return sysUserMapper.updateById(userDO) > 0;
    }

    public Boolean deleteAdminUser(List<Long> ids) {
        return sysUserMapper.deleteBatchIds(ids) > 0;
    }

    /**
     * 判断当前手机号是已经否在,排除当前用户
     *
     * @param phone
     */
    private void checkPhoneExist(String phone, Long userId) {
        SysUserDO sysUserPhoneDO = sysUserMapper.selectOneByPhone(phone, userId);
        if (sysUserPhoneDO != null) {
            throw ServiceExceptionUtil.exception(USER_PHONE_EXIST);
        }
    }
}
