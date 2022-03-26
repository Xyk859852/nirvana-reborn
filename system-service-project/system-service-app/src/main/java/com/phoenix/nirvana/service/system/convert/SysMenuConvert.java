package com.phoenix.nirvana.service.system.convert;

import com.phoenix.nirvana.service.system.dal.mysql.dataobject.SysMenuDO;
import com.phoenix.nirvana.service.system.rpc.auth.menu.domain.dto.AddMenuDTO;
import com.phoenix.nirvana.service.system.rpc.auth.menu.domain.dto.UpdateMenuDTO;
import com.phoenix.nirvana.service.system.rpc.auth.menu.domain.vo.MenuListItemVO;
import com.phoenix.nirvana.service.system.rpc.auth.menu.domain.vo.MenuTreeItemVO;
import org.mapstruct.Mapper;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface SysMenuConvert {

    SysMenuConvert INTERFACE = Mappers.getMapper(SysMenuConvert.class);

    @Mappings({})
    MenuListItemVO convert(SysMenuDO menuDO);

    @Mappings({})
    MenuTreeItemVO convertTree(SysMenuDO menuDO);

    @Mappings({})
    SysMenuDO convert(AddMenuDTO addMenuDTO);

    @Mappings({})
    SysMenuDO convert(UpdateMenuDTO updateMenuDTO);

    @Mappings({})
    List<MenuListItemVO> convert(List<SysMenuDO> menuDO);

    @Mappings({})
    List<MenuTreeItemVO> convertTree(List<SysMenuDO> menuDO);


}
