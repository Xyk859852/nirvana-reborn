package com.phoenix.nirvana.product.rpc.attr.domain.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 商品规格属性修改
 *
 * @Description: key - values
 * @Author: xuyongkang
 * @Date 2023/5/10 4:01 PM
 */
@Data
@Accessors(chain = true)
@ApiModel("规格属性修改")
public class ProductAttrValuesUpdateDTO extends ProductAttrValuesCreateDTO implements Serializable {

    @ApiModelProperty("商品属性id")
    private Long attrId;


}
