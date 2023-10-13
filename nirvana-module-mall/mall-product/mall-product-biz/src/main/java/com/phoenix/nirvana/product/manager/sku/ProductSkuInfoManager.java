package com.phoenix.nirvana.product.manager.sku;

import com.phoenix.nirvana.common.vo.CommonResult;
import com.phoenix.nirvana.product.rpc.sku.domain.dto.ListProductSkuDTO;
import com.phoenix.nirvana.product.rpc.sku.domain.vo.ProductSkuInfoVO;
import com.phoenix.nirvana.product.service.sku.ProductSkuInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 商品sku信息 manager
 *
 * @Description: sku manager
 * @Author: xuyongkang
 * @Date 2023/5/10 9:49 AM
 */
@Component
public class ProductSkuInfoManager {

    @Autowired
    private ProductSkuInfoService productSkuInfoService;

    /**
     * 根据 skuCode 查询对应商品sku信息
     *
     * @param listProductSku
     * @return
     */
    public CommonResult<List<ProductSkuInfoVO>> listProductSku(ListProductSkuDTO listProductSku) {
        return CommonResult.success(productSkuInfoService.listProductSku(listProductSku));
    }

}
