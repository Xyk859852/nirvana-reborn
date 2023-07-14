package com.phoenix.nirvana.order.dal.mysql.mapper.orderiterm;

import com.phoenix.nirvana.order.dal.mysql.dataobject.orderiterm.ProductOrderItemDO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 商品订单明细表 Mapper 接口
 * </p>
 *
 * @author xuyongkang
 * @since 2023-07-13
 */
@Mapper
public interface ProductOrderItemMapper extends BaseMapper<ProductOrderItemDO> {

}
