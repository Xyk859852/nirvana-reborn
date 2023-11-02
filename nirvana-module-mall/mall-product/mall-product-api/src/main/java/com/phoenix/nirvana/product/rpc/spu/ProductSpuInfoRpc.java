package com.phoenix.nirvana.product.rpc.spu;

import com.phoenix.nirvana.common.vo.CommonResult;
import com.phoenix.nirvana.product.rpc.spu.domain.vo.ProductSpuInfoVO;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * 商品 spu dubbo api
 *
 * @Author: xuyongkang
 * @Date 2023/7/26 15:58
 */
public interface ProductSpuInfoRpc {

    /**
     * 根据id集合查询商品对应spu数据
     *
     * @param idList
     * @return
     */
    CommonResult<List<ProductSpuInfoVO>> listProductSpuByIds(Collection<? extends Serializable> idList);
}
