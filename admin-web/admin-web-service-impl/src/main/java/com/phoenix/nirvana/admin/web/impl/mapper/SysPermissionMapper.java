package com.phoenix.nirvana.admin.web.impl.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.phoenix.nirvana.admin.web.impl.dataobject.SysPermissionDO;
import com.phoenix.nirvana.common.constant.CommonNirvanaConstants;
import org.springframework.stereotype.Repository;

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
public interface SysPermissionMapper extends BaseMapper<SysPermissionDO> {

    default List<SysPermissionDO> selectListByIds(Set<Long> ids) {
        return selectList(new QueryWrapper<SysPermissionDO>().lambda().eq(SysPermissionDO::getIsEnable, false)
                .in(SysPermissionDO::getId, ids));
    }

    default List<SysPermissionDO> selectButtonListByPid(Set<Long> pids) {
        return selectList(new QueryWrapper<SysPermissionDO>().lambda()
                .eq(SysPermissionDO::getType, CommonNirvanaConstants.PERMISSION_BUTTON).eq(SysPermissionDO::getIsEnable, false)
                .in(SysPermissionDO::getId, pids));
    }
}
