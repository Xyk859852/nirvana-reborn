package com.phoenix.nirvana.order.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.phoenix.nirvana.order.dal.mysql.dataobject.ProductOrderDO;
import com.phoenix.nirvana.order.rpc.domain.dto.CreateOrderDTO;
import com.phoenix.nirvana.order.rpc.domain.dto.GenOrderIdDTO;
import com.phoenix.nirvana.order.rpc.domain.vo.GenOrderIdVO;
import com.phoenix.nirvana.order.rpc.domain.vo.OrderInfoVO;

/**
 * <p>
 * 商品订单表 服务类
 * </p>
 *
 * @author xuyongkang
 * @since 2023-06-29
 */
public interface ProductOrderService extends IService<ProductOrderDO> {

    /**
     * 获取订单号
     *
     * @param genOrderIdDTO
     * @return
     */
    GenOrderIdVO genOrderId(GenOrderIdDTO genOrderIdDTO);

    /**
     * 订单创建
     *
     * @param createOrder
     * @return
     */
    OrderInfoVO createOrder(CreateOrderDTO createOrder);
}
