package com.phoenix.nirvana.service.system.dal.mysql.mapper.dept;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.phoenix.nirvana.mybatis.core.mapper.BaseMapperX;
import com.phoenix.nirvana.service.system.dal.mysql.dataobject.dept.SysDepartmentDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 *  部门表 Mapper 接口
 * </p>
 *
 * @author Xuyk
 * @since 2021-02-01
 */
@Mapper
public interface SysDepartmentMapper extends BaseMapperX<SysDepartmentDO> {

    default List<SysDepartmentDO> selectListByLikeRightCode(String code) {
        return selectList(new QueryWrapper<SysDepartmentDO>().likeRight("code", code));
    }

}
