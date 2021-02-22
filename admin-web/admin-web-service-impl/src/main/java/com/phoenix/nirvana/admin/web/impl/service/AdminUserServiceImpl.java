package com.phoenix.nirvana.admin.web.impl.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.phoenix.nirvana.admin.web.api.AdminUserService;
import com.phoenix.nirvana.admin.web.api.dto.user.AdminUserCreateDTO;
import com.phoenix.nirvana.admin.web.api.dto.user.AdminUserPageDTO;
import com.phoenix.nirvana.admin.web.api.vo.user.UserPageItemVO;
import com.phoenix.nirvana.admin.web.impl.convert.SysUserConvert;
import com.phoenix.nirvana.admin.web.impl.dataobject.SysDepartmentDO;
import com.phoenix.nirvana.admin.web.impl.dataobject.SysRoleDO;
import com.phoenix.nirvana.admin.web.impl.dataobject.SysUserDO;
import com.phoenix.nirvana.admin.web.impl.mapper.SysDepartmentMapper;
import com.phoenix.nirvana.admin.web.impl.mapper.SysRoleMapper;
import com.phoenix.nirvana.admin.web.impl.mapper.SysUserMapper;
import com.phoenix.nirvana.common.exception.util.ServiceExceptionUtil;
import com.phoenix.nirvana.common.util.CollectionUtils;
import com.phoenix.nirvana.common.util.MD5Util;
import com.phoenix.nirvana.common.vo.PageResult;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.phoenix.nirvana.admin.web.impl.enums.AdminWebCodeConstants.USER_PHONE_EXIST;

@Service
@DubboService
public class AdminUserServiceImpl implements AdminUserService {

    @Autowired
    SysUserMapper sysUserMapper;

    @Autowired
    SysDepartmentMapper departmentMapper;

    @Autowired
    SysRoleMapper roleMapper;

    @Override
    public PageResult<UserPageItemVO> getUserPageList(AdminUserPageDTO adminUserPageDTO) {
        Page<SysUserDO> sysUserPage = sysUserMapper.selectPage(adminUserPageDTO);
        if (CollectionUtils.isEmpty(sysUserPage.getRecords())) {
            return PageResult.empty();
        } else {
            List<UserPageItemVO> userPageItemVOS = new ArrayList<>();
            List<SysDepartmentDO> departments = departmentMapper.selectBatchIds(sysUserPage.getRecords().stream().map(SysUserDO::getDepartmentId).collect(Collectors.toSet()));
            List<SysRoleDO> roles = roleMapper.selectBatchIds(sysUserPage.getRecords().stream().map(SysUserDO::getRoleId).collect(Collectors.toSet()));
            Map<Long, SysDepartmentDO> departmentMap = CollectionUtils.convertMap(departments, SysDepartmentDO::getId);
            Map<Long, SysRoleDO> roleMap = CollectionUtils.convertMap(roles, SysRoleDO::getId);
            sysUserPage.getRecords().forEach(user -> {
                UserPageItemVO pageItemVO = SysUserConvert.INTERFACE.convertPageItem(user);
                SysDepartmentDO departmentDO = departmentMap.get(user.getDepartmentId());
                SysRoleDO sysRoleDO = roleMap.get(user.getRoleId());
                pageItemVO.setDepartment(new UserPageItemVO.Department().setId(departmentDO.getId()).setName(departmentDO.getName()));
                pageItemVO.setRole(new UserPageItemVO.Role().setId(sysRoleDO.getId()).setName(sysRoleDO.getName()));
                userPageItemVOS.add(pageItemVO);

            });
            return new PageResult<UserPageItemVO>()
                    .setPageNo(adminUserPageDTO.getCurrent())
                    .setPageSize(adminUserPageDTO.getSize())
                    .setTotalCount(sysUserPage.getTotal())
                    .setList(userPageItemVOS);
        }
    }

    @Override
    public void addAdminUser(AdminUserCreateDTO adminUserCreateDTO) {
        SysUserDO sysUserPhoneDO = sysUserMapper.selectOneByPhone(adminUserCreateDTO.getPhone());
        if (sysUserPhoneDO != null) {
            throw ServiceExceptionUtil.exception(USER_PHONE_EXIST);
        }
        SysUserDO userDO = SysUserConvert.INTERFACE.convert(adminUserCreateDTO);
        userDO.setPassWord(MD5Util.encryption(adminUserCreateDTO.getPhone().substring(6)));
        userDO.setCreateTime(new Date());
        sysUserMapper.insert(userDO);
    }

    @Override
    public void deleteAdminUser(List<String> ids) {
        sysUserMapper.deleteBatchIds(ids);
    }
}
