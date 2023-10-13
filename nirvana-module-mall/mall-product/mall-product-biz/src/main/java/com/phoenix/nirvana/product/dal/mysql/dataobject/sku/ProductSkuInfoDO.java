package com.phoenix.nirvana.product.dal.mysql.dataobject.sku;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.phoenix.nirvana.mybatis.core.dataobject.BaseDO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 商品sku信息
 * </p>
 *
 * @author xuyongkang
 * @since 2023-05-10
 */
@Data
@Accessors(chain = true)
@TableName("product_sku_info")
@ApiModel(value = "ProductSkuInfoDO对象", description = "商品sku信息")
public class ProductSkuInfoDO extends BaseDO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("skuId")
    @TableId(value = "sku_id", type = IdType.AUTO)
    private Long skuId;

    @ApiModelProperty("商品Id")
    @TableField("product_id")
    private Long productId;

    @TableField("sku_code")
    private String skuCode;

    @ApiModelProperty("sku名称")
    @TableField("sku_name")
    private String skuName;

    @ApiModelProperty("sku介绍描述")
    @TableField("sku_desc")
    private String skuDesc;

    @ApiModelProperty("默认图片")
    @TableField("sku_default_img")
    private String skuDefaultImg;

    @ApiModelProperty("库存")
    @TableField("stock")
    private Integer stock;

    @ApiModelProperty("原价")
    @TableField("old_price")
    private Integer oldPrice;

    @ApiModelProperty("现价")
    @TableField("new_price")
    private Integer newPrice;

    @ApiModelProperty("重量")
    @TableField("weight")
    private Integer weight;

    @ApiModelProperty("商品状态（0正常 1停用）")
    @TableField("status")
    private Integer status;

    @ApiModelProperty("备注")
    @TableField("remark")
    private String remark;

}
