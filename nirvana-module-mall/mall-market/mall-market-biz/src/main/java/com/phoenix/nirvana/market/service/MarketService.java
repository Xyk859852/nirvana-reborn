package com.phoenix.nirvana.market.service;

import com.phoenix.nirvana.market.domain.dto.CalculateOrderAmountDTO;
import com.phoenix.nirvana.market.domain.vo.CalculateOrderAmountVO;

/**
 * 营销服务业务api
 *
 * @Author: xuyongkang
 * @Date 2023/7/17 16:55
 */
public interface MarketService {


    /**
     * 订单金额计算
     *
     * @param calculateOrderAmount
     * @return
     */
    CalculateOrderAmountVO calculateOrderAmount(CalculateOrderAmountDTO calculateOrderAmount);
}
