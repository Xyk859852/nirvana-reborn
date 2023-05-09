package com.phoenix.nirvana.service.system.dal.mysql.mapper.permission;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.phoenix.nirvana.common.enums.CommonStatusEnum;
import com.phoenix.nirvana.mybatis.core.mapper.BaseMapperX;
import com.phoenix.nirvana.mybatis.core.query.LambdaQueryWrapperX;
import com.phoenix.nirvana.service.system.dal.mysql.dataobject.permission.SysPermissionDO;
import com.phoenix.nirvana.service.system.rpc.auth.permission.domain.dto.PermissionListDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.Collection;
import java.util.List;
import java.util.Set;

/**
 * <p>
 * 菜单资源 Mapper 接口
 * </p>
 *
 * @author Xuyk
 * @since 2021-02-01
 */
@Mapper
public interface SysPermissionMapper extends BaseMapperX<SysPermissionDO> {

    default List<SysPermissionDO> selectListByIds(Collection<Long> ids) {
        return selectList(new LambdaQueryWrapperX<SysPermissionDO>()
                .eq(SysPermissionDO::getEnable, CommonStatusEnum.ENABLE.getStatus())
                .in(SysPermissionDO::getId, ids));
    }

    default List<SysPermissionDO> selectAll() {
        return selectList(new QueryWrapper());
    }


    default List<SysPermissionDO> selectListByPid(Long pid) {
        return selectList(new LambdaQueryWrapperX<SysPermissionDO>()
                .eq(SysPermissionDO::getPid, pid));
    }

    default Page<SysPermissionDO> selectListByPidOrLikeTitle(PermissionListDTO permissionListDTO) {
        return selectPage(new Page(permissionListDTO.getCurrent(), permissionListDTO.getSize()), new LambdaQueryWrapperX<SysPermissionDO>()
                .likeIfPresent(SysPermissionDO::getTitle, permissionListDTO.getKeyboard())
                .eqIfPresent(SysPermissionDO::getPid, permissionListDTO.getPid())
                .orderByDesc(SysPermissionDO::getSort));
    }

    default Boolean deleteByPid(Long pid) {
        return delete(new LambdaQueryWrapperX<SysPermissionDO>().eq(SysPermissionDO::getPid, pid)) > 0;
    }

    @Select("SELECT " +
            "p.perm_code " +
            "FROM " +
            "sys_permission p " +
            "LEFT JOIN sys_role_permission rp ON p.id = rp.pid " +
            "LEFT JOIN sys_role r on r.id = rp.rid " +
            "where r.id = #{roleId} ")
    Set<String> selectPermissionCodes(Long roleId);
}
