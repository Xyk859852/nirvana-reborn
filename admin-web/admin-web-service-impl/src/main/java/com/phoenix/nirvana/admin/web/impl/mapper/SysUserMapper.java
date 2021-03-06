package com.phoenix.nirvana.admin.web.impl.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.phoenix.nirvana.admin.web.api.dto.user.AdminUserPageDTO;
import com.phoenix.nirvana.admin.web.impl.dataobject.SysUserDO;
import com.phoenix.nirvana.common.util.StringUtils;
import com.phoenix.nirvana.mybatis.core.query.QueryWrapperX;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * 系统用户 Mapper 接口
 * </p>
 *
 * @author Xuyk
 * @since 2021-01-26
 */
@Repository
public interface SysUserMapper extends BaseMapper<SysUserDO> {

    default SysUserDO selectOneByUserName(String userName) {
        return selectOne(new QueryWrapper<SysUserDO>().eq("userName", userName));

    }

    default SysUserDO selectOneByPhone(String phone) {
        return selectOne(new QueryWrapper<SysUserDO>().eq("phone", phone));

    }

    default Page<SysUserDO> selectPage(AdminUserPageDTO pageListDTO) {
        QueryWrapperX<SysUserDO> doQueryWrapperX = new QueryWrapperX<>();
        if (StringUtils.hasText(pageListDTO.getKeyboard())) {
            doQueryWrapperX.and(sysUserDOQueryWrapper ->
                    sysUserDOQueryWrapper.like("userName", pageListDTO.getKeyboard())
                            .or()
                            .like("phone", pageListDTO.getKeyboard())
            );
        }
        return selectPage(new Page(pageListDTO.getCurrent(), pageListDTO.getSize()), doQueryWrapperX);
    }
}
