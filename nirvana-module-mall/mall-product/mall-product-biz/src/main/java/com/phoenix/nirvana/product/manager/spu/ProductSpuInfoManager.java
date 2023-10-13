package com.phoenix.nirvana.product.manager.spu;

import com.phoenix.nirvana.common.exception.util.ServiceExceptionUtil;
import com.phoenix.nirvana.common.vo.CommonResult;
import com.phoenix.nirvana.product.convert.attr.ProductAttrConvert;
import com.phoenix.nirvana.product.convert.sku.ProductSkuInfoConvert;
import com.phoenix.nirvana.product.rpc.attr.domain.dto.ProductAttrValuesCreateDTO;
import com.phoenix.nirvana.product.rpc.attr.domain.vo.ProductAttrVO;
import com.phoenix.nirvana.product.rpc.attr.domain.vo.ProductAttrValuesVO;
import com.phoenix.nirvana.product.rpc.sku.domain.dto.ProductSkuInfoCreateDTO;
import com.phoenix.nirvana.product.rpc.spu.domain.dto.ProductInfoCreateDTO;
import com.phoenix.nirvana.product.rpc.spu.domain.vo.ProductSpuInfoVO;
import com.phoenix.nirvana.product.service.attr.ProductAttrService;
import com.phoenix.nirvana.product.service.sku.ProductSkuInfoService;
import com.phoenix.nirvana.product.service.spu.ProductSpuInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import static com.phoenix.nirvana.product.enums.ProductErrorCodeConstants.PRODUCT_SPU_INSERT_ERROR;

/**
 * product sku manager
 *
 * @Author: xuyongkang
 * @Date 2023/7/26 16:52
 */
@Component
public class ProductSpuInfoManager {

    @Autowired
    ProductSpuInfoService productSpuInfoService;

    @Autowired
    ProductSkuInfoService productSkuInfoService;

    @Autowired
    ProductAttrService productAttrService;

    /**
     * 根据id集合查询商品对应spu数据
     *
     * @param idList
     * @return
     */
    public CommonResult<List<ProductSpuInfoVO>> listProductSpuByIds(Collection<? extends Serializable> idList) {
        return CommonResult.success(productSpuInfoService.listProductSpuByIds(idList));
    }


    /**
     * 创建商品
     *
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public CommonResult<Boolean> createProduct(ProductInfoCreateDTO productInfoCreate) {
        Long productSpuId = productSpuInfoService.createProductSpu(productInfoCreate);
        if (productSpuId == null) {
            throw ServiceExceptionUtil.exception(PRODUCT_SPU_INSERT_ERROR);
        }
        for (ProductInfoCreateDTO.ProductAttr productAttr : productInfoCreate.getProductAttrList()) {
            ProductAttrValuesCreateDTO attrValuesCreate = ProductAttrConvert.INTERFACE.convert(productAttr);
            attrValuesCreate.setProductId(productSpuId);
            ProductAttrVO attrKeyValues = productAttrService.createAttrKeyValues(attrValuesCreate);
            for (ProductAttrValuesVO attrValue : attrKeyValues.getAttrValues()) {
                ProductSkuInfoCreateDTO productSkuCreateInfo = ProductSkuInfoConvert.INTERFACE.convert(productAttr);
                productSkuCreateInfo.setProductId(productSpuId);
                String skuCode = productSpuId + "_" + attrKeyValues.getAttrId() + "_" + attrValue.getAttrValueId();
                productSkuCreateInfo.setSkuCode(skuCode);
                productSkuInfoService.createProductSku(productSkuCreateInfo);
            }
        }
        return CommonResult.success(true);
    }


}
