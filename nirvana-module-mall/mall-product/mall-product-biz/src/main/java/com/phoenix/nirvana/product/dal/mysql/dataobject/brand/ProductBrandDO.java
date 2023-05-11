package com.phoenix.nirvana.product.dal.mysql.dataobject.brand;

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

/**
 * <p>
 * 品牌
 * </p>
 *
 * @author xuyongkang
 * @since 2023-05-10
 */
@Getter
@Setter
@TableName("product_brand")
@ApiModel(value = "ProductBrandDO对象", description = "品牌")
public class ProductBrandDO extends BaseDO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("品牌id")
    @TableId(value = "brand_id", type = IdType.AUTO)
    private Long brandId;

    @ApiModelProperty("品牌名")
    @TableField("brand_name")
    private String brandName;

    @ApiModelProperty("品牌logo地址")
    @TableField("brand_logo")
    private String brandLogo;

    @ApiModelProperty("介绍")
    @TableField("brand_describe")
    private String brandDescribe;

    @ApiModelProperty("显示状态[0-显示；1-不显示]")
    @TableField("show_status")
    private String showStatus;

    @ApiModelProperty("检索首字母")
    @TableField("first_letter")
    private String firstLetter;

    @ApiModelProperty("排序")
    @TableField("brand_sort")
    private Integer brandSort;

    @ApiModelProperty("品牌状态（0正常 1停用）")
    @TableField("enable")
    private Boolean enable;

    @ApiModelProperty("备注")
    @TableField("remark")
    private String remark;
}
