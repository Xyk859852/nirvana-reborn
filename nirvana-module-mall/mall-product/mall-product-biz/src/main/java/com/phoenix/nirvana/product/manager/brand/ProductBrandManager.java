package com.phoenix.nirvana.product.manager.brand;

import com.phoenix.nirvana.common.vo.CommonResult;
import com.phoenix.nirvana.common.vo.PageResult;
import com.phoenix.nirvana.product.rpc.brand.domain.dto.ProductBrandPageDTO;
import com.phoenix.nirvana.product.rpc.brand.domain.vo.ProductBrandPageListVO;
import com.phoenix.nirvana.product.service.brand.ProductBrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 品牌 Manager
 *
 * @Description:
 * @Author: xuyongkang
 * @Date 2023/5/15 13:42
 */
@Component
public class ProductBrandManager {

    @Autowired
    ProductBrandService productBrandService;

    /**
     * 商品品牌分页列表查询
     * @param productBrandPage
     * @return
     */
    public CommonResult<PageResult<ProductBrandPageListVO>> pageList(ProductBrandPageDTO productBrandPage) {
        return CommonResult.success(productBrandService.pageList(productBrandPage));
    }
}
