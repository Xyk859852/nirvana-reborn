package com.phoenix.nirvana.product.manager.attr;

import com.phoenix.nirvana.common.vo.CommonResult;
import com.phoenix.nirvana.product.rpc.attr.domain.dto.ProductAttrValuesCreateDTO;
import com.phoenix.nirvana.product.rpc.attr.domain.dto.ProductAttrValuesUpdateDTO;
import com.phoenix.nirvana.product.rpc.attr.domain.vo.ProductAttrVO;
import com.phoenix.nirvana.product.service.attr.ProductAttrService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 商品规格
 *
 * @Description:
 * @Author: xuyongkang
 * @Date 2023/5/10 3:08 PM
 */
@Component
public class ProductAttrManager {

    @Autowired
    ProductAttrService productAttrService;

    /**
     * 根据商品属性id 查询对应的 key - values
     *
     * @param attrId 属性key id
     * @return
     */
    public CommonResult<ProductAttrVO> getAttrDetailById(Long attrId) {
        return CommonResult.success(productAttrService.getAttrDetailById(attrId));
    }


    /**
     * 创建商品属性 key - values
     *
     * @return
     */
    public CommonResult<ProductAttrVO> createAttrKeyValues(ProductAttrValuesCreateDTO attrValuesCreate) {
        return CommonResult.success(productAttrService.createAttrKeyValues(attrValuesCreate));
    }

    /**
     * 修改商品属性 key - values
     *
     * @return
     */
    public CommonResult<ProductAttrVO> updateAttrKeyValues(ProductAttrValuesUpdateDTO attrValuesUpdate) {
        return CommonResult.success(productAttrService.updateAttrKeyValues(attrValuesUpdate));
    }

    /**
     * 根据属性id删除对应key - values
     *
     * @return
     */
    public CommonResult<Boolean> delete(Long attrId) {
        return CommonResult.success(productAttrService.delete(attrId));
    }

}
