package com.phoenix.nirvana.market.manager;

import com.phoenix.nirvana.common.vo.CommonResult;
import com.phoenix.nirvana.market.domain.dto.CalculateOrderAmountDTO;
import com.phoenix.nirvana.market.domain.vo.CalculateOrderAmountVO;
import com.phoenix.nirvana.market.service.MarketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 营销服务 manager
 *
 * @Author: xuyongkang
 * @Date 2023/7/17 16:53
 */
@Component
public class MarketManager {

    @Autowired
    MarketService marketService;

    public CommonResult<CalculateOrderAmountVO> calculateOrderAmount(CalculateOrderAmountDTO calculateOrderAmount) {
        return CommonResult.success(marketService.calculateOrderAmount(calculateOrderAmount));
    }

}
