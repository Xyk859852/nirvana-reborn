package com.phoenix.nirvana.product.rpc.sku.domain.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 商品 Sku 详情数据
 *
 * @Description: TODO
 * @Author: xuyongkang
 * @Date 2023/7/17 11:26
 */
@Data
@Accessors(chain = true)
public class ProductSkuInfoVO implements Serializable {

    @ApiModelProperty("skuId")
    private Long skuId;

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
    private String status;

    @ApiModelProperty("备注")
    private String remark;
}
