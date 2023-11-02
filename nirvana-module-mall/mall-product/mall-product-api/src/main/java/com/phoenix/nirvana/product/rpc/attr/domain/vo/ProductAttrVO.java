package com.phoenix.nirvana.product.rpc.attr.domain.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 * 商品属性返回
 *
 * @Author: xuyongkang
 * @Date 2023/5/11 11:31
 */
@Data
@Accessors(chain = true)
@ApiModel("商品属性明细")
public class ProductAttrVO implements Serializable {

    @ApiModelProperty("商品属性id")
    private Long attrId;

    @ApiModelProperty("商品id")
    private Long productId;

    @ApiModelProperty("属性名")
    private String attrName;

    @ApiModelProperty("属性排序")
    private Integer attrSort;

    @ApiModelProperty("属性值集合")
    private List<ProductAttrValuesVO> attrValues;

}
