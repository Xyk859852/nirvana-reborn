package com.phoenix.nirvana.product.rpc.attr.domain.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 * 商品规格属性新增
 *
 * @Description: key - values
 * @Author: xuyongkang
 * @Date 2023/5/10 4:01 PM
 */
@Data
@Accessors(chain = true)
@ApiModel("规格属性新增")
public class ProductAttrValuesCreateDTO implements Serializable {

    @ApiModelProperty("商品id")
    private Long productId;

    @ApiModelProperty("属性名")
    private String attrName;

    @ApiModelProperty("属性排序")
    private Integer attrSort;

    @ApiModelProperty("属性值集合")
    private List<String> attrValues;


}
