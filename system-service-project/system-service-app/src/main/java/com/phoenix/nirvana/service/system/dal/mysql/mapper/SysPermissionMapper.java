package com.phoenix.nirvana.service.system.dal.mysql.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.phoenix.nirvana.mybatis.core.query.QueryWrapperX;
import com.phoenix.nirvana.service.system.dal.mysql.dataobject.SysPermissionDO;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

/**
 * <p>
 * 权限表 Mapper 接口
 * </p>
 *
 * @author Xuyk
 * @since 2021-02-01
 */
@Repository
public interface SysPermissionMapper extends BaseMapper<SysPermissionDO> {

    default List<SysPermissionDO> selectListByIds(Collection<Long> ids) {
        return selectList(new QueryWrapper<SysPermissionDO>().lambda()
                .in(SysPermissionDO::getId, ids));
    }

    default List<SysPermissionDO> selectAll() {
        return selectList(new QueryWrapper());
    }


    default List<SysPermissionDO> selectListByPid(Long pid) {
        return selectList(new QueryWrapper<SysPermissionDO>().lambda()
                .eq(SysPermissionDO::getPid, pid));
    }

    default List<SysPermissionDO> selectListByPidOrLikeTitle(String title, Long pid) {
        QueryWrapperX queryWrapperX = new QueryWrapperX<SysPermissionDO>();
        queryWrapperX.likeIfPresent("title", title).eqIfPresent("pid", pid).orderByAsc("sort");
        return selectList(queryWrapperX);
    }

    default Boolean deleteByPid(Long pid) {
        return delete(new QueryWrapper<SysPermissionDO>().eq("pid", pid)) > 0;
    }
}
