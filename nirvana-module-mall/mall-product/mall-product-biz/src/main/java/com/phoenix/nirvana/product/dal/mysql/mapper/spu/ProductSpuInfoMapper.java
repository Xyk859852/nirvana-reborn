package com.phoenix.nirvana.product.dal.mysql.mapper.spu;

import com.phoenix.nirvana.mybatis.core.mapper.BaseMapperX;
import com.phoenix.nirvana.mybatis.core.query.LambdaQueryWrapperX;
import com.phoenix.nirvana.product.dal.mysql.dataobject.spu.ProductSpuInfoDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 商品spu信息 Mapper 接口
 * </p>
 *
 * @author xuyongkang
 * @since 2023-05-10
 */
@Mapper
public interface ProductSpuInfoMapper extends BaseMapperX<ProductSpuInfoDO> {


    default Boolean existsProductSpu(String productName) {
        return this.exists(new LambdaQueryWrapperX<ProductSpuInfoDO>()
                .eq(ProductSpuInfoDO::getProductName, productName));
    }
}
