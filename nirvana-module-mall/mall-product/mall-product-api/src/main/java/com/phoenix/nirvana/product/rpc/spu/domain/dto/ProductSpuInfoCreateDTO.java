package com.phoenix.nirvana.product.rpc.spu.domain.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * @Description: TODO
 * @Author: xuyongkang
 * @Date 2023/5/10 18:45
 */
@Data
@Accessors(chain = true)
@ApiModel("商品SPU新增")
public class ProductSpuInfoCreateDTO {

    @ApiModelProperty("商品编码")
    private String productCode;

    @ApiModelProperty("商品名称")
    private String productName;

    @ApiModelProperty("默认图片")
    private String productDefaultImg;

    @ApiModelProperty("商品轮播图地址")
    private String productCarouselUrl;

    @ApiModelProperty("商品标签")
    private String productLabel;

    @ApiModelProperty("商品描述")
    private String productDesc;

    @ApiModelProperty("商品计量单位")
    private String productUnit;

    @ApiModelProperty("商品新品")
    private String productNew;

    @ApiModelProperty("商品热卖")
    private String productHot;

    @ApiModelProperty("商品在售")
    private String productOnsale;

    @ApiModelProperty("所属分类id")
    private Long catalogId;

    @ApiModelProperty("品牌id")
    private Long brandId;

    @ApiModelProperty("所属商家id")
    private Long shopId;

    @ApiModelProperty("商品规格（0无 1有）")
    private String hasSku;

    @ApiModelProperty("上架状态[-1 已创建 0 - 上架，1 - 下架]")
    private String publishStatus;

    @ApiModelProperty("原价")
    private BigDecimal oldPrice;

    @ApiModelProperty("现价")
    private BigDecimal newPrice;

    @ApiModelProperty("包装费")
    private BigDecimal packFee;

    @ApiModelProperty("售卖开始时间")
    private Timestamp saleStartTime;

    @ApiModelProperty("售卖结束时间")
    private Timestamp saleEndTime;

    @ApiModelProperty("是否必选（0非 1是）")
    private String mustChoose;

    @ApiModelProperty("起售份数")
    private Integer saleStartUnit;

    @ApiModelProperty("商品状态（0正常 1停用）")
    private Boolean enable;

    @ApiModelProperty("备注")
    private String remark;
}
