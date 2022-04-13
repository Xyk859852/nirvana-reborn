package com.phoenix.nirvana.service.system.dal.mysql.mapper.permission;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.phoenix.nirvana.mybatis.core.mapper.BaseMapperX;
import com.phoenix.nirvana.service.system.dal.mysql.dataobject.permission.SysRoleDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 *  用户角色表 Mapper 接口
 * </p>
 *
 * @author Xuyk
 * @since 2021-02-01
 */
@Mapper
public interface SysRoleMapper extends BaseMapperX<SysRoleDO> {

    default List<SysRoleDO> selectListByCreateId(Long createId) {
        return selectList(Wrappers.lambdaQuery(SysRoleDO.class).eq(SysRoleDO::getCreator, createId));
    }

//    default Page<SysRoleDO> selectPageList(RolePageDTO rolePageDTO) {
//        QueryWrapperX<SysRoleDO> queryWrapperX = new QueryWrapperX<>();
//        queryWrapperX.likeIfPresent("name", rolePageDTO.getKeyboard());
//        return selectPage(new Page(rolePageDTO.getCurrent(), rolePageDTO.getSize()), queryWrapperX);
//    }
}
