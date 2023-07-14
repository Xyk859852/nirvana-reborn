package com.phoenix.nirvana.order.rpc.order.domain.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 云路信息科技有限公司 版权所有 ©Copyright 2023
 *
 * @Description: TODO
 * @Author: xuyongkang
 * @Date 2023/7/6 11:08
 */
@Data
@Accessors(chain = true)
@ApiModel("预生成订单号")
public class GenOrderIdVO implements Serializable {

    @ApiModelProperty("订单号")
    private String orderNo;
}
