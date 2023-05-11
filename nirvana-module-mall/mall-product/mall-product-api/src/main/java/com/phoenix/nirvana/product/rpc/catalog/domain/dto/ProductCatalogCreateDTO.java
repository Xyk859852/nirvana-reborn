package com.phoenix.nirvana.product.rpc.catalog.domain.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 商品三级分类新增
 *
 * @Description:
 * @Author: xuyongkang
 * @Date 2023/5/11 14:48
 */
@Data
@Accessors(chain = true)
@ApiModel("商品三级分类新增Model")
public class ProductCatalogCreateDTO implements Serializable {

    @ApiModelProperty("分类名称")
    private String catalogName;

    @ApiModelProperty("父分类id")
    private Long parentId;

    @ApiModelProperty("商家id")
    private Long shopId;

    @ApiModelProperty("祖先节点")
    private String ancestors;

    @ApiModelProperty("层级")
    private Integer catalogLevel;

    @ApiModelProperty("排序")
    private Integer catalogSort;

    @ApiModelProperty("图标地址")
    private String catalogIcon;

    @ApiModelProperty("商品状态（0正常 1停用）")
    private Boolean enable;

    @ApiModelProperty("备注")
    private String remark;
}
