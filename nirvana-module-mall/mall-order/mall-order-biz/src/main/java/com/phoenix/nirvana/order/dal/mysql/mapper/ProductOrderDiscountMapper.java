package com.phoenix.nirvana.order.dal.mysql.mapper;

import com.phoenix.nirvana.order.dal.mysql.dataobject.ProductOrderDiscountDO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 商品订单优惠明细表 Mapper 接口
 * </p>
 *
 * @author xuyongkang
 * @since 2023-07-19
 */
@Mapper
public interface ProductOrderDiscountMapper extends BaseMapper<ProductOrderDiscountDO> {

}
