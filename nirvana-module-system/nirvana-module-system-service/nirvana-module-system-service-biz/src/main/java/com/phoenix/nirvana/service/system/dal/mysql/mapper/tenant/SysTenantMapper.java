package com.phoenix.nirvana.service.system.dal.mysql.mapper.tenant;

import com.phoenix.nirvana.mybatis.core.mapper.BaseMapperX;
import com.phoenix.nirvana.service.system.dal.mysql.dataobject.tenant.SysTenantDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.Date;
import java.util.List;

/**
 * 租户 Mapper
 *
 * @author xuyongkang
 */
@Mapper
public interface SysTenantMapper extends BaseMapperX<SysTenantDO> {

//    default PageResult<SysTenantDO> selectPage(TenantPageReqVO reqVO) {
//        return selectPage(reqVO, new LambdaQueryWrapperX<SysTenantDO>()
//                .likeIfPresent(SysTenantDO::getName, reqVO.getName())
//                .likeIfPresent(SysTenantDO::getContactName, reqVO.getContactName())
//                .likeIfPresent(SysTenantDO::getContactMobile, reqVO.getContactMobile())
//                .eqIfPresent(SysTenantDO::getStatus, reqVO.getStatus())
//                .betweenIfPresent(SysTenantDO::getCreateTime, reqVO.getBeginCreateTime(), reqVO.getEndCreateTime())
//                .orderByDesc(SysTenantDO::getId));
//    }
//
//    default List<SysTenantDO> selectList(TenantExportReqVO reqVO) {
//        return selectList(new LambdaQueryWrapperX<SysTenantDO>()
//                .likeIfPresent(SysTenantDO::getName, reqVO.getName())
//                .likeIfPresent(SysTenantDO::getContactName, reqVO.getContactName())
//                .likeIfPresent(SysTenantDO::getContactMobile, reqVO.getContactMobile())
//                .eqIfPresent(SysTenantDO::getStatus, reqVO.getStatus())
//                .betweenIfPresent(SysTenantDO::getCreateTime, reqVO.getBeginCreateTime(), reqVO.getEndCreateTime())
//                .orderByDesc(SysTenantDO::getId));
//    }

    default SysTenantDO selectByName(String name) {
        return selectOne(SysTenantDO::getName, name);
    }

    default Long selectCountByPackageId(Long packageId) {
        return selectCount(SysTenantDO::getPackageId, packageId);
    }

    default List<SysTenantDO> selectListByPackageId(Long packageId) {
        return selectList(SysTenantDO::getPackageId, packageId);
    }

    @Select("SELECT id FROM system_tenant WHERE update_time > #{maxUpdateTime} LIMIT 1")
    Long selectExistsByUpdateTimeAfter(Date maxUpdateTime);

}
