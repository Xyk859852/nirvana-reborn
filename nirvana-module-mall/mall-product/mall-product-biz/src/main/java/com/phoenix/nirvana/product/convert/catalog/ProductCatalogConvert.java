package com.phoenix.nirvana.product.convert.catalog;

import com.phoenix.nirvana.common.vo.PageResult;
import com.phoenix.nirvana.product.dal.mysql.dataobject.catalog.ProductCatalogDO;
import com.phoenix.nirvana.product.rpc.catalog.domain.dto.ProductCatalogCreateDTO;
import com.phoenix.nirvana.product.rpc.catalog.domain.dto.ProductCatalogUpdateDTO;
import com.phoenix.nirvana.product.rpc.catalog.domain.vo.ProductCatalogPageListVO;
import com.phoenix.nirvana.product.rpc.catalog.domain.vo.ProductCatalogTreeVO;
import org.mapstruct.Mapper;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

/**
 * 商品三级分类 对象转换
 *
 * @Description:
 * @Author: xuyongkang
 * @Date 2023/5/11 15:03
 */
@Mapper
public interface ProductCatalogConvert {

    ProductCatalogConvert INTERFACE = Mappers.getMapper(ProductCatalogConvert.class);

    @Mappings({})
    ProductCatalogDO convert(ProductCatalogCreateDTO create);

    @Mappings({})
    ProductCatalogDO convert(ProductCatalogUpdateDTO update);

    @Mappings({})
    ProductCatalogTreeVO convert(ProductCatalogDO catalog);

    @Mappings({})
    ProductCatalogPageListVO convertPage(ProductCatalogDO catalog);

    @Mappings({})
    PageResult<ProductCatalogPageListVO> convert(PageResult<ProductCatalogDO> catalog);
}
