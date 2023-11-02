package com.phoenix.nirvana.product.service.sku;

import com.phoenix.nirvana.product.rpc.sku.domain.dto.ListProductSkuDTO;
import com.phoenix.nirvana.product.rpc.sku.domain.dto.ProductSkuInfoCreateDTO;
import com.phoenix.nirvana.product.rpc.sku.domain.vo.ProductSkuInfoVO;

import java.util.List;

/**
 * <p>
 * 商品sku信息 服务类
 * </p>
 *
 * @author xuyongkang
 * @since 2023-05-10
 */
public interface ProductSkuInfoService {

    /**
     * 根据 skuCode 查询对应商品sku信息
     *
     * @param listProductSku
     * @return
     */
    List<ProductSkuInfoVO> listProductSku(ListProductSkuDTO listProductSku);


    /**
     * sku 入库
     *
     * @return
     */
    Boolean createProductSku(ProductSkuInfoCreateDTO skuInfoCreateList);
}
