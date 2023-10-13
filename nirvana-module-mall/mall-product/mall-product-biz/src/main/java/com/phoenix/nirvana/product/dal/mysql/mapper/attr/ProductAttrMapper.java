package com.phoenix.nirvana.product.dal.mysql.mapper.attr;

import com.phoenix.nirvana.mybatis.core.mapper.BaseMapperX;
import com.phoenix.nirvana.mybatis.core.query.LambdaQueryWrapperX;
import com.phoenix.nirvana.product.dal.mysql.dataobject.attr.ProductAttrDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 商品属性 Mapper 接口
 * </p>
 *
 * @author xuyongkang
 * @since 2023-05-10
 */
@Mapper
public interface ProductAttrMapper extends BaseMapperX<ProductAttrDO> {


    default Boolean existsByProductIdAttrKey(Long productId, String attrKeyName, Long attrId) {
        return this.exists(new LambdaQueryWrapperX<ProductAttrDO>()
                .eq(ProductAttrDO::getProductId, productId)
                .eq(ProductAttrDO::getAttrName, attrKeyName)
                .neIfPresent(ProductAttrDO::getAttrId, attrId)
        );
    }

    default ProductAttrDO selectAttrDetailByProductIdAndAttrName(Long productId, String attrKeyName) {
        return this.selectOne(new LambdaQueryWrapperX<ProductAttrDO>()
                .eq(ProductAttrDO::getProductId, productId)
                .eq(ProductAttrDO::getAttrName, attrKeyName)
        );
    }
}
