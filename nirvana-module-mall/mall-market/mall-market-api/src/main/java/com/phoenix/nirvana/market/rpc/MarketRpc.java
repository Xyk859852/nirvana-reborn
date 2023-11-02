package com.phoenix.nirvana.market.rpc;

import com.phoenix.nirvana.common.vo.CommonResult;
import com.phoenix.nirvana.market.domain.dto.CalculateOrderAmountDTO;
import com.phoenix.nirvana.market.domain.vo.CalculateOrderAmountVO;

/**
 * 营销服务 rpc接口
 *
 * @Author: xuyongkang
 * @Date 2023/7/17 15:25
 */
public interface MarketRpc {


    /**
     * 订单接口计算
     *
     * @param calculateOrderAmount
     * @return
     */
    CommonResult<CalculateOrderAmountVO> calculateOrderAmount(CalculateOrderAmountDTO calculateOrderAmount);
}
