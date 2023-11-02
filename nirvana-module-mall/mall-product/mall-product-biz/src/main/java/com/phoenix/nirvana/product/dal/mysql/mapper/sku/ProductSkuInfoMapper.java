package com.phoenix.nirvana.product.dal.mysql.mapper.sku;

import com.phoenix.nirvana.mybatis.core.mapper.BaseMapperX;
import com.phoenix.nirvana.mybatis.core.query.LambdaQueryWrapperX;
import com.phoenix.nirvana.product.dal.mysql.dataobject.sku.ProductSkuInfoDO;
import com.phoenix.nirvana.product.enums.sku.ProductSkuEnableEnum;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 * 商品sku信息 Mapper 接口
 * </p>
 *
 * @author xuyongkang
 * @since 2023-05-10
 */
@Mapper
public interface ProductSkuInfoMapper extends BaseMapperX<ProductSkuInfoDO> {

    default List<ProductSkuInfoDO> selectProductSkuByCodes(List<String> skuCodes) {
        return selectList(new LambdaQueryWrapperX<ProductSkuInfoDO>()
                .in(ProductSkuInfoDO::getSkuCode, skuCodes)
                .eq(ProductSkuInfoDO::getStatus, ProductSkuEnableEnum.TRUE.getCode())
        );
    }
}
