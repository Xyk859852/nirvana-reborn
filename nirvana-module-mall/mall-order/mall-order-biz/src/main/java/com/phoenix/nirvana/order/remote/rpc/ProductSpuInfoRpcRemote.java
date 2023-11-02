package com.phoenix.nirvana.order.remote.rpc;

import com.phoenix.nirvana.common.vo.CommonResult;
import com.phoenix.nirvana.product.rpc.spu.ProductSpuInfoRpc;
import com.phoenix.nirvana.product.rpc.spu.domain.vo.ProductSpuInfoVO;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * product sku rpc remote
 *
 * @Author: xuyongkang
 * @Date 2023/7/26 15:56
 */
@Component
public class ProductSpuInfoRpcRemote {

    @DubboReference(retries = 0)
    ProductSpuInfoRpc productSpuInfoRpc;

    public List<ProductSpuInfoVO> listProductSpuByIds(Collection<? extends Serializable> idList) {
        CommonResult<List<ProductSpuInfoVO>> result = productSpuInfoRpc.listProductSpuByIds(idList);
        return result.getCheckedData();
    }


}
