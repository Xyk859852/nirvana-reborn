package com.phoenix.nirvana.order.manager;

import com.phoenix.nirvana.common.vo.CommonResult;
import com.phoenix.nirvana.order.rpc.order.domain.dto.GenOrderIdDTO;
import com.phoenix.nirvana.order.rpc.order.domain.vo.GenOrderIdVO;
import com.phoenix.nirvana.order.service.order.ProductOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 订单操作 Manager
 *
 * @Description:
 * @Author: xuyongkang
 * @Date 2023/7/6 11:21
 */
@Component
public class ProductOrderManager {

    @Autowired
    ProductOrderService productOrderService;

    public CommonResult<GenOrderIdVO> genOrderId(GenOrderIdDTO genOrderIdDTO) {
        return CommonResult.success(productOrderService.genOrderId(genOrderIdDTO));
    }

}
