package com.phoenix.nirvana.product.convert.attr;

import com.phoenix.nirvana.product.dal.mysql.dataobject.attr.ProductAttrDO;
import com.phoenix.nirvana.product.rpc.attr.domain.dto.ProductAttrValuesCreateDTO;
import com.phoenix.nirvana.product.rpc.attr.domain.dto.ProductAttrValuesUpdateDTO;
import com.phoenix.nirvana.product.rpc.attr.domain.vo.ProductAttrValuesVO;
import org.mapstruct.Mapper;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

/**
 * 商品规格类转换
 *
 * @Description:
 * @Author: xuyongkang
 * @Date 2023/5/10 17:22
 */
@Mapper
public interface ProductAttrConvert {

    ProductAttrConvert INTERFACE = Mappers.getMapper(ProductAttrConvert.class);

    @Mappings({})
    ProductAttrDO convert(ProductAttrValuesCreateDTO create);

    @Mappings({})
    ProductAttrDO convert(ProductAttrValuesUpdateDTO update);

    @Mappings({})
    ProductAttrValuesVO convert(ProductAttrDO attr);
}
