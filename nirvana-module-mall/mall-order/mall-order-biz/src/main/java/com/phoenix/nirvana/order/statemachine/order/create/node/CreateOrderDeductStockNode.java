package com.phoenix.nirvana.order.statemachine.order.create.node;

import com.phoenix.nirvana.inventory.rpc.InventoryRpc;
import com.phoenix.nirvana.inventory.rpc.dto.ReleaseProductStockDTO;
import com.phoenix.nirvana.order.convert.order.ProductOrderConvert;
import com.phoenix.nirvana.order.rpc.domain.dto.CreateOrderDTO;
import com.phoenix.nirvana.process.core.process.ProcessContext;
import com.phoenix.nirvana.process.core.process.RollbackProcessor;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.stereotype.Component;

/**
 * 订单商品库存扣减
 *
 * @Author: xuyongkang
 * @Date 2023/7/14 17:24
 */
@Slf4j
@Component
public class CreateOrderDeductStockNode extends RollbackProcessor {

    @DubboReference(retries = 0)
    InventoryRpc inventoryRpc;


    @Override
    protected void processInternal(ProcessContext context) {
        log.info("CreateOrderDeductStockNode.processInternal-> before deduct stock");
        CreateOrderDTO createOrder = context.get("createOrderDTO");
        inventoryRpc.deductProductStock(ProductOrderConvert.INSTANCE.convert2Deduct(createOrder));
        log.info("CreateOrderDeductStockNode.processInternal-> after deduct stock");
    }

    @Override
    protected void rollback(ProcessContext context) {
        CreateOrderDTO createOrder = context.get("createOrderDTO");
        doRollback(ProductOrderConvert.INSTANCE.convert2Release(createOrder));
    }

    public void doRollback(ReleaseProductStockDTO releaseProductStock) {
        inventoryRpc.releaseProductStock(releaseProductStock);
    }
}
