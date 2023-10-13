package com.phoenix.nirvana.order.convert.order;

import com.phoenix.nirvana.inventory.rpc.dto.DeductProductStockDTO;
import com.phoenix.nirvana.inventory.rpc.dto.ReleaseProductStockDTO;
import com.phoenix.nirvana.market.domain.dto.CalculateOrderAmountDTO;
import com.phoenix.nirvana.market.domain.vo.CalculateOrderAmountVO;
import com.phoenix.nirvana.order.dal.mysql.dataobject.ProductOrderDO;
import com.phoenix.nirvana.order.dal.mysql.dataobject.ProductOrderDiscountDO;
import com.phoenix.nirvana.order.dal.mysql.dataobject.ProductOrderItemDO;
import com.phoenix.nirvana.order.mq.message.OrderStdChangeMessage;
import com.phoenix.nirvana.order.rpc.domain.dto.CreateOrderDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;

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

    @Mappings({})
    ProductOrderDO convert2DO(CreateOrderDTO createOrder);

    @Mappings({})
    DeductProductStockDTO convert2Deduct(CreateOrderDTO createOrder);

    @Mappings({})
    ReleaseProductStockDTO convert2Release(CreateOrderDTO createOrder);

    @Mappings({})
    CalculateOrderAmountDTO convert2Market(CreateOrderDTO createOrder);

    @Mappings({
            @Mapping(target = "productSkuCode", source = "skuCode"),
            @Mapping(target = "productCount", source = "saleQuantity"),
            @Mapping(target = "productUnitPrice", source = "salePrice"),
            @Mapping(target = "productTotalPrice", source = "productTotalPrice"),
            @Mapping(target = "productDiscountPrice", source = "discountsAmount")
    })
    ProductOrderItemDO convert(CalculateOrderAmountVO.OrderItemAmountDetailVO productSkuInfo);

    @Mappings({})
    List<ProductOrderItemDO> convert(List<CalculateOrderAmountVO.OrderItemAmountDetailVO> productSkuInfo);


    @Mappings({})
    ProductOrderDiscountDO convertCoupon(CalculateOrderAmountVO.OrderCouponDetailVO couponDetail);

    @Mappings({})
    List<ProductOrderDiscountDO> convertCoupon(List<CalculateOrderAmountVO.OrderCouponDetailVO> couponDetailList);
}
