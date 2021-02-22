package com.phoenix.nirvana.admin.web.impl.convert;

import com.phoenix.nirvana.admin.web.api.bo.OnlineUserBO;
import com.phoenix.nirvana.admin.web.api.dto.user.AdminUserCreateDTO;
import com.phoenix.nirvana.admin.web.api.vo.user.UserPageItemVO;
import com.phoenix.nirvana.admin.web.impl.dataobject.SysUserDO;
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
}
