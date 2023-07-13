package com.phoenix.nirvana.order.service.order.impl;

import com.phoenix.nirvana.order.dal.mysql.dataobject.order.ProductOrderDO;
import com.phoenix.nirvana.order.dal.mysql.mapper.order.ProductOrderMapper;
import com.phoenix.nirvana.order.rpc.order.domain.dto.GenOrderIdDTO;
import com.phoenix.nirvana.order.rpc.order.domain.vo.GenOrderIdVO;
import com.phoenix.nirvana.order.service.generator.GenOrderNoService;
import com.phoenix.nirvana.order.service.order.ProductOrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 商品订单表 服务实现类
 * </p>
 *
 * @author xuyongkang
 * @since 2023-06-29
 */
@Service
public class ProductOrderServiceImpl extends ServiceImpl<ProductOrderMapper, ProductOrderDO> implements ProductOrderService {

    @Autowired
    private GenOrderNoService genOrderNoService;

    @Override
    public GenOrderIdVO genOrderId(GenOrderIdDTO genOrderIdDTO) {
        return new GenOrderIdVO().setOrderNo(genOrderNoService.genOrderNo(genOrderIdDTO.getBusinessIdentifier(), genOrderIdDTO.getUserId()));
    }
}
