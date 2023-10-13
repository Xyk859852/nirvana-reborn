package com.phoenix.nirvana.product.service.spu.impl;

import com.phoenix.nirvana.common.exception.util.ServiceExceptionUtil;
import com.phoenix.nirvana.product.convert.spu.ProductSpuInfoConvert;
import com.phoenix.nirvana.product.dal.mysql.dataobject.spu.ProductSpuInfoDO;
import com.phoenix.nirvana.product.dal.mysql.mapper.spu.ProductSpuInfoMapper;
import com.phoenix.nirvana.product.rpc.spu.domain.dto.ProductInfoCreateDTO;
import com.phoenix.nirvana.product.rpc.spu.domain.vo.ProductSpuInfoVO;
import com.phoenix.nirvana.product.service.spu.ProductSpuInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import static com.phoenix.nirvana.product.enums.ProductErrorCodeConstants.PRODUCT_SPU_EXISTS;

/**
 * <p>
 * 商品spu信息 服务实现类
 * </p>
 *
 * @author xuyongkang
 * @since 2023-05-10
 */
@Slf4j
@Service
public class ProductSpuInfoServiceImpl implements ProductSpuInfoService {

    @Autowired
    ProductSpuInfoMapper productSpuInfoMapper;

    @Override
    public List<ProductSpuInfoVO> listProductSpuByIds(Collection<? extends Serializable> idList) {
        log.info("list spu by ids:{}", idList);
        List<ProductSpuInfoDO> productSpuInfoList = productSpuInfoMapper.selectBatchIds(idList);
        return ProductSpuInfoConvert.INTERFACE.convert2Vo(productSpuInfoList);
    }

    @Override
    public Boolean checkProductSpuByName(String productName) {
        return productSpuInfoMapper.existsProductSpu(productName);
    }


    @Override
    public Long createProductSpu(ProductInfoCreateDTO productInfoCreate) {
        if (checkProductSpuByName(productInfoCreate.getProductName())) {
            throw ServiceExceptionUtil.exception(PRODUCT_SPU_EXISTS);
        }
        ProductSpuInfoDO productSpuInfo = ProductSpuInfoConvert.INTERFACE.convert(productInfoCreate);
        if (productSpuInfoMapper.insert(productSpuInfo) > 0) {
            return productSpuInfo.getProductId();
        }
        return null;
    }
}
