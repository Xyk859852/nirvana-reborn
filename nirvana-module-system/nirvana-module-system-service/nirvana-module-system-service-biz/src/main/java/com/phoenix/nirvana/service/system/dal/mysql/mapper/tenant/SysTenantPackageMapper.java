package com.phoenix.nirvana.service.system.dal.mysql.mapper.tenant;

import com.phoenix.nirvana.mybatis.core.mapper.BaseMapperX;
import com.phoenix.nirvana.service.system.dal.mysql.dataobject.tenant.SysTenantPackageDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 租户套餐 Mapper
 *
 * @author xuyongkang
 */
@Mapper
public interface SysTenantPackageMapper extends BaseMapperX<SysTenantPackageDO> {

//    default PageResult<SysTenantPackageDO> selectPage(TenantPackagePageReqVO reqVO) {
//        return selectPage(reqVO, new LambdaQueryWrapperX<SysTenantPackageDO>()
//                .likeIfPresent(SysTenantPackageDO::getName, reqVO.getName())
//                .eqIfPresent(SysTenantPackageDO::getStatus, reqVO.getStatus())
//                .likeIfPresent(SysTenantPackageDO::getRemark, reqVO.getRemark())
//                .betweenIfPresent(SysTenantPackageDO::getCreateTime, reqVO.getBeginCreateTime(), reqVO.getEndCreateTime())
//                .orderByDesc(SysTenantPackageDO::getId));
//    }

    default List<SysTenantPackageDO> selectListByStatus(Integer status) {
        return selectList(SysTenantPackageDO::getStatus, status);
    }
}
