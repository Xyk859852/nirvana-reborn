package com.phoenix.nirvana.product.rpc.attr.domain.vo;

import com.phoenix.nirvana.product.rpc.attr.domain.dto.ProductAttrValuesUpdateDTO;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 云路信息科技有限公司 版权所有 ©Copyright 2023
 *
 * @Description: TODO
 * @Author: xuyongkang
 * @Date 2023/5/11 11:31
 */
@Data
@Accessors(chain = true)
@ApiModel("商品属性明细")
public class ProductAttrValuesVO extends ProductAttrValuesUpdateDTO implements Serializable {
}
