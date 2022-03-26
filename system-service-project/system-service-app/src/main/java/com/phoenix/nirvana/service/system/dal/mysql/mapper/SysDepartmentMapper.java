package com.phoenix.nirvana.service.system.dal.mysql.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.phoenix.nirvana.service.system.dal.mysql.dataobject.SysDepartmentDO;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 *  部门表 Mapper 接口
 * </p>
 *
 * @author Xuyk
 * @since 2021-02-01
 */
@Repository
public interface SysDepartmentMapper extends BaseMapper<SysDepartmentDO> {

    default List<SysDepartmentDO> selectListByLikeRightCode(String code) {
        return selectList(new QueryWrapper<SysDepartmentDO>().likeRight("code", code));
    }

}
