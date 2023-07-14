package com.phoenix.nirvana.product.dal.mysql.dataobject.attr;

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
 * 商品属性
 * </p>
 *
 * @author xuyongkang
 * @since 2023-05-10
 */
@Getter
@Setter
@TableName("product_attr")
@ApiModel(value = "ProductAttrDO对象", description = "商品属性")
public class ProductAttrDO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("商品属性id")
    @TableId(value = "attr_id", type = IdType.AUTO)
    private Long attrId;

    @ApiModelProperty("商品id")
    @TableField("product_id")
    private Long productId;

    @ApiModelProperty("属性名")
    @TableField("attr_name")
    private String attrName;

    @ApiModelProperty("属性排序")
    @TableField("attr_sort")
    private Integer attrSort;
}
