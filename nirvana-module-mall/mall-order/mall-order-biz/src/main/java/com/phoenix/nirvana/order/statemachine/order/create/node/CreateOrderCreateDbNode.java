package com.phoenix.nirvana.order.statemachine.order.create.node;

import com.phoenix.nirvana.common.util.CollectionUtils;
import com.phoenix.nirvana.order.builder.FullOrderData;
import com.phoenix.nirvana.order.dal.mongodb.OrderOperateLogDO;
import com.phoenix.nirvana.order.dal.mysql.dataobject.ProductOrderDO;
import com.phoenix.nirvana.order.dal.mysql.dataobject.ProductOrderItemDO;
import com.phoenix.nirvana.order.dal.mysql.dataobject.ProductOrderPayDO;
import com.phoenix.nirvana.order.dal.mysql.mapper.ProductOrderDiscountMapper;
import com.phoenix.nirvana.order.dal.mysql.mapper.ProductOrderPayMapper;
import com.phoenix.nirvana.order.dal.mysql.mapper.order.ProductOrderMapper;
import com.phoenix.nirvana.order.dal.mysql.mapper.orderiterm.ProductOrderItemMapper;
import com.phoenix.nirvana.process.core.process.ProcessContext;
import com.phoenix.nirvana.process.core.process.StandardProcessor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.support.TransactionSynchronizationAdapter;
import org.springframework.transaction.support.TransactionSynchronizationManager;
import org.springframework.transaction.support.TransactionTemplate;

import java.util.List;

/**
 * 订单入库节点
 *
 * @Author: xuyongkang
 * @Date 2023/7/27 15:35
 */
@Slf4j
@Component
public class CreateOrderCreateDbNode extends StandardProcessor {

    @Autowired
    ProductOrderMapper productOrderMapper;

    @Autowired
    ProductOrderItemMapper productOrderItemMapper;

    @Autowired
    ProductOrderDiscountMapper productOrderDiscountMapper;

    @Autowired
    ProductOrderPayMapper productOrderPayMapper;

    @Autowired
    MongoTemplate mongoTemplate;

    @Autowired
    TransactionTemplate transactionTemplate;


    @Override
    protected void processInternal(ProcessContext context) {
        log.info("订单数据落库...");
        transactionTemplate.execute(status -> {
            FullOrderData fullOrderData = context.get("fullOrderData");
            ProductOrderDO productOrder = fullOrderData.getProductOrder();
            if (productOrder != null) {
                productOrderMapper.insert(productOrder);
            }
            List<ProductOrderItemDO> productOrderItem = fullOrderData.getProductOrderItem();
            if (!CollectionUtils.isAnyEmpty(productOrderItem)) {
                productOrderItemMapper.insertBatch(productOrderItem);
            }
            ProductOrderPayDO productOrderPay = fullOrderData.getProductOrderPay();
            if (productOrderPay != null) {
                productOrderPayMapper.insert(productOrderPay);
            }
            TransactionSynchronizationManager.registerSynchronization(new TransactionSynchronizationAdapter() {
                @Override
                public void afterCommit() {
                    OrderOperateLogDO orderOperateLog = fullOrderData.getOrderOperateLog();
                    if (orderOperateLog != null) {
                        mongoTemplate.insert(orderOperateLog);
                    }
                }
            });
            return true;
        });

    }
}
