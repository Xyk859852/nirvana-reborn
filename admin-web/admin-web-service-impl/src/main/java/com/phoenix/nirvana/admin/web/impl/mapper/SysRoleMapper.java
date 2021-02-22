package com.phoenix.nirvana.admin.web.impl.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.phoenix.nirvana.admin.web.impl.dataobject.SysRoleDO;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 *  用户角色表 Mapper 接口
 * </p>
 *
 * @author Xuyk
 * @since 2021-02-01
 */
@Repository
public interface SysRoleMapper extends BaseMapper<SysRoleDO> {

    default List<SysRoleDO> selectListByCreateId(Long createId) {
        return selectList(Wrappers.lambdaQuery(SysRoleDO.class).eq(SysRoleDO::getCreateId, createId));
    }
}
