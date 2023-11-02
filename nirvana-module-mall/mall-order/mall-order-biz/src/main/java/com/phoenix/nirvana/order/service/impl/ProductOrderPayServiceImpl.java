package com.phoenix.nirvana.order.service.impl;

import com.phoenix.nirvana.order.dal.mysql.dataobject.ProductOrderPayDO;
import com.phoenix.nirvana.order.dal.mysql.mapper.ProductOrderPayMapper;
import com.phoenix.nirvana.order.service.ProductOrderPayService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 商品订单支付明细表 服务实现类
 * </p>
 *
 * @author xuyongkang
 * @since 2023-07-19
 */
@Service
public class ProductOrderPayServiceImpl extends ServiceImpl<ProductOrderPayMapper, ProductOrderPayDO> implements ProductOrderPayService {

}
