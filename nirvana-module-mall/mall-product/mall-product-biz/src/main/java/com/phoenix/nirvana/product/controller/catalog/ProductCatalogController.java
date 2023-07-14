package com.phoenix.nirvana.product.controller.catalog;

import com.phoenix.nirvana.common.vo.CommonResult;
import com.phoenix.nirvana.common.vo.PageResult;
import com.phoenix.nirvana.product.manager.catalog.ProductCatalogManager;
import com.phoenix.nirvana.product.rpc.catalog.domain.dto.ProductCatalogCreateDTO;
import com.phoenix.nirvana.product.rpc.catalog.domain.dto.ProductCatalogPageDTO;
import com.phoenix.nirvana.product.rpc.catalog.domain.dto.ProductCatalogUpdateDTO;
import com.phoenix.nirvana.product.rpc.catalog.domain.vo.ProductCatalogPageListVO;
import com.phoenix.nirvana.product.rpc.catalog.domain.vo.ProductCatalogTreeVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 商品三级分类 前端控制器
 * </p>
 *
 * @author xuyongkang
 * @since 2023-05-10
 */
@RestController
@Api(tags = "商品三级分类")
@RequestMapping("/product/catalog")
public class ProductCatalogController {

    @Autowired
    ProductCatalogManager productCatalogManager;


    @ApiOperation("商品分类分页列表查询")
    @GetMapping("/pageList")
    public CommonResult<PageResult<ProductCatalogPageListVO>> pageList(ProductCatalogPageDTO productCatalogPage) {
        return productCatalogManager.pageList(productCatalogPage);
    }

    @ApiOperation("商品分类树形结构数据")
    @GetMapping("/treeList")
    public CommonResult<List<ProductCatalogTreeVO>> treeList(@RequestParam(required = false) Long pid) {
        return productCatalogManager.treeList(pid);
    }

    @ApiOperation("商品分类新增")
    @PostMapping("/create")
    public CommonResult<Long> create(ProductCatalogCreateDTO productCatalogCreate) {
        return productCatalogManager.create(productCatalogCreate);
    }

    @ApiOperation("商品分类修改")
    @PostMapping("/update")
    public CommonResult<Boolean> update(ProductCatalogUpdateDTO productCatalogUpdate) {
        return productCatalogManager.update(productCatalogUpdate);
    }

    @ApiOperation("商品分类删除")
    @DeleteMapping("/delete/{catalogId}")
    public CommonResult<Boolean> delete(@PathVariable Long catalogId) {
        return productCatalogManager.delete(catalogId);
    }

}
