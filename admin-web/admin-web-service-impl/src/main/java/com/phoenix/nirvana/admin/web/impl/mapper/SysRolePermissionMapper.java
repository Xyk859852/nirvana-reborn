package com.phoenix.nirvana.admin.web.impl.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.phoenix.nirvana.admin.web.impl.dataobject.SysRolePermissionDO;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 * 角色权限表 Mapper 接口
 * </p>
 *
 * @author Xuyk
 * @since 2021-02-01
 */
@Repository
public interface SysRolePermissionMapper extends BaseMapper<SysRolePermissionDO> {

    default List<SysRolePermissionDO> selectListByRoleId(Long rid) {
        return selectList(Wrappers.lambdaQuery(SysRolePermissionDO.class).eq(SysRolePermissionDO::getRid, rid));
    }

    default Boolean deleteByRoleId(Long rid) {
        return delete(new QueryWrapper<SysRolePermissionDO>().eq("rid", rid)) > 0;
    }
}
