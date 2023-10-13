package com.phoenix.nirvana.product.rpc.sku;

import com.phoenix.nirvana.common.vo.CommonResult;
import com.phoenix.nirvana.product.rpc.sku.domain.dto.ListProductSkuDTO;
import com.phoenix.nirvana.product.rpc.sku.domain.vo.ProductSkuInfoVO;

import java.util.List;

/**
 * 商品 sku dubbo api
 *
 * @Description:
 * @Author: xuyongkang
 * @Date 2023/5/10 10:01 AM
 */
public interface ProductSkuInfoRpc {


    /**
     * 根据 skuCode 查询对应商品sku信息
     *
     * @param listProductSku
     * @return
     */
    CommonResult<List<ProductSkuInfoVO>> listProductSku(ListProductSkuDTO listProductSku);
}
