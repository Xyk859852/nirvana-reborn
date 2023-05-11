package com.phoenix.nirvana.product.rpc.catalog.domain.dto;

import com.phoenix.nirvana.common.dto.PageParam;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 商品三级分类分页查询参数
 *
 * @Description:
 * @Author: xuyongkang
 * @Date 2023/5/11 19:15
 */
@Data
@Accessors(chain = true)
@ApiModel("商品三级分类分页查询参数")
public class ProductCatalogPageDTO extends PageParam implements Serializable {

    @ApiModelProperty("模糊查询")
    private String keyword;

    @ApiModelProperty("父级id")
    private Long pid;
}
