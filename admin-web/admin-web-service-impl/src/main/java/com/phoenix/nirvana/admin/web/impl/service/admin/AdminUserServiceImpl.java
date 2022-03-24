package com.phoenix.nirvana.admin.web.impl.service.admin;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.phoenix.nirvana.admin.web.api.admin.AdminUserService;
import com.phoenix.nirvana.admin.web.api.admin.domain.dto.user.AdminUserCreateDTO;
import com.phoenix.nirvana.admin.web.api.admin.domain.dto.user.AdminUserPageDTO;
import com.phoenix.nirvana.admin.web.api.admin.domain.dto.user.AdminUserUpdateDTO;
import com.phoenix.nirvana.admin.web.api.admin.domain.vo.user.UserPageItemVO;
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

    @SentinelResource(value = "AdminUserService:getUserPageList")
    @Override
    public PageResult<UserPageItemVO> getUserPageList(AdminUserPageDTO adminUserPageDTO) {
        Page<SysUserDO> sysUserPage = sysUserMapper.selectPageList(adminUserPageDTO);
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
                    .setTotalCount(Math.toIntExact(sysUserPage.getTotal()))
                    .setList(userPageItemVOS);
        }
    }

    @Override
    public void addAdminUser(AdminUserCreateDTO adminUserCreateDTO) {
        checkPhoneExist(adminUserCreateDTO.getPhone(), null);
        SysUserDO userDO = SysUserConvert.INTERFACE.convert(adminUserCreateDTO);
        userDO.setPassWord(MD5Util.encryption(adminUserCreateDTO.getPhone().substring(6)));
        userDO.setCreateTime(new Date());
        sysUserMapper.insert(userDO);
    }

    @Override
    public void updateAdminUser(AdminUserUpdateDTO adminUserUpdateDTO) {
        checkPhoneExist(adminUserUpdateDTO.getPhone(), adminUserUpdateDTO.getId());
        SysUserDO userDO = SysUserConvert.INTERFACE.convert(adminUserUpdateDTO);
        sysUserMapper.updateById(userDO);
    }

    @Override
    public void deleteAdminUser(List<Long> ids) {
        sysUserMapper.deleteBatchIds(ids);
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
