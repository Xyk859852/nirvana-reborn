package com.phoenix.nirvana.product.rpc.catalog.domain.vo;

import com.phoenix.nirvana.product.rpc.catalog.domain.dto.ProductCatalogUpdateDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 商品三级分类树形结构数据
 *
 * @Description:
 * @Author: xuyongkang
 * @Date 2023/5/11 17:53
 */
@Data
@Accessors(chain = true)
@ApiModel("商品三级分类树形结构数据")
public class ProductCatalogTreeVO extends ProductCatalogUpdateDTO implements Serializable {

    @ApiModelProperty("子集")
    public List<ProductCatalogTreeVO> children = new ArrayList<>();
}
