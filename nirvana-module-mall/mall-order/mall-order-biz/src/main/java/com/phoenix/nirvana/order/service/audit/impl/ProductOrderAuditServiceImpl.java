package com.phoenix.nirvana.order.service.audit.impl;

import com.phoenix.nirvana.order.dal.mysql.dataobject.audit.ProductOrderAuditDO;
import com.phoenix.nirvana.order.dal.mysql.mapper.audit.ProductOrderAuditMapper;
import com.phoenix.nirvana.order.service.audit.ProductOrderAuditService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 商品订单审核明细表 服务实现类
 * </p>
 *
 * @author xuyongkang
 * @since 2023-06-29
 */
@Service
public class ProductOrderAuditServiceImpl extends ServiceImpl<ProductOrderAuditMapper, ProductOrderAuditDO> implements ProductOrderAuditService {

}
