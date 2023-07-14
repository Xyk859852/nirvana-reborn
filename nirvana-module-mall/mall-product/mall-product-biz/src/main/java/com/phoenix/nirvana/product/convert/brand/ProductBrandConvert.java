package com.phoenix.nirvana.product.convert.brand;

import com.phoenix.nirvana.common.vo.PageResult;
import com.phoenix.nirvana.product.dal.mysql.dataobject.brand.ProductBrandDO;
import com.phoenix.nirvana.product.rpc.brand.domain.vo.ProductBrandPageListVO;
import org.mapstruct.Mapper;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

/**
 * 品牌对象转换
 *
 * @Description:
 * @Author: xuyongkang
 * @Date 2023/5/15 14:00
 */
@Mapper
public interface ProductBrandConvert {

    ProductBrandConvert INTERFACE = Mappers.getMapper(ProductBrandConvert.class);

    @Mappings({})
    ProductBrandPageListVO convertPage(ProductBrandDO catalog);

    @Mappings({})
    PageResult<ProductBrandPageListVO> convert(PageResult<ProductBrandDO> catalog);
}
