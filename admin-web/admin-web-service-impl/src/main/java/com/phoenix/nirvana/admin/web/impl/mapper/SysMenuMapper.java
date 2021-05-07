package com.phoenix.nirvana.admin.web.impl.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.phoenix.nirvana.admin.web.impl.dataobject.SysMenuDO;
import com.phoenix.nirvana.mybatis.core.query.QueryWrapperX;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Set;

/**
 * <p>
 * 权限表 Mapper 接口
 * </p>
 *
 * @author Xuyk
 * @since 2021-02-01
 */
@Repository
public interface SysMenuMapper extends BaseMapper<SysMenuDO> {

    default List<SysMenuDO> selectListByIds(Collection<Long> ids) {
        return selectList(new QueryWrapper<SysMenuDO>().lambda()
                .in(SysMenuDO::getId, ids));
    }

    default List<SysMenuDO> selectAll() {
        return selectList(new QueryWrapper());
    }


    default List<SysMenuDO> selectListByPid(Long pid) {
        return selectList(new QueryWrapper<SysMenuDO>().lambda()
                .eq(SysMenuDO::getPid, pid));
    }

    default List<SysMenuDO> selectListByPidOrLikeTitle(String title, Long pid) {
        QueryWrapperX queryWrapperX = new QueryWrapperX<SysMenuDO>();
        queryWrapperX.likeIfPresent("title", title).eqIfPresent("pid", pid).orderByAsc("sort");
        return selectList(queryWrapperX);
    }

    default Boolean deleteByPid(Long pid) {
        return delete(new QueryWrapper<SysMenuDO>().eq("pid", pid)) > 0;
    }
}
