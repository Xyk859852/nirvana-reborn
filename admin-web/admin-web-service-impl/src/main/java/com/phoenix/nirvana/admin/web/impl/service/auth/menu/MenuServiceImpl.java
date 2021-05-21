package com.phoenix.nirvana.admin.web.impl.service.auth.menu;

import com.phoenix.nirvana.admin.web.api.auth.menu.MenuService;
import com.phoenix.nirvana.admin.web.api.auth.menu.domain.dto.AddMenuDTO;
import com.phoenix.nirvana.admin.web.api.auth.menu.domain.dto.MenuListDTO;
import com.phoenix.nirvana.admin.web.api.auth.menu.domain.dto.UpdateMenuDTO;
import com.phoenix.nirvana.admin.web.api.auth.menu.domain.vo.MenuListItemVO;
import com.phoenix.nirvana.admin.web.api.auth.menu.domain.vo.MenuTreeItemVO;
import com.phoenix.nirvana.admin.web.impl.convert.SysMenuConvert;
import com.phoenix.nirvana.admin.web.impl.dataobject.SysMenuDO;
import com.phoenix.nirvana.admin.web.impl.mapper.SysMenuMapper;
import com.phoenix.nirvana.common.exception.util.ServiceExceptionUtil;
import com.phoenix.nirvana.common.util.CollectionUtils;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static com.phoenix.nirvana.admin.web.impl.enums.AdminWebCodeConstants.*;

@Service
@DubboService
public class MenuServiceImpl implements MenuService {

    @Autowired
    SysMenuMapper menuMapper;

    @Resource(name = "queryAuthorizedMenuOperation")
    MenuOperation<MenuTreeItemVO> queryAuthorizedMenuOperation;

    @Resource(name = "reverseQueryAuthorizedMenuOperation")
    MenuOperation<List<MenuTreeItemVO>> reverseQueryAuthorizedMenuOperation;

    @Override
    public List<MenuListItemVO> getMenuList(MenuListDTO menuListDTO) {
        return SysMenuConvert.INTERFACE.convert(menuMapper.selectListByPidOrLikeTitle(menuListDTO.getKeyword(), menuListDTO.getPid()));
    }

    @Override
    public List<MenuTreeItemVO> getMenuTreeSuperior(Long id) {
        SysMenuDO sysMenuDO = menuMapper.selectById(id);
        if (sysMenuDO == null) {
            throw ServiceExceptionUtil.exception(MENU_IS_NULL);
        }
        reverseQueryAuthorizedMenuOperation.clean();
        return sysMenuDO.execute(reverseQueryAuthorizedMenuOperation);
    }

    @Override
    public List<MenuTreeItemVO> getMenuAllTree() {
        List<MenuTreeItemVO> menuTreeItemVOS = new ArrayList<>();
        List<SysMenuDO> sysMenuDOS = menuMapper.selectListByPid(0l);
        for (SysMenuDO sysMenuDO : sysMenuDOS) {
            menuTreeItemVOS.add(sysMenuDO.execute(queryAuthorizedMenuOperation));
        }
        return menuTreeItemVOS;
    }

    @Override
    public Boolean addMenu(AddMenuDTO addMenuDTO) {
        SysMenuDO sysMenuDO = SysMenuConvert.INTERFACE.convert(addMenuDTO);
        sysMenuDO.setCreateTime(new Date());
        menuMapper.insert(sysMenuDO);
        if (addMenuDTO.getPid() != 0) {
            SysMenuDO parentMenu = menuMapper.selectById(addMenuDTO.getPid());
            parentMenu.setSubCount(parentMenu.getSubCount() + 1);
            menuMapper.updateById(parentMenu);
        }
        return true;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean updateMenu(UpdateMenuDTO updateMenuDTO) {
        if (updateMenuDTO.getId().equals(updateMenuDTO.getPid())) {
            throw ServiceExceptionUtil.exception(MENU_ID_EQUALS_PID);
        }
        if (updateMenuDTO.getIFrame()) {
            String http = "http://", https = "https://";
            if (!(updateMenuDTO.getUrl().toLowerCase().startsWith(http) || updateMenuDTO.getUrl().toLowerCase().startsWith(https))) {
                throw ServiceExceptionUtil.exception(MENU_IFRAME_URL_TOP_ERROR);
            }
        }
        SysMenuDO sysMenuDO = menuMapper.selectById(updateMenuDTO.getId());
        Long newPid = updateMenuDTO.getPid();
        Long oldPid = sysMenuDO.getPid();
        sysMenuDO = SysMenuConvert.INTERFACE.convert(updateMenuDTO);
        sysMenuDO.setModifyTime(new Date());
        menuMapper.updateById(sysMenuDO);
        if (!newPid.equals(oldPid)) {
            updateMenuSubCont(newPid, 1);
            updateMenuSubCont(oldPid, -1);
        }
        return true;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean deleteMenu(List<Long> ids) {
        List<SysMenuDO> sysMenuDOS = menuMapper.selectListByIds(ids);
        sysMenuDOS.forEach(sysMenu -> {
            if (sysMenu.getPid() != 0) {
                updateMenuSubCont(sysMenu.getPid(), -1);
            }
            recursionDeleteMenu(sysMenu.getId());
            menuMapper.deleteById(sysMenu.getId());
        });
        return true;
    }

    private void recursionDeleteMenu(Long id) {
        List<SysMenuDO> sysMenuDOS = menuMapper.selectListByPid(id);
        if (CollectionUtils.isEmpty(sysMenuDOS)) {
            return;
        }
        sysMenuDOS.forEach(sysMenuDO -> recursionDeleteMenu(sysMenuDO.getId()));
        menuMapper.deleteBatchIds(sysMenuDOS.stream().map(SysMenuDO::getId).collect(Collectors.toSet()));
    }

    private void updateMenuSubCont(Long menuId, Integer size) {
        SysMenuDO sysMenuDO = menuMapper.selectById(menuId);
        if (sysMenuDO != null) {
            sysMenuDO.setSubCount(sysMenuDO.getSubCount() + size);
            menuMapper.updateById(sysMenuDO);
        }
    }
}
