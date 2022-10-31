package com.phoenix.nirvana.service.system.convert.permission;

import com.phoenix.nirvana.service.system.dal.mysql.dataobject.permission.SysRoleDO;
import com.phoenix.nirvana.service.system.rpc.role.domain.dto.AddRoleDTO;
import com.phoenix.nirvana.service.system.rpc.role.domain.dto.UpdateRoleDTO;
import com.phoenix.nirvana.service.system.rpc.role.domain.vo.RoleCascade;
import com.phoenix.nirvana.service.system.rpc.role.domain.vo.RolePageItemVO;
import com.phoenix.nirvana.service.system.rpc.auth.login.domain.vo.LoginUserRoleVO;
import org.mapstruct.Mapper;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface SysRoleConvert {

    SysRoleConvert INTERFACE = Mappers.getMapper(SysRoleConvert.class);

    @Mappings({})
    LoginUserRoleVO convert(SysRoleDO sysRole);

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
