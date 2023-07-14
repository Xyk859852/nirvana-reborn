package com.phoenix.nirvana.product.rpc.catalog.domain.vo;

import com.phoenix.nirvana.product.rpc.catalog.domain.dto.ProductCatalogUpdateDTO;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 商品分类分页列表查询返回对象
 *
 * @Description:
 * @Author: xuyongkang
 * @Date 2023/5/11 19:23
 */
@Data
@Accessors(chain = true)
@ApiModel("商品分类分页列表查询返回对象")
public class ProductCatalogPageListVO extends ProductCatalogUpdateDTO implements Serializable {


}
