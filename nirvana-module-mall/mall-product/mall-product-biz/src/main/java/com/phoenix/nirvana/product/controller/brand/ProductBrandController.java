package com.phoenix.nirvana.product.controller.brand;

import com.phoenix.nirvana.common.vo.CommonResult;
import com.phoenix.nirvana.common.vo.PageResult;
import com.phoenix.nirvana.product.manager.brand.ProductBrandManager;
import com.phoenix.nirvana.product.rpc.brand.domain.dto.ProductBrandPageDTO;
import com.phoenix.nirvana.product.rpc.brand.domain.vo.ProductBrandPageListVO;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 品牌 前端控制器
 * </p>
 *
 * @author xuyongkang
 * @since 2023-05-10
 */
@RestController
@RequestMapping("/product/brand")
public class ProductBrandController {

    ProductBrandManager productBrandManager;

    @ApiOperation("商品品牌分页列表查询")
    @GetMapping("/pageList")
    public CommonResult<PageResult<ProductBrandPageListVO>> pageList(ProductBrandPageDTO productBrandPage) {
        return productBrandManager.pageList(productBrandPage);
    }


}
