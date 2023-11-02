package com.phoenix.nirvana.order.rpc.domain.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 订单号预生成
 *
 * @Description:
 * @Author: xuyongkang
 * @Date 2023/7/6 11:04
 */
@Data
@Accessors(chain = true)
@ApiModel("订单号预生成")
public class GenOrderIdDTO implements Serializable {

    /**
     * 业务线标识
     * 目前仅仅支持一种业务标识，就是自营商城，B2C，大B端，直接对c端出售商品
     */
    @ApiModelProperty("业务线标识")
    private Integer businessIdentifier;

    /**
     * 用户ID
     */
    @ApiModelProperty("用户ID")
    @NotNull(message = "用户ID不能为null")
    private String userId;
}
