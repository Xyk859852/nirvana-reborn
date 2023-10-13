package com.phoenix.nirvana.product.rpc.attr;

import com.phoenix.nirvana.common.vo.CommonResult;
import com.phoenix.nirvana.product.manager.attr.ProductAttrManager;
import com.phoenix.nirvana.product.rpc.attr.domain.dto.ProductAttrValuesCreateDTO;
import com.phoenix.nirvana.product.rpc.attr.domain.vo.ProductAttrVO;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

/**
 * 云路信息科技有限公司 版权所有 ©Copyright 2023
 *
 * @Description: TODO
 * @Author: xuyongkang
 * @Date 2023/5/10 17:36
 */
@Service
@DubboService
@RestController
public class ProductAttrRpcImpl implements ProductAttrRpc {

    @Autowired
    ProductAttrManager productAttrManager;


    /**
     * 创建商品规格 key - values
     *
     * @return
     */
    public CommonResult<ProductAttrVO> createAttrKeyValues(ProductAttrValuesCreateDTO productAttrCreateDTO) {
        return productAttrManager.createAttrKeyValues(productAttrCreateDTO);
    }

    /**
     * 根据商品属性id 查询对应的 key - values
     *
     * @param attrId 属性key id
     * @return
     */
    @Override
    public CommonResult<ProductAttrVO> getAttrDetailById(Long attrId) {
        return productAttrManager.getAttrDetailById(attrId);
    }
}
