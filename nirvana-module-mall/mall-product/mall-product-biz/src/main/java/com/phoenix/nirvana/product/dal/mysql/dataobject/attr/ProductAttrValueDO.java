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
@TableName("product_attr_value")
@ApiModel(value = "ProductAttrValueDO对象", description = "商品属性")
public class ProductAttrValueDO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("属性value id")
    @TableId(value = "attr_value_id", type = IdType.AUTO)
    private Long attrValueId;

    @ApiModelProperty("商品属性id")
    @TableField("attr_id")
    private Long attrId;

    @ApiModelProperty("属性值名称")
    @TableField("attr_value_name")
    private String attrValueName;

    @ApiModelProperty("属性 值排序")
    @TableField("attr_value_sort")
    private Integer attrValueSort;
}
