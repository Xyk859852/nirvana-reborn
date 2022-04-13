package com.phoenix.nirvana.service.system.dal.mysql.mapper.user;

import com.phoenix.nirvana.common.util.StringUtils;
import com.phoenix.nirvana.common.vo.PageResult;
import com.phoenix.nirvana.mybatis.core.mapper.BaseMapperX;
import com.phoenix.nirvana.mybatis.core.query.LambdaQueryWrapperX;
import com.phoenix.nirvana.service.system.dal.mysql.dataobject.user.SysUserDO;
import com.phoenix.nirvana.service.system.rpc.admin.domain.dto.user.AdminUserPageDTO;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 系统用户 Mapper 接口
 * </p>
 *
 * @author Xuyk
 * @since 2021-01-26
 */
@Mapper
public interface SysUserMapper extends BaseMapperX<SysUserDO> {

    default SysUserDO selectOneByUserName(String userName) {
        return selectOne(new LambdaQueryWrapperX<SysUserDO>().eq(SysUserDO::getUserName, userName));

    }

    default SysUserDO selectOneByPhone(String phone, Long userId) {
        return selectOne(new LambdaQueryWrapperX<SysUserDO>().eq(SysUserDO::getPhone, phone));

    }

    default PageResult<SysUserDO> selectPageList(AdminUserPageDTO pageListDTO) {
        LambdaQueryWrapperX<SysUserDO> doQueryWrapperX = new LambdaQueryWrapperX<>();
        if (StringUtils.hasText(pageListDTO.getKeyboard())) {
            doQueryWrapperX.and(sysUserDOQueryWrapper ->
                    sysUserDOQueryWrapper.like(SysUserDO::getUserName, pageListDTO.getKeyboard())
                            .or()
                            .like(SysUserDO::getPhone, pageListDTO.getKeyboard())
            );
        }
        return selectPage(pageListDTO, doQueryWrapperX);
    }
}
