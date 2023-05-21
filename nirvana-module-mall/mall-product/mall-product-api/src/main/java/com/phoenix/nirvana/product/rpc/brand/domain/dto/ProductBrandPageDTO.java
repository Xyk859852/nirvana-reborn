package com.phoenix.nirvana.product.rpc.brand.domain.dto;

import com.phoenix.nirvana.common.dto.PageParam;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 品牌列表查询DTO
 *
 * @Description:
 * @Author: xuyongkang
 * @Date 2023/5/15 13:46
 */
@Data
@Accessors(chain = true)
@ApiModel("品牌列表查询参数")
public class ProductBrandPageDTO extends PageParam implements Serializable {

    @ApiModelProperty("模糊查询")
    private String keyword;
}
