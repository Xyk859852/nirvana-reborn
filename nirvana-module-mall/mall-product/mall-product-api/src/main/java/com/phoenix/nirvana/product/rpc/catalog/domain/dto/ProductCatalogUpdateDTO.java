package com.phoenix.nirvana.product.rpc.catalog.domain.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 商品三级分类新增
 *
 * @Description:
 * @Author: xuyongkang
 * @Date 2023/5/11 14:48
 */
@Data
@Accessors(chain = true)
@ApiModel("商品三级分类修改Model")
public class ProductCatalogUpdateDTO extends ProductCatalogCreateDTO implements Serializable {

    @ApiModelProperty("分类id")
    private Long catalogId;

}
