package com.phoenix.nirvana.product.rpc.brand.domain.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 品牌分页列表查询返回数据对象
 *
 * @Description:
 * @Author: xuyongkang
 * @Date 2023/5/15 13:48
 */
@Data
@Accessors(chain = true)
@ApiModel("品牌分页列表查询返回数据对象")
public class ProductBrandPageListVO implements Serializable {

    @ApiModelProperty("品牌id")
    private Long brandId;

    @ApiModelProperty("品牌名")
    private String brandName;

    @ApiModelProperty("品牌logo地址")
    private String brandLogo;

    @ApiModelProperty("介绍")
    private String brandDescribe;

    @ApiModelProperty("显示状态[0-显示；1-不显示]")
    private String showStatus;

    @ApiModelProperty("检索首字母")
    private String firstLetter;

    @ApiModelProperty("排序")
    private Integer brandSort;

    @ApiModelProperty("品牌状态（0正常 1停用）")
    private Boolean enable;

    @ApiModelProperty("备注")
    private String remark;
}
