package com.phoenix.nirvana.admin.web.api.auth.menu;

import com.phoenix.nirvana.admin.web.api.auth.menu.domain.dto.AddMenuDTO;
import com.phoenix.nirvana.admin.web.api.auth.menu.domain.dto.MenuListDTO;
import com.phoenix.nirvana.admin.web.api.auth.menu.domain.dto.UpdateMenuDTO;
import com.phoenix.nirvana.admin.web.api.auth.menu.domain.vo.MenuListItemVO;
import com.phoenix.nirvana.admin.web.api.auth.menu.domain.vo.MenuTreeItemVO;

import java.util.List;

public interface MenuService {

    /**
     * 菜单列表数据查询
     *
     * @param menuListDTO
     * @return
     */
    List<MenuListItemVO> getMenuList(MenuListDTO menuListDTO);

    /**
     * 根据 id 获取上级菜单列表 tree 树结构格式返回
     *
     * @param id
     * @return
     */
    List<MenuTreeItemVO> getMenuTreeSuperior(Long id);

    /**
     * 获取所有的菜单数据，tree 树结构格式返回
     * @return
     */
    List<MenuTreeItemVO> getMenuAllTree();

    /**
     * 新增菜单
     *
     * @param addMenuDTO
     * @return
     */
    Boolean addMenu(AddMenuDTO addMenuDTO);

    /**
     * 修改菜单
     *
     * @param updateMenuDTO
     * @return
     */
    Boolean updateMenu(UpdateMenuDTO updateMenuDTO);

    /**
     * 根据主键id 删除对应菜单。并且所关联的子集菜单也一并删除
     *
     * @param ids
     * @return
     */
    Boolean deleteMenu(List<Long> ids);
}
