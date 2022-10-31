package com.phoenix.nirvana.service.system.convert.permission;

import com.phoenix.nirvana.service.system.dal.mysql.dataobject.permission.SysPermissionDO;
import com.phoenix.nirvana.service.system.rpc.auth.login.domain.vo.LoginUserPermissionButtonVO;
import com.phoenix.nirvana.service.system.rpc.auth.login.domain.vo.LoginUserRolePermissionMenuVO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface SysPermissionConvert {

    SysPermissionConvert INTERFACE = Mappers.getMapper(SysPermissionConvert.class);

    @Mappings({})
    List<LoginUserPermissionButtonVO> convertButton(List<SysPermissionDO> sysPermission);

    @Mappings({@Mapping(target = "action", source = "permCode"),
            @Mapping(target = "describe", source = "title")})
    LoginUserPermissionButtonVO convertButton(SysPermissionDO sysPermission);

    @Mappings({@Mapping(target = "permissionId", source = "permCode"),
            @Mapping(target = "permissionName", source = "title")})
    LoginUserRolePermissionMenuVO convertMenu(SysPermissionDO sysPermission);

    @Mappings({})
    List<LoginUserRolePermissionMenuVO> convertMenus(List<SysPermissionDO> sysPermission);

}
