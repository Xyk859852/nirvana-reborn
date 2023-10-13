package com.phoenix.nirvana.order.dal.mysql.mapper.order;

import com.phoenix.nirvana.mybatis.core.query.LambdaQueryWrapperX;
import com.phoenix.nirvana.order.dal.mysql.dataobject.ProductOrderDO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 商品订单表 Mapper 接口
 * </p>
 *
 * @author xuyongkang
 * @since 2023-06-29
 */
@Mapper
public interface ProductOrderMapper extends BaseMapper<ProductOrderDO> {


    default ProductOrderDO getByOrderNo(String orderNo) {
        return selectOne(new LambdaQueryWrapperX<ProductOrderDO>().eqIfPresent(ProductOrderDO::getOrderNo, orderNo));
    }
}
