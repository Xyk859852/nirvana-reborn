package com.phoenix.nirvana.admin.web.impl.convert;

import com.phoenix.nirvana.admin.web.api.vo.login.AuthenticationPermissionButtonVO;
import com.phoenix.nirvana.admin.web.api.vo.login.AuthenticationRolePermissionMenuVO;
import com.phoenix.nirvana.admin.web.impl.dataobject.SysPermissionDO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.Set;

@Mapper
public interface SysPermissionConvert {

    SysPermissionConvert INTERFACE = Mappers.getMapper(SysPermissionConvert.class);

    @Mappings({})
    List<AuthenticationPermissionButtonVO> convertButton(List<SysPermissionDO> sysPermission);

    @Mappings({@Mapping(target = "action", source = "permCode"),
            @Mapping(target = "describe", source = "title")})
    AuthenticationPermissionButtonVO convertButton(SysPermissionDO sysPermission);

    @Mappings({@Mapping(target = "permissionId", source = "permCode"),
            @Mapping(target = "permissionName", source = "title")})
    AuthenticationRolePermissionMenuVO convertMenu(SysPermissionDO sysPermission);

    @Mappings({})
    List<AuthenticationRolePermissionMenuVO> convertMenus(List<SysPermissionDO> sysPermission);

}
