package com.phoenix.nirvana.product.rpc.attr.domain.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 商品属性值返回对象
 *
 * @Author: xuyongkang
 * @Date 2023/8/3 14:32
 */
@Data
@Accessors
public class ProductAttrValuesVO implements Serializable {

    @ApiModelProperty("属性value id")
    private Long attrValueId;

    @ApiModelProperty("商品属性id")
    private Long attrId;

    @ApiModelProperty("属性值名称")
    private String attrValueName;

    @ApiModelProperty("属性 值排序")
    private Integer attrValueSort;

}
