package com.phoenix.nirvana.service.system.dal.mysql.mapper.permission;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.phoenix.nirvana.mybatis.core.mapper.BaseMapperX;
import com.phoenix.nirvana.mybatis.core.query.LambdaQueryWrapperX;
import com.phoenix.nirvana.service.system.dal.mysql.dataobject.permission.SysRoleDO;
import com.phoenix.nirvana.service.system.rpc.role.domain.dto.RolePageDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 * 用户角色表 Mapper 接口
 * </p>
 *
 * @author Xuyk
 * @since 2021-02-01
 */
@Mapper
public interface SysRoleMapper extends BaseMapperX<SysRoleDO> {

    default List<SysRoleDO> selectListByCreateId(Long createId) {
        return selectList(new LambdaQueryWrapperX<SysRoleDO>().eq(SysRoleDO::getCreator, createId));
    }

    default Page<SysRoleDO> selectPageList(RolePageDTO rolePageDTO) {
        return selectPage(new Page(rolePageDTO.getPageNo(), rolePageDTO.getPageSize()), new LambdaQueryWrapperX<SysRoleDO>()
                .likeIfPresent(SysRoleDO::getName, rolePageDTO.getKeyboard())
                .orderByDesc(SysRoleDO::getSort)
        );
    }
}
