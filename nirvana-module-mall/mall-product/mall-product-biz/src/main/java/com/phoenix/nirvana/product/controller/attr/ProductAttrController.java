package com.phoenix.nirvana.product.controller.attr;

import com.phoenix.nirvana.common.vo.CommonResult;
import com.phoenix.nirvana.product.manager.attr.ProductAttrManager;
import com.phoenix.nirvana.product.rpc.attr.domain.dto.ProductAttrValuesCreateDTO;
import com.phoenix.nirvana.product.rpc.attr.domain.dto.ProductAttrValuesUpdateDTO;
import com.phoenix.nirvana.product.rpc.attr.domain.vo.ProductAttrValuesVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 商品属性 前端控制器
 * </p>
 *
 * @author xuyongkang
 * @since 2023-05-10
 */
@Api(tags = "商品属性")
@RestController
@RequestMapping("/product/attr")
public class ProductAttrController {

    @Autowired
    ProductAttrManager productAttrManager;


    @ApiOperation(value = "根据商品属性id查询属性 key - values")
    @GetMapping("/getAttrDetailById/{attrId}")
    public CommonResult<ProductAttrValuesVO> getAttrDetailById(@PathVariable("attrId") Long attrId) {
        return productAttrManager.getAttrDetailById(attrId);
    }

    @ApiOperation(value = "创建商品属性 key - values")
    @PostMapping("/createAttrKeyValues")
    public CommonResult<Boolean> createAttrKeyValues(@RequestBody ProductAttrValuesCreateDTO attrValuesCreate) {
        return productAttrManager.createAttrKeyValues(attrValuesCreate);
    }

    @ApiOperation(value = "修改商品属性 key - values")
    @PostMapping("/updateAttrKeyValues")
    public CommonResult<Boolean> updateAttrKeyValues(@RequestBody ProductAttrValuesUpdateDTO attrValuesUpdate) {
        return productAttrManager.updateAttrKeyValues(attrValuesUpdate);
    }

    @ApiOperation(value = "修改商品属性 key - values")
    @DeleteMapping("/delete/{attrId}")
    public CommonResult<Boolean> delete(@PathVariable("attrId") Long attrId) {
        return productAttrManager.delete(attrId);
    }


}
