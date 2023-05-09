package com.phoenix.nirvana.service.system.convert.user;

import com.phoenix.nirvana.service.system.dal.mysql.dataobject.dept.SysDepartmentDO;
import com.phoenix.nirvana.service.system.dal.mysql.dataobject.permission.SysRoleDO;
import com.phoenix.nirvana.service.system.dal.mysql.dataobject.user.SysUserDO;
import com.phoenix.nirvana.service.system.rpc.admin.domain.bo.OnlineUserBO;
import com.phoenix.nirvana.service.system.rpc.user.domain.dto.AdminUserCreateDTO;
import com.phoenix.nirvana.service.system.rpc.user.domain.dto.AdminUserUpdateDTO;
import com.phoenix.nirvana.service.system.rpc.user.domain.vo.AdminUserPageItemVO;
import org.mapstruct.Mapper;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface SysUserConvert {

    SysUserConvert INTERFACE = Mappers.getMapper(SysUserConvert.class);

    @Mappings({})
    OnlineUserBO convert(SysUserDO userDO);

    @Mappings({})
    AdminUserPageItemVO convertPageItem(SysUserDO userDO);

    AdminUserPageItemVO.Department convertPageDeptItem(SysDepartmentDO bean);

    AdminUserPageItemVO.Role convertPageRoleItem(SysRoleDO bean);

    @Mappings({})
    List<AdminUserPageItemVO> convertPageItem(List<SysUserDO> userDO);

    @Mappings({})
    SysUserDO convert(AdminUserCreateDTO adminUserCreateDTO);

    @Mappings({})
    SysUserDO convert(AdminUserUpdateDTO adminUserUpdateDTO);
}
