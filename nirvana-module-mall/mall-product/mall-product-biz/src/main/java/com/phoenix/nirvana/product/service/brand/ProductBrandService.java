package com.phoenix.nirvana.product.service.brand;

import com.phoenix.nirvana.common.vo.PageResult;
import com.phoenix.nirvana.product.rpc.brand.domain.dto.ProductBrandPageDTO;
import com.phoenix.nirvana.product.rpc.brand.domain.vo.ProductBrandPageListVO;

/**
 * <p>
 * 品牌 服务类
 * </p>
 *
 * @author xuyongkang
 * @since 2023-05-10
 */
public interface ProductBrandService {


    /**
     * 商品品牌分页列表查询
     *
     * @param productBrandPage 查询参数
     * @return
     */
    PageResult<ProductBrandPageListVO> pageList(ProductBrandPageDTO productBrandPage);
}
