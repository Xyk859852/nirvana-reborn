package com.phoenix.nirvana.service.system.convert.permission;

import com.phoenix.nirvana.service.system.dal.mysql.dataobject.permission.SysRoleDO;
import com.phoenix.nirvana.service.system.rpc.admin.domain.dto.role.AddRoleDTO;
import com.phoenix.nirvana.service.system.rpc.admin.domain.dto.role.UpdateRoleDTO;
import com.phoenix.nirvana.service.system.rpc.admin.domain.vo.role.RoleCascade;
import com.phoenix.nirvana.service.system.rpc.admin.domain.vo.role.RolePageItemVO;
import com.phoenix.nirvana.service.system.rpc.auth.login.domain.vo.AuthenticationUserRoleVO;
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
    RolePageItemVO convertPageItem(SysRoleDO sysRole);

    @Mappings({})
    SysRoleDO convert(AddRoleDTO addRoleDTO);

    @Mappings({})
    SysRoleDO convert(UpdateRoleDTO updateRoleDTO);

    @Mappings({})
    List<RolePageItemVO> convertPageItem(List<SysRoleDO> sysRole);

    @Mappings({})
    RoleCascade convertCascade(SysRoleDO sysRoleDO);

    @Mappings({})
    List<RoleCascade> convertCascade(List<SysRoleDO> sysRoleDO);
}
