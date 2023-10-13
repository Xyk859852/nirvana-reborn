package com.phoenix.nirvana.order.rpc.domain.vo;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 订单创建返回对象
 *
 * @Author: xuyongkang
 * @Date 2023/7/11 11:00
 */
@Data
@Accessors(chain = true)
public class OrderInfoVO implements Serializable {

    /**
     * 订单ID
     */
    private String orderNo;
}
