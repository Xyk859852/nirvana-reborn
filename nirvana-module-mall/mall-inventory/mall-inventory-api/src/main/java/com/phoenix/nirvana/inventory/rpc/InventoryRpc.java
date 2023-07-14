package com.phoenix.nirvana.inventory.rpc;

import com.phoenix.nirvana.common.vo.CommonResult;
import com.phoenix.nirvana.inventory.rpc.dto.DeductProductStockDTO;
import com.phoenix.nirvana.inventory.rpc.dto.ReleaseProductStockDTO;

/**
 * 库存中心，商品库存操作
 *
 * @Author: xuyongkang
 * @Date 2023/7/14 14:43
 */
public interface InventoryRpc {

    /**
     * 扣减商品库存
     *
     * @param deductProductStockDTO
     * @return
     */
    CommonResult<Boolean> deductProductStock(DeductProductStockDTO deductProductStockDTO);

    /**
     * 取消订单 释放商品库存
     *
     * @param releaseProductStockDTO
     * @return
     */
    CommonResult<Boolean> releaseProductStock(ReleaseProductStockDTO releaseProductStockDTO);

}
