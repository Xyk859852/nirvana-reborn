package com.phoenix.nirvana.inventory.service.stocklog.impl;

import com.phoenix.nirvana.inventory.dal.mysql.dataobject.stocklog.InventoryProductStockLogDO;
import com.phoenix.nirvana.inventory.dal.mysql.mapper.stocklog.InventoryProductStockLogMapper;
import com.phoenix.nirvana.inventory.service.stocklog.InventoryProductStockLogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 库存扣减日志表 服务实现类
 * </p>
 *
 * @author xuyongkang
 * @since 2023-07-14
 */
@Service
public class InventoryProductStockLogServiceImpl extends ServiceImpl<InventoryProductStockLogMapper, InventoryProductStockLogDO> implements InventoryProductStockLogService {

}
