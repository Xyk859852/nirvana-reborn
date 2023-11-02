package com.phoenix.nirvana.product.rpc.sku;

import com.phoenix.nirvana.common.vo.CommonResult;
import com.phoenix.nirvana.product.manager.sku.ProductSkuInfoManager;
import com.phoenix.nirvana.product.rpc.sku.domain.dto.ListProductSkuDTO;
import com.phoenix.nirvana.product.rpc.sku.domain.vo.ProductSkuInfoVO;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * product sku rpc impl
 *
 * @Author: xuyongkang
 * @Date 2023/7/17 11:32
 */
@Service
@DubboService
public class ProductSkuInfoRpcImpl implements ProductSkuInfoRpc {

    @Autowired
    ProductSkuInfoManager productSkuInfoManager;


    @Override
    public CommonResult<List<ProductSkuInfoVO>> listProductSku(ListProductSkuDTO listProductSku) {
        return productSkuInfoManager.listProductSku(listProductSku);
    }

}
