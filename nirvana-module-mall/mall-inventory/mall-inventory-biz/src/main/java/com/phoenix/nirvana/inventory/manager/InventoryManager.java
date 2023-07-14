package com.phoenix.nirvana.inventory.manager;

import com.phoenix.nirvana.common.vo.CommonResult;
import com.phoenix.nirvana.inventory.rpc.dto.DeductProductStockDTO;
import com.phoenix.nirvana.inventory.rpc.dto.ReleaseProductStockDTO;
import com.phoenix.nirvana.inventory.service.stock.InventoryProductStockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 商品库存操作 manager
 *
 * @Description: TODO
 * @Author: xuyongkang
 * @Date 2023/7/14 14:52
 */
@Component
public class InventoryManager {

    @Autowired
    InventoryProductStockService inventoryProductStockService;

    public CommonResult<Boolean> deductProductStock(DeductProductStockDTO deductProductStockDTO) {
        return CommonResult.success(inventoryProductStockService.deductProductStock(deductProductStockDTO));
    }

    public CommonResult<Boolean> releaseProductStock(ReleaseProductStockDTO releaseProductStockDTO) {
        return CommonResult.success(inventoryProductStockService.releaseProductStock(releaseProductStockDTO));
    }
}
