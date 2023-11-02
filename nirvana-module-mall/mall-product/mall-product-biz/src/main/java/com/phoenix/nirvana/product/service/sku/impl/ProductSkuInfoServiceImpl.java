package com.phoenix.nirvana.product.service.sku.impl;

import com.phoenix.nirvana.product.convert.sku.ProductSkuInfoConvert;
import com.phoenix.nirvana.product.dal.mysql.dataobject.sku.ProductSkuInfoDO;
import com.phoenix.nirvana.product.dal.mysql.mapper.sku.ProductSkuInfoMapper;
import com.phoenix.nirvana.product.rpc.sku.domain.dto.ListProductSkuDTO;
import com.phoenix.nirvana.product.rpc.sku.domain.dto.ProductSkuInfoCreateDTO;
import com.phoenix.nirvana.product.rpc.sku.domain.vo.ProductSkuInfoVO;
import com.phoenix.nirvana.product.service.sku.ProductSkuInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 商品sku信息 服务实现类
 * </p>
 *
 * @author xuyongkang
 * @since 2023-05-10
 */
@Service
public class ProductSkuInfoServiceImpl implements ProductSkuInfoService {

    @Autowired
    ProductSkuInfoMapper productSkuInfoMapper;

    @Override
    public List<ProductSkuInfoVO> listProductSku(ListProductSkuDTO listProductSku) {
        List<ProductSkuInfoDO> skuInfos = productSkuInfoMapper.selectProductSkuByCodes(listProductSku.getSkuCodes());
        return ProductSkuInfoConvert.INTERFACE.convert2Vo(skuInfos);
    }

    @Override
    public Boolean createProductSku(ProductSkuInfoCreateDTO skuInfoCreate) {
        ProductSkuInfoDO skuInfo = ProductSkuInfoConvert.INTERFACE.convert(skuInfoCreate);
        productSkuInfoMapper.insert(skuInfo);
        return true;
    }
}
