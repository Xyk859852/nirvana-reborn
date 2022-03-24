package com.phoenix.nirvana.admin.web.impl.service.auth.login;

import com.phoenix.nirvana.admin.web.api.auth.login.AuthenticationService;
import com.phoenix.nirvana.admin.web.api.auth.login.domain.dto.AdminAuthenticationDTO;
import com.phoenix.nirvana.admin.web.api.auth.login.domain.vo.*;
import com.phoenix.nirvana.admin.web.impl.convert.SysPermissionConvert;
import com.phoenix.nirvana.admin.web.impl.convert.SysRoleConvert;
import com.phoenix.nirvana.admin.web.impl.dataobject.SysMenuDO;
import com.phoenix.nirvana.admin.web.impl.dataobject.SysRoleDO;
import com.phoenix.nirvana.admin.web.impl.dataobject.SysRolePermissionDO;
import com.phoenix.nirvana.admin.web.impl.dataobject.SysUserDO;
import com.phoenix.nirvana.admin.web.impl.mapper.*;
import com.phoenix.nirvana.cache.redis.utils.RedisUtils;
import com.phoenix.nirvana.common.constant.CommonNirvanaConstants;
import com.phoenix.nirvana.common.exception.util.ServiceExceptionUtil;
import com.phoenix.nirvana.common.util.MD5Util;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.phoenix.nirvana.admin.web.impl.enums.AdminWebCodeConstants.*;

@Service
@DubboService
public class AuthenticationServiceImpl implements AuthenticationService {

    @Autowired
    SysUserMapper userMapper;

    @Autowired
    SysRoleMapper roleMapper;

    @Autowired
    SysDepartmentMapper departmentMapper;

    @Autowired
    SysRolePermissionMapper sysRolePermissionMapper;

    @Autowired
    SysMenuMapper sysMenuMapper;

    @Autowired
    RedisUtils redisUtils;


    @Override
    public AuthenticationLoginCodeVO getLoginCode() {
        return null;
    }

    @Override
    public AuthenticationUserVO login(AdminAuthenticationDTO adminAuthenticationDTO) {
        String cacheCode = (String) redisUtils.get(adminAuthenticationDTO.getCodeId());
        if (!adminAuthenticationDTO.getCode().equals(cacheCode)) {
            throw ServiceExceptionUtil.exception(CACHE_CODE_ERROR);
        }
        redisUtils.del(adminAuthenticationDTO.getCodeId());
        SysUserDO user = userMapper.selectOneByUserName(adminAuthenticationDTO.getUsername());
        if (user == null) {
            throw ServiceExceptionUtil.exception(USER_IS_NULL);
        }
        if (user.getIsEnable()) {
            throw ServiceExceptionUtil.exception(USER_IS_ENABLE);
        }
        if (!user.getPassWord().equals(MD5Util.encryption(adminAuthenticationDTO.getPassword()))) {
            throw ServiceExceptionUtil.exception(USER_PASSWORD_ERROR);
        }
        return new AuthenticationUserVO().setId(user.getId());
    }

    @Override
    public AuthenticationUserInfoVO getUserInfo(Long userId) {
        SysUserDO sysUser = userMapper.selectById(userId);
        SysRoleDO sysRole = roleMapper.selectById(sysUser.getRoleId());
        List<SysRolePermissionDO> sysRolePermissions = sysRolePermissionMapper.selectListByRoleId(sysUser.getRoleId());
        //当前角色没有资源的时候，直接返回用户信息，不进行菜单资源获取
        if (sysRolePermissions == null || sysRolePermissions.isEmpty()) {
            return new AuthenticationUserInfoVO().setPhone(sysUser.getUserName()).setName(sysUser.getUserName()).setRole(SysRoleConvert.INTERFACE.convert(sysRole));
        }
        List<SysMenuDO> permissions = sysMenuMapper.selectListByIds(sysRolePermissions.stream().map(SysRolePermissionDO::getPid).collect(Collectors.toSet()));
        List<SysMenuDO> buttons = permissions.stream().filter(permission -> permission.getType().equals(CommonNirvanaConstants.PERMISSION_BUTTON)).collect(Collectors.toList());
        permissions.removeAll(buttons);
        //根据上级id把按钮数据归类
        Map<Long, List<SysMenuDO>> collect = buttons.stream().collect(Collectors.groupingBy(SysMenuDO::getPid));
        //把按钮数据放入到父菜单
        List<AuthenticationRolePermissionMenuVO> authenticationRolePermissionMenuVOS = SysPermissionConvert.INTERFACE.convertMenus(permissions);
        permissions.parallelStream().filter(menu -> collect.containsKey(menu.getId()))
                .forEach(sysPermission -> authenticationRolePermissionMenuVOS.stream()
                        .filter(menuFilter -> menuFilter.getPermissionId().equals(sysPermission.getPermCode()))
                        .forEach(menusVO -> menusVO.setActions(SysPermissionConvert.INTERFACE.convertButton(collect.get(sysPermission.getId()))))
                );
        return new AuthenticationUserInfoVO()
                .setId(sysUser.getId())
                .setName(sysUser.getUserName())
                .setPhone(sysUser.getPhone())
                .setRole(new AuthenticationUserRoleVO()
                        .setId(sysRole.getId())
                        .setName(sysRole.getName())
                        .setPermissions(authenticationRolePermissionMenuVOS)
                );
    }
}
