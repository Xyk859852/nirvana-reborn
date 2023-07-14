package com.phoenix.nirvana.product.service.brand.impl;

import com.phoenix.nirvana.common.vo.PageResult;
import com.phoenix.nirvana.product.convert.brand.ProductBrandConvert;
import com.phoenix.nirvana.product.dal.mysql.dataobject.brand.ProductBrandDO;
import com.phoenix.nirvana.product.dal.mysql.mapper.brand.ProductBrandMapper;
import com.phoenix.nirvana.product.rpc.brand.domain.dto.ProductBrandPageDTO;
import com.phoenix.nirvana.product.rpc.brand.domain.vo.ProductBrandPageListVO;
import com.phoenix.nirvana.product.service.brand.ProductBrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 品牌 服务实现类
 * </p>
 *
 * @author xuyongkang
 * @since 2023-05-10
 */
@Service
public class ProductBrandServiceImpl implements ProductBrandService {

    @Autowired
    ProductBrandMapper productBrandMapper;

    @Override
    public PageResult<ProductBrandPageListVO> pageList(ProductBrandPageDTO productBrandPage) {
        PageResult<ProductBrandDO> brandPage = productBrandMapper.pageList(productBrandPage);
        return ProductBrandConvert.INTERFACE.convert(brandPage);
    }
}
