package com.phoenix.nirvana.admin.web.impl.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.phoenix.nirvana.admin.web.impl.dataobject.SysErrorCodeDO;
import com.phoenix.nirvana.mybatis.core.query.QueryWrapperX;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 错误代码表 Mapper 接口
 * </p>
 *
 * @author Xuyk
 * @since 2021-02-01
 */
@Repository
public interface SysErrorCodeMapper extends BaseMapper<SysErrorCodeDO> {

    default List<SysErrorCodeDO> selectListByGroup(String group) {
        return selectList(new QueryWrapperX<SysErrorCodeDO>().
                eqIfPresent("`group`", group));
    }

    default List<SysErrorCodeDO> selectListByGroup(String group, Date minUpdateDate) {
        return selectList(new QueryWrapperX<SysErrorCodeDO>().
                eqIfPresent("`group`", group)
                .gtIfPresent("updateTime", minUpdateDate));
    }

    default List<SysErrorCodeDO> selectListByCodes(Collection<Integer> codes) {
        return selectList(new QueryWrapper<SysErrorCodeDO>().in("code", codes));
    }
}
