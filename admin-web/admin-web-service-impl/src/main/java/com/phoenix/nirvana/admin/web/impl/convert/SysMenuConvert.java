package com.phoenix.nirvana.admin.web.impl.convert;

import com.phoenix.nirvana.admin.web.api.dto.menu.AddMenuDTO;
import com.phoenix.nirvana.admin.web.api.dto.menu.UpdateMenuDTO;
import com.phoenix.nirvana.admin.web.api.vo.menu.MenuListItemVO;
import com.phoenix.nirvana.admin.web.api.vo.menu.MenuTreeItemVO;
import com.phoenix.nirvana.admin.web.impl.dataobject.SysMenuDO;
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
