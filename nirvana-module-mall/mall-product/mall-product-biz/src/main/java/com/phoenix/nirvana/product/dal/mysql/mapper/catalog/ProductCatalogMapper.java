package com.phoenix.nirvana.product.dal.mysql.mapper.catalog;

import com.phoenix.nirvana.product.dal.mysql.dataobject.catalog.ProductCatalogDO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 商品三级分类 Mapper 接口
 * </p>
 *
 * @author xuyongkang
 * @since 2023-05-10
 */
@Mapper
public interface ProductCatalogMapper extends BaseMapper<ProductCatalogDO> {

}
