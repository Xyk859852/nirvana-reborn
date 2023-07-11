package com.phoenix.nirvana.order.rpc.order;

import com.phoenix.nirvana.common.vo.CommonResult;
import com.phoenix.nirvana.order.rpc.order.domain.dto.GenOrderIdDTO;
import com.phoenix.nirvana.order.rpc.order.domain.vo.GenOrderIdVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

import static com.phoenix.nirvana.order.enums.MallOrderApiConstants.ORDER_SERVICE_APP;
import static com.phoenix.nirvana.order.enums.MallOrderApiConstants.ORDER_SERVICE_APP_PREFIX;

/**
 * 订单操作
 *
 * @Description:
 * @Author: xuyongkang
 * @Date 2023/7/6 09:59
 */
@FeignClient(name = ORDER_SERVICE_APP, path = ORDER_SERVICE_APP_PREFIX + "/order")
public interface ProductOrderRpc {


    /**
     * 预生成订单号
     *
     * @param genOrderIdDTO
     * @return
     */
    CommonResult<GenOrderIdVO> genOrderId(@Valid @RequestBody GenOrderIdDTO genOrderIdDTO);
}
