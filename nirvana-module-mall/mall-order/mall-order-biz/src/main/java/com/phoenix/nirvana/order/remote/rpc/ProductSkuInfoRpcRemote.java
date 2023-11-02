package com.phoenix.nirvana.order.remote.rpc;

import com.phoenix.nirvana.common.vo.CommonResult;
import com.phoenix.nirvana.product.rpc.sku.ProductSkuInfoRpc;
import com.phoenix.nirvana.product.rpc.sku.domain.dto.ListProductSkuDTO;
import com.phoenix.nirvana.product.rpc.sku.domain.vo.ProductSkuInfoVO;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * product sku rpc remote
 *
 * @Author: xuyongkang
 * @Date 2023/7/17 14:50
 */
@Component
public class ProductSkuInfoRpcRemote {

    @DubboReference(retries = 0)
    ProductSkuInfoRpc productSkuInfoRpc;

    public List<ProductSkuInfoVO> listProductSku(ListProductSkuDTO listProductSku) {
        CommonResult<List<ProductSkuInfoVO>> result = productSkuInfoRpc.listProductSku(listProductSku);
        return result.getCheckedData();
    }
}
