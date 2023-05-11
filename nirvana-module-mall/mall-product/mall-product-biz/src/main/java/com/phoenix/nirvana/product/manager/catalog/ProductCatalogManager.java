package com.phoenix.nirvana.product.manager.catalog;

import com.phoenix.nirvana.common.vo.CommonResult;
import com.phoenix.nirvana.common.vo.PageResult;
import com.phoenix.nirvana.product.rpc.catalog.domain.dto.ProductCatalogCreateDTO;
import com.phoenix.nirvana.product.rpc.catalog.domain.dto.ProductCatalogPageDTO;
import com.phoenix.nirvana.product.rpc.catalog.domain.dto.ProductCatalogUpdateDTO;
import com.phoenix.nirvana.product.rpc.catalog.domain.vo.ProductCatalogPageListVO;
import com.phoenix.nirvana.product.rpc.catalog.domain.vo.ProductCatalogTreeVO;
import com.phoenix.nirvana.product.service.catalog.ProductCatalogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 云路信息科技有限公司 版权所有 ©Copyright 2023
 *
 * @Description: TODO
 * @Author: xuyongkang
 * @Date 2023/5/11 14:45
 */
@Component
public class ProductCatalogManager {

    @Autowired
    ProductCatalogService productCatalogService;


    /**
     * 商品分类分页列表查询
     *
     * @return
     */
    public CommonResult<PageResult<ProductCatalogPageListVO>> pageList(ProductCatalogPageDTO productCatalogPage) {
        return CommonResult.success(productCatalogService.pageList(productCatalogPage));
    }

    /**
     * 商品分类树形结构数据
     *
     * @return
     */
    public CommonResult<List<ProductCatalogTreeVO>> treeList(Long pid) {
        return CommonResult.success(productCatalogService.treeList(pid));
    }

    /**
     * 创建商品分类数据
     *
     * @param productCatalogCreate
     * @return
     */
    public CommonResult<Long> create(ProductCatalogCreateDTO productCatalogCreate) {
        return CommonResult.success(productCatalogService.create(productCatalogCreate));
    }

    /**
     * 修改商品分类数据
     *
     * @param productCatalogUpdate
     * @return
     */
    public CommonResult<Boolean> update(ProductCatalogUpdateDTO productCatalogUpdate) {
        return CommonResult.success(productCatalogService.update(productCatalogUpdate));
    }

    /**
     * 删除商品分类数据
     *
     * @param catalogId
     * @return
     */
    public CommonResult<Boolean> delete(Long catalogId) {
        return CommonResult.success(productCatalogService.delete(catalogId));
    }


}
