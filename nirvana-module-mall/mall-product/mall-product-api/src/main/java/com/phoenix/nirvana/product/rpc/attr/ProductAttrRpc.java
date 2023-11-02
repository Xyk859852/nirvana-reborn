package com.phoenix.nirvana.product.rpc.attr;

import com.phoenix.nirvana.common.vo.CommonResult;
import com.phoenix.nirvana.product.rpc.attr.domain.dto.ProductAttrValuesCreateDTO;
import com.phoenix.nirvana.product.rpc.attr.domain.vo.ProductAttrVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import static com.phoenix.nirvana.product.enums.MallProductApiConstants.SYSTEM_SERVICE_APP;
import static com.phoenix.nirvana.product.enums.MallProductApiConstants.SYSTEM_SERVICE_APP_PREFIX;

/**
 * @Description:
 * @Author: xuyongkang
 * @Date 2023/5/10 3:47 PM
 */
@FeignClient(name = SYSTEM_SERVICE_APP, path = SYSTEM_SERVICE_APP_PREFIX + "/attr")
public interface ProductAttrRpc {


    /**
     * 创建商品属性 key - values
     *
     * @param productAttrCreateDTO
     * @return
     */
    @PostMapping("/createAttrKeyValues")
    CommonResult<ProductAttrVO> createAttrKeyValues(@RequestBody ProductAttrValuesCreateDTO productAttrCreateDTO);


    /**
     * 根据商品属性id 查询对应的 key - values
     *
     * @param attrId 属性key id
     * @return
     */
    @GetMapping("/getAttrDetailById/{attrId}")
    CommonResult<ProductAttrVO> getAttrDetailById(@PathVariable("attrId") Long attrId);
}
