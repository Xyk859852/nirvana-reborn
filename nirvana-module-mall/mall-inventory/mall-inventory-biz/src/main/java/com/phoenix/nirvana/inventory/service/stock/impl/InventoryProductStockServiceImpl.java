package com.phoenix.nirvana.inventory.service.stock.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.phoenix.nirvana.cache.redis.core.RedisLock;
import com.phoenix.nirvana.common.exception.util.ServiceExceptionUtil;
import com.phoenix.nirvana.common.util.CollectionUtils;
import com.phoenix.nirvana.common.util.StringUtils;
import com.phoenix.nirvana.inventory.dal.mysql.dataobject.stock.InventoryProductStockDO;
import com.phoenix.nirvana.inventory.dal.mysql.dataobject.stocklog.InventoryProductStockLogDO;
import com.phoenix.nirvana.inventory.dal.mysql.mapper.stock.InventoryProductStockMapper;
import com.phoenix.nirvana.inventory.dal.mysql.mapper.stocklog.InventoryProductStockLogMapper;
import com.phoenix.nirvana.inventory.enums.StockLogStatusEnum;
import com.phoenix.nirvana.inventory.rpc.dto.DeductProductStockDTO;
import com.phoenix.nirvana.inventory.rpc.dto.ReleaseProductStockDTO;
import com.phoenix.nirvana.inventory.service.stock.InventoryProductStockService;
import com.phoenix.nirvana.inventory.service.stock.bo.DeductStockBO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static com.phoenix.nirvana.common.enums.CoreConstant.DEFAULT_WAIT_SECONDS;
import static com.phoenix.nirvana.common.enums.RedisLockKeyConstants.DEDUCT_PRODUCT_STOCK_KEY;
import static com.phoenix.nirvana.inventory.enums.InventoryErrorCodeConstants.*;

/**
 * <p>
 * 库存中心的商品库存表 服务实现类
 * </p>
 *
 * @author xuyongkang
 * @since 2023-07-14
 */
@Slf4j
@Service
public class InventoryProductStockServiceImpl extends ServiceImpl<InventoryProductStockMapper, InventoryProductStockDO> implements InventoryProductStockService {

    @Autowired
    RedisLock redisLock;

    @Autowired
    InventoryProductStockMapper inventoryProductStockMapper;

    @Autowired
    InventoryProductStockLogMapper inventoryProductStockLogMapper;

    @Autowired
    InventoryProductStockProcessor inventoryProductStockProcessor;

    @Override
    public Boolean deductProductStock(DeductProductStockDTO deductProductStock) {
        checkDeductProductStockDTO(deductProductStock);
        List<DeductProductStockDTO.OrderItemDTO> orderItemList = deductProductStock.getOrderItemList();
        orderItemList = orderItemList.stream().sorted(Comparator.comparing(DeductProductStockDTO.OrderItemDTO::getSkuCode)).collect(Collectors.toList());
        String orderNo = deductProductStock.getOrderNo();
        for (DeductProductStockDTO.OrderItemDTO orderItem : orderItemList) {
            String skuCode = orderItem.getSkuCode();
            String lockKey = DEDUCT_PRODUCT_STOCK_KEY + skuCode;
            try {
                // 1、添加redis锁扣库存锁
                // (1)防同一笔订单重复扣减
                // (2)重量级锁，保证mysql+redis扣库存的原子性，同一时间只能有一个订单来扣，
                // 需要锁查询+扣库存
                // 获取不到锁，阻塞等待

                // 不会说无限制的加锁等待，如果说要是加锁时间过长，3秒钟都没拿到锁，就直接释放掉
                if (!redisLock.tryLock(lockKey, DEFAULT_WAIT_SECONDS)) {
                    log.error("key: {} get lock error", skuCode);
                    throw ServiceExceptionUtil.exception(DEDUCT_PRODUCT_SKU_STOCK_CANNOT_ACQUIRE_ERROR);
                }
                // 2、查询mysql库存数据
                InventoryProductStockDO productStock = getStockBySkuCode(skuCode);
                InventoryProductStockLogDO stockLog = inventoryProductStockLogMapper.getLog(orderNo, skuCode);
                if (stockLog != null) {
                    log.info("已扣减过，扣减库存日志已存在,orderNo={},skuCode={}", orderNo, skuCode);
                    return true;
                }
                // 3、查询库存扣减日志
                Integer saleQuantity = orderItem.getSaleQuantity();
                DeductStockBO deductStock = new DeductStockBO(orderNo, skuCode, saleQuantity, productStock);
                inventoryProductStockProcessor.doDeduct(deductStock);
            } finally {
                redisLock.unlock(lockKey);
            }

        }
        return true;
    }

    @Override
    public Boolean releaseProductStock(ReleaseProductStockDTO releaseProductStock) {
        checkDeductProductStockDTO(releaseProductStock);
        List<DeductProductStockDTO.OrderItemDTO> orderItemList = releaseProductStock.getOrderItemList();
        orderItemList = orderItemList.stream().sorted(Comparator.comparing(DeductProductStockDTO.OrderItemDTO::getSkuCode)).collect(Collectors.toList());
        String orderNo = releaseProductStock.getOrderNo();
        for (DeductProductStockDTO.OrderItemDTO orderItem : orderItemList) {
            String skuCode = orderItem.getSkuCode();
            Integer saleQuantity = orderItem.getSaleQuantity();
            String lockKey = DEDUCT_PRODUCT_STOCK_KEY + skuCode;
            try {
                if (!redisLock.tryLock(lockKey, DEFAULT_WAIT_SECONDS)) {
                    log.error("key: {} get lock error", skuCode);
                    throw ServiceExceptionUtil.exception(DEDUCT_PRODUCT_SKU_STOCK_CANNOT_ACQUIRE_ERROR);
                }
                getStockBySkuCode(skuCode);
                InventoryProductStockLogDO stockLog = inventoryProductStockLogMapper.getLog(orderNo, skuCode);
                if (stockLog == null) {
                    log.info("暂无扣减库存日志，直接返回，orderNo={},skuCode={}", orderNo, skuCode);
                    return true;
                }
                if (null != stockLog && stockLog.getStatus().equals(StockLogStatusEnum.RELEASED.getCode())) {
                    log.info("已释放过库存,orderId={},skuCode={}", orderNo, skuCode);
                    return true;
                }
                inventoryProductStockProcessor.doRelease(skuCode, saleQuantity, stockLog);
            } finally {
                redisLock.unlock(lockKey);
            }
        }
        return true;
    }

    private void checkDeductProductStockDTO(DeductProductStockDTO deductProductStock) {
        String orderNo = deductProductStock.getOrderNo();
        if (!StringUtils.hasText(orderNo)) {
            throw ServiceExceptionUtil.exception(DEDUCT_PRODUCT_STOCK_ORDER_NO_ERROR);
        }
        List<DeductProductStockDTO.OrderItemDTO> orderItemList = deductProductStock.getOrderItemList();
        if (CollectionUtils.isAnyEmpty(orderItemList)) {
            throw ServiceExceptionUtil.exception(DEDUCT_PRODUCT_STOCK_ORDER_ITERM_ERROR);
        }
    }

    private InventoryProductStockDO getStockBySkuCode(String skuCode) {
        InventoryProductStockDO productStock = inventoryProductStockMapper.getBySkuCode(skuCode);
        if (productStock == null) {
            log.error("{}: get sku stock in db is null", skuCode);
            throw ServiceExceptionUtil.exception(PRODUCT_SKU_STOCK_NOT_FOUND_ERROR);
        }
        return productStock;
    }

}
