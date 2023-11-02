package com.phoenix.nirvana.order.rpc;

import com.phoenix.nirvana.common.vo.CommonResult;
import com.phoenix.nirvana.order.manager.ProductOrderManager;
import com.phoenix.nirvana.order.rpc.domain.dto.GenOrderIdDTO;
import com.phoenix.nirvana.order.rpc.domain.vo.GenOrderIdVO;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 云路信息科技有限公司 版权所有 ©Copyright 2023
 *
 * @Description: TODO
 * @Author: xuyongkang
 * @Date 2023/7/6 11:18
 */
@Service
@DubboService
public class ProductOrderRpcImpl implements ProductOrderRpc {

    @Autowired
    ProductOrderManager productOrderManager;

    @Override
    public CommonResult<GenOrderIdVO> genOrderId(GenOrderIdDTO genOrderIdDTO) {
        return productOrderManager.genOrderId(genOrderIdDTO);
    }
}
