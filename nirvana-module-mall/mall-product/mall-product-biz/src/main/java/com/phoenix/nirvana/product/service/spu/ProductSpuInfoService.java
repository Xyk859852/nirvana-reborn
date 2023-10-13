package com.phoenix.nirvana.product.service.spu;

import com.phoenix.nirvana.product.rpc.spu.domain.dto.ProductInfoCreateDTO;
import com.phoenix.nirvana.product.rpc.spu.domain.vo.ProductSpuInfoVO;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * <p>
 * 商品spu信息 服务类
 * </p>
 *
 * @author xuyongkang
 * @since 2023-05-10
 */
public interface ProductSpuInfoService {


    /**
     * 根据id集合查询商品对应spu数据
     *
     * @param idList
     * @return
     */
    List<ProductSpuInfoVO> listProductSpuByIds(Collection<? extends Serializable> idList);

    /**
     * check product spu by product name
     * @return
     */
    Boolean checkProductSpuByName(String productName);
    /**
     * create product spu
     *
     * @param productInfoCreate
     * @return
     */
    Long createProductSpu(ProductInfoCreateDTO productInfoCreate);
}
