package com.phoenix.nirvana.admin.web.impl.convert;

import com.phoenix.nirvana.admin.web.api.auth.login.domain.vo.AuthenticationPermissionButtonVO;
import com.phoenix.nirvana.admin.web.api.auth.login.domain.vo.AuthenticationRolePermissionMenuVO;
import com.phoenix.nirvana.admin.web.impl.dataobject.SysMenuDO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface SysPermissionConvert {

    SysPermissionConvert INTERFACE = Mappers.getMapper(SysPermissionConvert.class);

    @Mappings({})
    List<AuthenticationPermissionButtonVO> convertButton(List<SysMenuDO> sysPermission);

    @Mappings({@Mapping(target = "action", source = "permCode"),
            @Mapping(target = "describe", source = "title")})
    AuthenticationPermissionButtonVO convertButton(SysMenuDO sysPermission);

    @Mappings({@Mapping(target = "permissionId", source = "permCode"),
            @Mapping(target = "permissionName", source = "title")})
    AuthenticationRolePermissionMenuVO convertMenu(SysMenuDO sysPermission);

    @Mappings({})
    List<AuthenticationRolePermissionMenuVO> convertMenus(List<SysMenuDO> sysPermission);

}
