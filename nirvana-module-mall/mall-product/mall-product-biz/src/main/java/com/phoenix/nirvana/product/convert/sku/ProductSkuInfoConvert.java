package com.phoenix.nirvana.product.convert.sku;

import com.phoenix.nirvana.product.dal.mysql.dataobject.sku.ProductSkuInfoDO;
import com.phoenix.nirvana.product.rpc.sku.domain.dto.ProductSkuInfoCreateDTO;
import com.phoenix.nirvana.product.rpc.sku.domain.vo.ProductSkuInfoVO;
import com.phoenix.nirvana.product.rpc.spu.domain.dto.ProductInfoCreateDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * 商品 sku 对象转换
 *
 * @Author: xuyongkang
 * @Date 2023/7/17 14:24
 */
@Mapper
public interface ProductSkuInfoConvert {

    ProductSkuInfoConvert INTERFACE = Mappers.getMapper(ProductSkuInfoConvert.class);

    @Mappings({})
    ProductSkuInfoVO convert(ProductSkuInfoDO productSkuInfo);

    @Mappings({})
    List<ProductSkuInfoVO> convert2Vo(List<ProductSkuInfoDO> productSkuInfo);

    @Mappings({})
    ProductSkuInfoCreateDTO convert(ProductInfoCreateDTO.ProductAttr attr);

    @Mappings({})
    ProductSkuInfoDO convert(ProductSkuInfoCreateDTO productSpuInfo);

    @Mappings({})
    List<ProductSkuInfoDO> convert(List<ProductSkuInfoCreateDTO> productSpuInfo);

}
