package com.phoenix.nirvana.market.rpc;

import com.phoenix.nirvana.common.vo.CommonResult;
import com.phoenix.nirvana.market.domain.dto.CalculateOrderAmountDTO;
import com.phoenix.nirvana.market.domain.vo.CalculateOrderAmountVO;
import com.phoenix.nirvana.market.manager.MarketManager;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 营销服务实现
 *
 * @Author: xuyongkang
 * @Date 2023/7/17 15:59
 */
@Service
@DubboService
public class MarketRpcImpl implements MarketRpc {

    @Autowired
    MarketManager marketManager;


    @Override
    public CommonResult<CalculateOrderAmountVO> calculateOrderAmount(CalculateOrderAmountDTO calculateOrderAmount) {
        return marketManager.calculateOrderAmount(calculateOrderAmount);
    }
}
