package com.phoenix.nirvana.order.service.orderiterm.impl;

import com.phoenix.nirvana.order.dal.mysql.dataobject.orderiterm.ProductOrderItemDO;
import com.phoenix.nirvana.order.dal.mysql.mapper.orderiterm.ProductOrderItemMapper;
import com.phoenix.nirvana.order.service.orderiterm.ProductOrderItemService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 商品订单明细表 服务实现类
 * </p>
 *
 * @author xuyongkang
 * @since 2023-07-13
 */
@Service
public class ProductOrderItemServiceImpl extends ServiceImpl<ProductOrderItemMapper, ProductOrderItemDO> implements ProductOrderItemService {

}
