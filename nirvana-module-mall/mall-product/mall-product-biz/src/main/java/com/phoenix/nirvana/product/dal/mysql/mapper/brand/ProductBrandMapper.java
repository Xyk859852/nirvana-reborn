package com.phoenix.nirvana.product.dal.mysql.mapper.brand;

import com.phoenix.nirvana.common.vo.PageResult;
import com.phoenix.nirvana.mybatis.core.mapper.BaseMapperX;
import com.phoenix.nirvana.mybatis.core.query.LambdaQueryWrapperX;
import com.phoenix.nirvana.product.dal.mysql.dataobject.brand.ProductBrandDO;
import com.phoenix.nirvana.product.rpc.brand.domain.dto.ProductBrandPageDTO;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 品牌 Mapper 接口
 * </p>
 *
 * @author xuyongkang
 * @since 2023-05-10
 */
@Mapper
public interface ProductBrandMapper extends BaseMapperX<ProductBrandDO> {

    default PageResult<ProductBrandDO> pageList(ProductBrandPageDTO productBrandPage) {
        return selectPage(productBrandPage, new LambdaQueryWrapperX<ProductBrandDO>()
                .likeIfPresent(ProductBrandDO::getBrandName, productBrandPage.getKeyword())
        );
    }
}
