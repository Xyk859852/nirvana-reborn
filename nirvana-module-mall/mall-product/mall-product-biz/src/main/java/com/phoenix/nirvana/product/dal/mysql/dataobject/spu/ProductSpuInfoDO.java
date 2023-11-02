package com.phoenix.nirvana.product.dal.mysql.dataobject.spu;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.phoenix.nirvana.mybatis.core.dataobject.BaseDO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 商品spu信息
 * </p>
 *
 * @author xuyongkang
 * @since 2023-05-10
 */
@Getter
@Setter
@TableName("product_spu_info")
@ApiModel(value = "ProductSpuInfoDO对象", description = "商品spu信息")
public class ProductSpuInfoDO extends BaseDO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("商品id")
    @TableId(value = "product_id", type = IdType.AUTO)
    private Long productId;

    @ApiModelProperty("商品编码")
    @TableField("product_code")
    private String productCode;

    @ApiModelProperty("商品名称")
    @TableField("product_name")
    private String productName;

    @ApiModelProperty("默认图片")
    @TableField("product_default_img")
    private String productDefaultImg;

    @ApiModelProperty("商品轮播图地址")
    @TableField("product_carousel_url")
    private String productCarouselUrl;

    @ApiModelProperty("商品标签")
    @TableField("product_label")
    private String productLabel;

    @ApiModelProperty("商品描述")
    @TableField("product_desc")
    private String productDesc;

    @ApiModelProperty("商品计量单位")
    @TableField("product_unit")
    private String productUnit;

    @ApiModelProperty("商品新品")
    @TableField("product_new")
    private String productNew;

    @ApiModelProperty("商品热卖")
    @TableField("product_hot")
    private String productHot;

    @ApiModelProperty("商品在售")
    @TableField("product_onsale")
    private String productOnsale;

    @ApiModelProperty("所属分类id")
    @TableField("catalog_id")
    private Long catalogId;

    @ApiModelProperty("分类名称")
    @TableField("catalog_name")
    private String catalogName;

    @ApiModelProperty("品牌id")
    @TableField("brand_id")
    private Long brandId;

    @ApiModelProperty("所属品牌名称")
    @TableField("brand_name")
    private String brandName;

    @ApiModelProperty("所属商家id")
    @TableField("shop_id")
    private Long shopId;

    @ApiModelProperty("所属商家名称")
    @TableField("shop_name")
    private String shopName;

    @ApiModelProperty("商品规格（0无 1有）")
    @TableField("has_sku")
    private String hasSku;

    @ApiModelProperty("上架状态[-1 已创建 0 - 上架，1 - 下架]")
    @TableField("publish_status")
    private String publishStatus;

    @ApiModelProperty("原价")
    @TableField("old_price")
    private Integer oldPrice;

    @ApiModelProperty("现价")
    @TableField("new_price")
    private Integer newPrice;

    @ApiModelProperty("包装费")
    @TableField("pack_fee")
    private Integer packFee;

    @ApiModelProperty("售卖开始时间")
    @TableField("sale_start_time")
    private LocalDateTime saleStartTime;

    @ApiModelProperty("售卖结束时间")
    @TableField("sale_end_time")
    private LocalDateTime saleEndTime;

    @ApiModelProperty("是否必选（0非 1是）")
    @TableField("must_choose")
    private String mustChoose;

    @ApiModelProperty("日销量")
    @TableField("sale_count_day")
    private Integer saleCountDay;

    @ApiModelProperty("月销量")
    @TableField("sale_count_month")
    private Integer saleCountMonth;

    @ApiModelProperty("起售份数")
    @TableField("sale_start_unit")
    private Integer saleStartUnit;

    @ApiModelProperty("商品状态（0正常 1停用）")
    @TableField("enable")
    private Boolean enable;

    @ApiModelProperty("备注")
    @TableField("remark")
    private String remark;
}
