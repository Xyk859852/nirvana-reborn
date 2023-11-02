package com.phoenix.nirvana.product.rpc.sku.domain.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * product sku create dto
 *
 * @Author: xuyongkang
 * @Date 2023/8/3 14:00
 */
@Data
@Accessors(chain = true)
public class ProductSkuInfoCreateDTO implements Serializable {

    @ApiModelProperty("商品Id")
    private Long productId;

    private String skuCode;

    @ApiModelProperty("sku名称")
    private String skuName;

    @ApiModelProperty("sku介绍描述")
    private String skuDesc;

    @ApiModelProperty("默认图片")
    private String skuDefaultImg;

    @ApiModelProperty("库存")
    private Integer stock;

    @ApiModelProperty("原价")
    private Integer oldPrice;

    @ApiModelProperty("现价")
    private Integer newPrice;

    @ApiModelProperty("重量")
    private Integer weight;

    @ApiModelProperty("商品状态（0正常 1停用）")
    private Integer status;

    @ApiModelProperty("备注")
    private String remark;
}
