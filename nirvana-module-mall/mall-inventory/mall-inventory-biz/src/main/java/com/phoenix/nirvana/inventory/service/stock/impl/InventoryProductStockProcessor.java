package com.phoenix.nirvana.inventory.service.stock.impl;

import com.phoenix.nirvana.common.exception.util.ServiceExceptionUtil;
import com.phoenix.nirvana.inventory.dal.mysql.dataobject.stock.InventoryProductStockDO;
import com.phoenix.nirvana.inventory.dal.mysql.dataobject.stocklog.InventoryProductStockLogDO;
import com.phoenix.nirvana.inventory.dal.mysql.mapper.stock.InventoryProductStockMapper;
import com.phoenix.nirvana.inventory.dal.mysql.mapper.stocklog.InventoryProductStockLogMapper;
import com.phoenix.nirvana.inventory.enums.StockLogStatusEnum;
import com.phoenix.nirvana.inventory.service.stock.bo.DeductStockBO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import static com.phoenix.nirvana.inventory.enums.InventoryErrorCodeConstants.RELEASE_PRODUCT_SKU_STOCK_ERROR;

/**
 * 扣减商品库存处理器
 *
 * @Author: xuyongkang
 * @Date 2023/7/14 15:49
 */
@Component
public class InventoryProductStockProcessor {

    @Autowired
    InventoryProductStockMapper inventoryProductStockMapper;

    @Autowired
    InventoryProductStockLogMapper inventoryProductStockLogMapper;

    @Transactional(rollbackFor = Exception.class)
    public void doDeduct(DeductStockBO deductStock) {
        inventoryProductStockMapper.deductProductStock(deductStock.getSkuCode(), deductStock.getSaleQuantity());
        inventoryProductStockLogMapper.insert(buildStockLog(deductStock));
    }

    @Transactional(rollbackFor = Exception.class)
    public void doRelease(String skuCode, Integer saleQuantity, InventoryProductStockLogDO stockLog) {
        int num = inventoryProductStockMapper.releaseProductStock(skuCode, saleQuantity);
        if (num <= 0) {
            throw ServiceExceptionUtil.exception(RELEASE_PRODUCT_SKU_STOCK_ERROR);
        }
        if (stockLog != null) {
            //修改扣减库存记录为已释放
            stockLog.setStatus(StockLogStatusEnum.RELEASED.getCode());
            inventoryProductStockLogMapper.updateById(stockLog);
        }
    }

    private InventoryProductStockLogDO buildStockLog(DeductStockBO deductStock) {

        InventoryProductStockDO productStockDO = deductStock.getProductStockDO();
        Long saleQuantity = deductStock.getSaleQuantity().longValue();
        Long originSaleStock = productStockDO.getSaleStockQuantity();
        // 通过扣减log获取原始已销售库存
        Long originSaledStock = getOriginSaledStock(productStockDO);

        InventoryProductStockLogDO logDO = new InventoryProductStockLogDO();
        logDO.setOrderNo(deductStock.getOrderNo());
        logDO.setSkuCode(deductStock.getSkuCode());
        logDO.setOriginSaleStockQuantity(originSaleStock);
        logDO.setOriginSaledStockQuantity(originSaledStock);
        logDO.setDeductedSaleStockQuantity(originSaleStock - saleQuantity);
        logDO.setIncreasedSaledStockQuantity(originSaledStock + saleQuantity);
        return logDO;
    }

    /**
     * 获取sku的原始已销售库存
     *
     * @param productStockDO
     * @return
     */
    private Long getOriginSaledStock(InventoryProductStockDO productStockDO) {
        //1、查询sku库存最近一笔扣减日志
        InventoryProductStockLogDO latestLog = inventoryProductStockLogMapper.getLatestOne(productStockDO.getSkuCode());

        //2、获取原始的已销售库存
        Long originSaledStock = null;
        if (null == latestLog) {
            //第一次扣，直接取productStockDO的saledStockQuantity
            originSaledStock = productStockDO.getSaledStockQuantity();
        } else {
            //取最近一笔扣减日志的increasedSaledStockQuantity
            originSaledStock = latestLog.getIncreasedSaledStockQuantity();
        }
        return originSaledStock;
    }


}
