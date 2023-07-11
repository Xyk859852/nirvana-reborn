package com.phoenix.nirvana.order.convert.order;

import com.phoenix.nirvana.order.dal.mysql.dataobject.order.ProductOrderDO;
import com.phoenix.nirvana.order.mq.message.OrderStdChangeMessage;
import org.mapstruct.Mapper;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

/**
 * 云路信息科技有限公司 版权所有 ©Copyright 2023
 *
 * @Description: TODO
 * @Author: xuyongkang
 * @Date 2023/7/11 16:49
 */
@Mapper
public interface ProductOrderConvert {

    ProductOrderConvert INSTANCE = Mappers.getMapper(ProductOrderConvert.class);


    @Mappings({})
    OrderStdChangeMessage convert(ProductOrderDO order);
}
