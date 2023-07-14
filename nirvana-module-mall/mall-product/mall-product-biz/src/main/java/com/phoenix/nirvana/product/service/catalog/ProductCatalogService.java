package com.phoenix.nirvana.product.service.catalog;

import com.phoenix.nirvana.common.vo.PageResult;
import com.phoenix.nirvana.product.rpc.catalog.domain.dto.ProductCatalogCreateDTO;
import com.phoenix.nirvana.product.rpc.catalog.domain.dto.ProductCatalogPageDTO;
import com.phoenix.nirvana.product.rpc.catalog.domain.dto.ProductCatalogUpdateDTO;
import com.phoenix.nirvana.product.rpc.catalog.domain.vo.ProductCatalogPageListVO;
import com.phoenix.nirvana.product.rpc.catalog.domain.vo.ProductCatalogTreeVO;

import java.util.List;

/**
 * <p>
 * 商品三级分类 服务类
 * </p>
 *
 * @author xuyongkang
 * @since 2023-05-10
 */
public interface ProductCatalogService {


    /**
     * 商品分类分页查询
     *
     * @return
     */
    PageResult<ProductCatalogPageListVO> pageList(ProductCatalogPageDTO productCatalogPage);

    /**
     * 商品分类树形结构数据
     *
     * @return
     */
    List<ProductCatalogTreeVO> treeList(Long pid);


    /**
     * 创建商品分类数据
     *
     * @param productCatalogCreate
     * @return
     */
    Long create(ProductCatalogCreateDTO productCatalogCreate);

    /**
     * 修改商品分类数据
     *
     * @param productCatalogUpdate
     * @return
     */
    Boolean update(ProductCatalogUpdateDTO productCatalogUpdate);

    /**
     * 删除商品分类数据
     *
     * @param catalogId
     * @return
     */
    Boolean delete(Long catalogId);



}
