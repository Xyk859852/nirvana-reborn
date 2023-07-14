package com.phoenix.nirvana.inventory.rpc;

import com.phoenix.nirvana.common.vo.CommonResult;
import com.phoenix.nirvana.inventory.manager.InventoryManager;
import com.phoenix.nirvana.inventory.rpc.dto.DeductProductStockDTO;
import com.phoenix.nirvana.inventory.rpc.dto.ReleaseProductStockDTO;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 库存中心，商品库存操作
 *
 * @Author: xuyongkang
 * @Date 2023/7/14 14:51
 */
@Service
@DubboService
public class InventoryRpcImpl implements InventoryRpc {

    @Autowired
    InventoryManager inventoryManager;

    @Override
    public CommonResult<Boolean> deductProductStock(DeductProductStockDTO deductProductStockDTO) {
        return inventoryManager.deductProductStock(deductProductStockDTO);
    }

    @Override
    public CommonResult<Boolean> releaseProductStock(ReleaseProductStockDTO releaseProductStockDTO) {
        return inventoryManager.releaseProductStock(releaseProductStockDTO);
    }
}
