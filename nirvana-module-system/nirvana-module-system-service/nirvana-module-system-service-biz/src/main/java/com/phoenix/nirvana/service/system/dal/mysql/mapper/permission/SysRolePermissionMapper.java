package com.phoenix.nirvana.service.system.dal.mysql.mapper.permission;

import com.phoenix.nirvana.mybatis.core.mapper.BaseMapperX;
import com.phoenix.nirvana.mybatis.core.query.LambdaQueryWrapperX;
import com.phoenix.nirvana.service.system.dal.mysql.dataobject.permission.SysRolePermissionDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 * 角色资源表 Mapper 接口
 * </p>
 *
 * @author Xuyk
 * @since 2021-02-01
 */
@Mapper
public interface SysRolePermissionMapper extends BaseMapperX<SysRolePermissionDO> {

    default List<SysRolePermissionDO> selectListByRoleId(Long rid) {
        return selectList(new LambdaQueryWrapperX<SysRolePermissionDO>().eq(SysRolePermissionDO::getRid, rid));
    }

    default Boolean deleteByRoleId(Long rid) {
        return delete(new LambdaQueryWrapperX<SysRolePermissionDO>().eq(SysRolePermissionDO::getRid, rid)) > 0;
    }
}
