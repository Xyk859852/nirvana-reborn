package com.phoenix.nirvana.product.convert.attr;

import com.phoenix.nirvana.product.dal.mysql.dataobject.attr.ProductAttrDO;
import com.phoenix.nirvana.product.dal.mysql.dataobject.attr.ProductAttrValueDO;
import com.phoenix.nirvana.product.rpc.attr.domain.dto.ProductAttrValuesCreateDTO;
import com.phoenix.nirvana.product.rpc.attr.domain.dto.ProductAttrValuesUpdateDTO;
import com.phoenix.nirvana.product.rpc.attr.domain.vo.ProductAttrVO;
import com.phoenix.nirvana.product.rpc.attr.domain.vo.ProductAttrValuesVO;
import com.phoenix.nirvana.product.rpc.spu.domain.dto.ProductInfoCreateDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;

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
    ProductAttrVO convert(ProductAttrDO attr);

    @Mappings({})
    ProductAttrValuesCreateDTO convert(ProductInfoCreateDTO.ProductAttr productAttr);

    @Mappings({})
    List<ProductAttrValuesCreateDTO> convert(List<ProductInfoCreateDTO.ProductAttr> productAttr);

    @Mappings({})
    ProductAttrValuesVO convert2VO(ProductAttrValueDO productAttrValue);

    @Mappings({})
    List<ProductAttrValuesVO> convert2VO(List<ProductAttrValueDO> productAttrValue);
}
