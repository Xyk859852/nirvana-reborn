package com.phoenix.nirvana.admin.web.impl.convert;

import com.phoenix.nirvana.admin.web.api.admin.domain.dto.role.AddRoleDTO;
import com.phoenix.nirvana.admin.web.api.admin.domain.dto.role.UpdateRoleDTO;
import com.phoenix.nirvana.admin.web.api.admin.domain.vo.role.RoleCascade;
import com.phoenix.nirvana.admin.web.api.admin.domain.vo.role.RolePageItemVO;
import com.phoenix.nirvana.admin.web.api.auth.login.domain.vo.AuthenticationUserRoleVO;
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
