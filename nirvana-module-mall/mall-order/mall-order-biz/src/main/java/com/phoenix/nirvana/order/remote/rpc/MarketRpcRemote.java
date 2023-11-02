package com.phoenix.nirvana.order.remote.rpc;

import com.phoenix.nirvana.common.vo.CommonResult;
import com.phoenix.nirvana.market.domain.dto.CalculateOrderAmountDTO;
import com.phoenix.nirvana.market.domain.vo.CalculateOrderAmountVO;
import com.phoenix.nirvana.market.rpc.MarketRpc;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.stereotype.Component;

/**
 * 营销服务调用
 *
 * @Author: xuyongkang
 * @Date 2023/7/19 14:24
 */
@Component
public class MarketRpcRemote {

    @DubboReference(retries = 0)
    MarketRpc marketRpc;

    public CalculateOrderAmountVO calculateOrderAmount(CalculateOrderAmountDTO calculateOrderAmount) {
        CommonResult<CalculateOrderAmountVO> result = marketRpc.calculateOrderAmount(calculateOrderAmount);
        return result.getCheckedData();
    }
}
