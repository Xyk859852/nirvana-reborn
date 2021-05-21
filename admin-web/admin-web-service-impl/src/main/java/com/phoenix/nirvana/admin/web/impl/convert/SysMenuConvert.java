package com.phoenix.nirvana.admin.web.impl.convert;

import com.phoenix.nirvana.admin.web.api.auth.menu.domain.dto.AddMenuDTO;
import com.phoenix.nirvana.admin.web.api.auth.menu.domain.dto.UpdateMenuDTO;
import com.phoenix.nirvana.admin.web.api.auth.menu.domain.vo.MenuListItemVO;
import com.phoenix.nirvana.admin.web.api.auth.menu.domain.vo.MenuTreeItemVO;
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
