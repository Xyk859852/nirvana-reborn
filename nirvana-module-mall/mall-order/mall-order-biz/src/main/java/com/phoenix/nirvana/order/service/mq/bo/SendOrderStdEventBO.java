package com.phoenix.nirvana.order.service.mq.bo;

import com.phoenix.nirvana.order.enums.OrderStatusChangeEnum;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 云路信息科技有限公司 版权所有 ©Copyright 2023
 *
 * @Description: TODO
 * @Author: xuyongkang
 * @Date 2023/7/11 16:07
 */
@Data
@Accessors(chain = true)
public class SendOrderStdEventBO {

    /**
     * 订单状态变化枚举
     */
    private OrderStatusChangeEnum orderStatusChangeEnum;

    /**
     * 订单号
     */
    private String orderNo;

}
