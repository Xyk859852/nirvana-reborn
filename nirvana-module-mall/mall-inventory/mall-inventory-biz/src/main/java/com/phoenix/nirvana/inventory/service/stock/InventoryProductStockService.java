package com.phoenix.nirvana.inventory.service.stock;

import com.baomidou.mybatisplus.extension.service.IService;
import com.phoenix.nirvana.inventory.dal.mysql.dataobject.stock.InventoryProductStockDO;
import com.phoenix.nirvana.inventory.rpc.dto.DeductProductStockDTO;
import com.phoenix.nirvana.inventory.rpc.dto.ReleaseProductStockDTO;

/**
 * <p>
 * 库存中心的商品库存表 服务类
 * </p>
 *
 * @author xuyongkang
 * @since 2023-07-14
 */
public interface InventoryProductStockService extends IService<InventoryProductStockDO> {

    /**
     * 订单创建，扣减商品库存
     *
     * @param deductProductStock
     * @return
     */
    Boolean deductProductStock(DeductProductStockDTO deductProductStock);


    /**
     * 订单取消，释放商品库存
     *
     * @param releaseProductStock
     * @return
     */
    Boolean releaseProductStock(ReleaseProductStockDTO releaseProductStock);
}
