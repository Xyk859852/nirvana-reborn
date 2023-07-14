package com.phoenix.nirvana.product.dal.mysql.mapper.catalog;

import com.phoenix.nirvana.common.vo.PageResult;
import com.phoenix.nirvana.mybatis.core.mapper.BaseMapperX;
import com.phoenix.nirvana.mybatis.core.query.LambdaQueryWrapperX;
import com.phoenix.nirvana.product.dal.mysql.dataobject.catalog.ProductCatalogDO;
import com.phoenix.nirvana.product.rpc.catalog.domain.dto.ProductCatalogPageDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 * 商品三级分类 Mapper 接口
 * </p>
 *
 * @author xuyongkang
 * @since 2023-05-10
 */
@Mapper
public interface ProductCatalogMapper extends BaseMapperX<ProductCatalogDO> {


    default PageResult<ProductCatalogDO> pageList(ProductCatalogPageDTO productCatalogPage) {
        return selectPage(productCatalogPage, new LambdaQueryWrapperX<ProductCatalogDO>()
                .likeIfPresent(ProductCatalogDO::getCatalogName, productCatalogPage.getKeyword())
                .eqIfPresent(ProductCatalogDO::getParentId, productCatalogPage.getPid())
        );
    }

    default List<ProductCatalogDO> selectListByPid(Long pid) {
        return this.selectList(new LambdaQueryWrapperX<ProductCatalogDO>().eqIfPresent(ProductCatalogDO::getParentId, pid));
    }

    default Boolean existsByName(String name) {
        return this.exists(new LambdaQueryWrapperX<ProductCatalogDO>().eq(ProductCatalogDO::getCatalogName, name));
    }

    default Boolean hasChild(Long catalogId) {
        return this.selectCount(ProductCatalogDO::getParentId, catalogId) > 0 ? true : false;
    }

}
