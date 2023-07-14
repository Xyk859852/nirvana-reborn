package com.phoenix.nirvana.inventory.dal.mysql.mapper.stocklog;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.phoenix.nirvana.inventory.dal.mysql.dataobject.stocklog.InventoryProductStockLogDO;
import com.phoenix.nirvana.mybatis.core.query.LambdaQueryWrapperX;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 库存扣减日志表 Mapper 接口
 * </p>
 *
 * @author xuyongkang
 * @since 2023-07-14
 */
@Mapper
public interface InventoryProductStockLogMapper extends BaseMapper<InventoryProductStockLogDO> {


    default InventoryProductStockLogDO getLog(String orderNo, String skuCode) {
        return selectOne(new LambdaQueryWrapperX<InventoryProductStockLogDO>()
                .eqIfPresent(InventoryProductStockLogDO::getOrderNo, orderNo)
                .eqIfPresent(InventoryProductStockLogDO::getSkuCode, skuCode)
        );
    }

    default InventoryProductStockLogDO getLatestOne(String skuCode) {
        return selectOne(new LambdaQueryWrapperX<InventoryProductStockLogDO>()
                .eqIfPresent(InventoryProductStockLogDO::getSkuCode, skuCode)
                .orderByDesc(InventoryProductStockLogDO::getProductStockLogId)
                .last("limit 1")
        );
    }

}
