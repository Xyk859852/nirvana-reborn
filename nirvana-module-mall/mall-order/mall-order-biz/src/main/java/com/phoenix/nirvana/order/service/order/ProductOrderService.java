package com.phoenix.nirvana.order.service.order;

import com.baomidou.mybatisplus.extension.service.IService;
import com.phoenix.nirvana.order.dal.mysql.dataobject.order.ProductOrderDO;
import com.phoenix.nirvana.order.rpc.order.domain.dto.GenOrderIdDTO;
import com.phoenix.nirvana.order.rpc.order.domain.vo.GenOrderIdVO;

/**
 * <p>
 * 商品订单表 服务类
 * </p>
 *
 * @author xuyongkang
 * @since 2023-06-29
 */
public interface ProductOrderService extends IService<ProductOrderDO> {

    GenOrderIdVO genOrderId(GenOrderIdDTO genOrderIdDTO);
}
