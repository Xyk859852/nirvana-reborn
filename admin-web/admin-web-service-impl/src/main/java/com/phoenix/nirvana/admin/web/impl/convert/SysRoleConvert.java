package com.phoenix.nirvana.admin.web.impl.convert;

import com.phoenix.nirvana.admin.web.api.vo.login.AuthenticationUserRoleVO;
import com.phoenix.nirvana.admin.web.api.vo.role.RoleCascade;
import com.phoenix.nirvana.admin.web.impl.dataobject.SysRoleDO;
import org.mapstruct.Mapper;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface SysRoleConvert {

    SysRoleConvert INTERFACE = Mappers.getMapper(SysRoleConvert.class);

    @Mappings({})
    AuthenticationUserRoleVO convert(SysRoleDO sysRole);

    @Mappings({})
    RoleCascade convertCascade(SysRoleDO sysRoleDO);

    @Mappings({})
    List<RoleCascade> convertCascade(List<SysRoleDO> sysRoleDO);
}
