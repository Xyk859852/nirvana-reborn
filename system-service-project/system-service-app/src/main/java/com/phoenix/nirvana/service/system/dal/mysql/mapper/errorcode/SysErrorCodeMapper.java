package com.phoenix.nirvana.service.system.dal.mysql.mapper.errorcode;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.phoenix.nirvana.mybatis.core.mapper.BaseMapperX;
import com.phoenix.nirvana.mybatis.core.query.QueryWrapperX;
import com.phoenix.nirvana.service.system.dal.mysql.dataobject.errorcode.SysErrorCodeDO;
import org.apache.ibatis.annotations.Mapper;

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
@Mapper
public interface SysErrorCodeMapper extends BaseMapperX<SysErrorCodeDO> {

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
