package com.phoenix.nirvana.product.dal.mysql.dataobject.catalog;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * <p>
 * 商品三级分类
 * </p>
 *
 * @author xuyongkang
 * @since 2023-05-10
 */
@Getter
@Setter
@TableName("product_catalog")
@ApiModel(value = "ProductCatalogDO对象", description = "商品三级分类")
public class ProductCatalogDO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("分类id")
    @TableId(value = "catalog_id", type = IdType.AUTO)
    private Long catalogId;

    @ApiModelProperty("分类名称")
    @TableField("catalog_name")
    private String catalogName;

    @ApiModelProperty("父分类id")
    @TableField("parent_id")
    private Long parentId;

    @ApiModelProperty("商家id")
    @TableField("shop_id")
    private Long shopId;

    @ApiModelProperty("商家名称")
    @TableField("shop_name")
    private String shopName;

    @ApiModelProperty("祖先节点")
    @TableField("ancestors")
    private String ancestors;

    @ApiModelProperty("层级")
    @TableField("catalog_level")
    private Integer catalogLevel;

    @ApiModelProperty("排序")
    @TableField("catalog_sort")
    private Integer catalogSort;

    @ApiModelProperty("图标地址")
    @TableField("catalog_icon")
    private String catalogIcon;

    @ApiModelProperty("商品状态（0正常 1停用）")
    @TableField("enable")
    private Boolean enable;
    @ApiModelProperty("备注")
    @TableField("remark")
    private String remark;
}
