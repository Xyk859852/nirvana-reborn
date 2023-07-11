package com.phoenix.nirvana.order.dal.mysql.mapper.audit;

import com.phoenix.nirvana.order.dal.mysql.dataobject.audit.ProductOrderAuditDO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 商品订单审核明细表 Mapper 接口
 * </p>
 *
 * @author xuyongkang
 * @since 2023-06-29
 */
@Mapper
public interface ProductOrderAuditMapper extends BaseMapper<ProductOrderAuditDO> {

}
