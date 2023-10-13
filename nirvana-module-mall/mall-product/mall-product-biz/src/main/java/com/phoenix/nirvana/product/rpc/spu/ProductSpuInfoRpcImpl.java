package com.phoenix.nirvana.product.rpc.spu;

import com.phoenix.nirvana.common.vo.CommonResult;
import com.phoenix.nirvana.product.manager.spu.ProductSpuInfoManager;
import com.phoenix.nirvana.product.rpc.spu.domain.vo.ProductSpuInfoVO;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * product sku rpc impl
 *
 * @Author: xuyongkang
 * @Date 2023/7/26 16:49
 */
@Component
@DubboService
public class ProductSpuInfoRpcImpl implements ProductSpuInfoRpc {

    @Autowired
    ProductSpuInfoManager productSpuInfoManager;

    @Override
    public CommonResult<List<ProductSpuInfoVO>> listProductSpuByIds(Collection<? extends Serializable> idList) {
        return productSpuInfoManager.listProductSpuByIds(idList);
    }
}
