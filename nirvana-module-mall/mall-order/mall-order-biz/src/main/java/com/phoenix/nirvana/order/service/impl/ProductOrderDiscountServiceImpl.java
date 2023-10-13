package com.phoenix.nirvana.order.service.impl;

import com.phoenix.nirvana.order.dal.mysql.dataobject.ProductOrderDiscountDO;
import com.phoenix.nirvana.order.dal.mysql.mapper.ProductOrderDiscountMapper;
import com.phoenix.nirvana.order.service.ProductOrderDiscountService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 商品订单优惠明细表 服务实现类
 * </p>
 *
 * @author xuyongkang
 * @since 2023-07-19
 */
@Service
public class ProductOrderDiscountServiceImpl extends ServiceImpl<ProductOrderDiscountMapper, ProductOrderDiscountDO> implements ProductOrderDiscountService {

}
