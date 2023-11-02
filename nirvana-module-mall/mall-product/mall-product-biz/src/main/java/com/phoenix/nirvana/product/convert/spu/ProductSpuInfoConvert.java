package com.phoenix.nirvana.product.convert.spu;

import com.phoenix.nirvana.product.dal.mysql.dataobject.spu.ProductSpuInfoDO;
import com.phoenix.nirvana.product.rpc.spu.domain.dto.ProductInfoCreateDTO;
import com.phoenix.nirvana.product.rpc.spu.domain.vo.ProductSpuInfoVO;
import org.mapstruct.Mapper;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * product sku rpc convert
 *
 * @Author: xuyongkang
 * @Date 2023/7/26 16:55
 */
@Mapper
public interface ProductSpuInfoConvert {

    ProductSpuInfoConvert INTERFACE = Mappers.getMapper(ProductSpuInfoConvert.class);

    @Mappings({})
    ProductSpuInfoVO convert(ProductSpuInfoDO productSpuInfo);

    @Mappings({})
    List<ProductSpuInfoVO> convert2Vo(List<ProductSpuInfoDO> productSpuInfo);

    @Mappings({})
    ProductSpuInfoDO convert(ProductInfoCreateDTO productInfoCreate);

}
