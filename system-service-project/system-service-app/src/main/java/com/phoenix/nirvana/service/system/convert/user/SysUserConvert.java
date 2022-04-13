package com.phoenix.nirvana.service.system.convert.user;

import com.phoenix.nirvana.service.system.dal.mysql.dataobject.user.SysUserDO;
import com.phoenix.nirvana.service.system.rpc.admin.domain.bo.OnlineUserBO;
import com.phoenix.nirvana.service.system.rpc.admin.domain.dto.user.AdminUserCreateDTO;
import com.phoenix.nirvana.service.system.rpc.admin.domain.dto.user.AdminUserUpdateDTO;
import com.phoenix.nirvana.service.system.rpc.admin.domain.vo.user.UserPageItemVO;
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
    UserPageItemVO convertPageItem(SysUserDO userDO);

    @Mappings({})
    List<UserPageItemVO> convertPageItem(List<SysUserDO> userDO);

    @Mappings({})
    SysUserDO convert(AdminUserCreateDTO adminUserCreateDTO);

    @Mappings({})
    SysUserDO convert(AdminUserUpdateDTO adminUserUpdateDTO);
}
