package com.phoenix.nirvana.service.system.convert.permission;

import com.phoenix.nirvana.service.system.dal.mysql.dataobject.permission.SysPermissionDO;
import com.phoenix.nirvana.service.system.rpc.auth.permission.domain.dto.AddPermissionDTO;
import com.phoenix.nirvana.service.system.rpc.auth.permission.domain.dto.UpdatePermissionDTO;
import com.phoenix.nirvana.service.system.rpc.auth.permission.domain.vo.PermissionMenuListItemVO;
import com.phoenix.nirvana.service.system.rpc.auth.permission.domain.vo.PermissionMenuTreeItemVO;
import org.mapstruct.Mapper;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface PermissionMenuTreeConvert {

    PermissionMenuTreeConvert INTERFACE = Mappers.getMapper(PermissionMenuTreeConvert.class);

    @Mappings({})
    PermissionMenuListItemVO convert(SysPermissionDO menuDO);

    @Mappings({})
    PermissionMenuTreeItemVO convertTree(SysPermissionDO menuDO);

    @Mappings({})
    SysPermissionDO convert(AddPermissionDTO addPermissionDTO);

    @Mappings({})
    SysPermissionDO convert(UpdatePermissionDTO updatePermissionDTO);

    @Mappings({})
    List<PermissionMenuListItemVO> convert(List<SysPermissionDO> menuDO);

    @Mappings({})
    List<PermissionMenuTreeItemVO> convertTree(List<SysPermissionDO> menuDO);


}
