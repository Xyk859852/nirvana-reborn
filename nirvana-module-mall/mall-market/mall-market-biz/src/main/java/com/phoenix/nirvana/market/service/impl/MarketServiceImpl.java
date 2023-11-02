package com.phoenix.nirvana.market.service.impl;

import com.phoenix.nirvana.common.enums.CouponTypeEnum;
import com.phoenix.nirvana.common.exception.util.ServiceExceptionUtil;
import com.phoenix.nirvana.common.util.CollectionUtils;
import com.phoenix.nirvana.market.dal.mysql.dataobject.coupon.MarketCouponDO;
import com.phoenix.nirvana.market.dal.mysql.mapper.coupon.MarketCouponMapper;
import com.phoenix.nirvana.market.domain.dto.CalculateOrderAmountDTO;
import com.phoenix.nirvana.market.domain.vo.CalculateOrderAmountVO;
import com.phoenix.nirvana.market.service.MarketCouponService;
import com.phoenix.nirvana.market.service.MarketService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.phoenix.nirvana.market.enums.MarketErrorCodeConstants.USER_COUPON_IS_NULL;

/**
 * 云路信息科技有限公司 版权所有 ©Copyright 2023
 *
 * @Description: TODO
 * @Author: xuyongkang
 * @Date 2023/7/17 16:59
 */
@Slf4j
@Service
public class MarketServiceImpl implements MarketService {

    @Autowired
    MarketCouponMapper marketCouponMapper;

    @Autowired
    MarketCouponService marketCouponService;

    /**
     * 计算订单费用
     * <p>
     * 假设订单有两个商品条目记录，分摊优惠券的规则如下：
     * 商品1
     * 单价（单位分）    购买数量    小计
     * 1000            10         1000 * 10
     * <p>
     * 商品2
     * 单价    购买数量    小计
     * 100    1         100 * 1
     * <p>
     * <p>
     * 整单优惠券抵扣5元，也就是500分
     * <p>
     * 则商品1分摊的优惠券抵扣金额为：
     * 优惠券抵扣总额 * (商品1单价*商品1购买数量)/((商品1单价*商品1购买数量) + (商品2单价*商品2购买数量))
     * = 500 * (1000 * 10) / ((1000 * 10)  + (100 * 1) )
     * = 5000000 / 10100
     * = 495 分
     * <p>
     * 同样的逻辑可计算出商品2分摊的优惠券抵扣金额为5分，也就是0.05元
     * <p>
     * <p>
     * 如果计算出的优惠券分摊到一条 item 上存在小数时，则向上取整，然后最后一条 item 分摊的金额就用优惠金额减掉前面所有优惠的item分摊的总额
     *
     * @param calculateOrderAmount
     * @return
     */
    @Override
    public CalculateOrderAmountVO calculateOrderAmount(CalculateOrderAmountDTO calculateOrderAmount) {
        log.info("计算订单金额：{}", calculateOrderAmount);
        List<CalculateOrderAmountDTO.OrderItemDTO> orderItemList = calculateOrderAmount.getOrderItemList();
        List<CalculateOrderAmountDTO.OrderCouponDTO> orderCouponList = calculateOrderAmount.getOrderCouponList();
        String orderNo = calculateOrderAmount.getOrderNo();
        List<MarketCouponDO> marketCouponList;
        CalculateOrderAmountVO calculateOrderAmountResult = new CalculateOrderAmountVO();
        List<CalculateOrderAmountVO.OrderCouponDetailVO> orderCouponDetailList = new ArrayList<>();
        // 优惠券抵扣金额
        Integer discountAmount = 0;
        // 红包购买金额
        Integer redPaketAmount = 0;
        // 满减金额
        Integer conditionAmount = 0;
        // 订单总计
        Integer totalProductAmount = 0;
        if (!CollectionUtils.isAnyEmpty(orderCouponList)) {
            marketCouponList = calculateCouponAmount(orderCouponList);
            for (MarketCouponDO marketCoupon : marketCouponList) {
                CalculateOrderAmountVO.OrderCouponDetailVO orderCoupon = new CalculateOrderAmountVO.OrderCouponDetailVO();
                orderCoupon.setCouponType(marketCoupon.getType());
                if (marketCoupon.getType().equals(CouponTypeEnum.READ_PACKET_COUPON.getCode())) {
                    redPaketAmount = redPaketAmount + marketCoupon.getSaleAmount();
                    orderCoupon.setCouponTypeName(CouponTypeEnum.READ_PACKET_COUPON.getMsg());
                } else if (marketCoupon.getType().equals(CouponTypeEnum.CONDITION_COUPON.getCode())) {
                    conditionAmount = conditionAmount + marketCoupon.getConditionAmount();
                    orderCoupon.setCouponTypeName(CouponTypeEnum.CONDITION_COUPON.getMsg());
                } else {
                    orderCoupon.setCouponTypeName(CouponTypeEnum.CASH_COUPON.getMsg());
                }
                orderCoupon.setSalePrice(marketCoupon.getSaleAmount());
                orderCoupon.setDiscountPrice(marketCoupon.getAmount());
                orderCoupon.setDiscountRelationId(marketCoupon.getCouponId());
                orderCoupon.setOrderNo(orderNo);
                discountAmount = redPaketAmount + marketCoupon.getAmount();
                orderCouponDetailList.add(orderCoupon);
            }
        }
        for (CalculateOrderAmountDTO.OrderItemDTO orderItem : orderItemList) {
            totalProductAmount = orderItem.getSalePrice() * orderItem.getSaleQuantity();
        }
        int index = 0;
        int totalNum = orderItemList.size();
        Integer notLastItemTotalDiscountAmount = 0;
        calculateOrderAmountResult.setTotalProductAmount(totalProductAmount);
        calculateOrderAmountResult.setRedPaketAmount(redPaketAmount);
        calculateOrderAmountResult.setDiscountAmount(discountAmount);
        calculateOrderAmountResult.setConditionAmount(conditionAmount);
        totalProductAmount = totalProductAmount + redPaketAmount;
        if (totalProductAmount >= conditionAmount && conditionAmount != 0) {
            totalProductAmount = totalProductAmount - conditionAmount;
        }
        List<CalculateOrderAmountVO.OrderItemAmountDetailVO> orderItemAmountDetailList = new ArrayList<>();
        for (CalculateOrderAmountDTO.OrderItemDTO orderItem : orderItemList) {
            CalculateOrderAmountVO.OrderItemAmountDetailVO orderItemAmountDetail = new CalculateOrderAmountVO.OrderItemAmountDetailVO();
            orderItemAmountDetail.setOrderNo(orderNo);
            orderItemAmountDetail.setProductId(orderItem.getProductId());
            orderItemAmountDetail.setSkuCode(orderItem.getSkuCode());
            orderItemAmountDetail.setSalePrice(orderItem.getSalePrice());
            orderItemAmountDetail.setSaleQuantity(orderItem.getSaleQuantity());
            orderItemAmountDetail.setPackPrice(orderItem.getPackPrice());
            orderItemAmountDetail.setProductTotalPrice(orderItem.getSalePrice() * orderItem.getSaleQuantity());
            if (discountAmount > 0) {
                if (++index < totalNum) {
                    double partDiscountAmount = Integer.valueOf(discountAmount * orderItem.getSalePrice()
                            * orderItem.getSaleQuantity()).doubleValue() / Integer.valueOf(totalProductAmount).doubleValue();
                    // 遇到小数则向上取整
                    double curDiscountAmount = Math.ceil(partDiscountAmount);
                    orderItemAmountDetail.setDiscountsAmount(Double.valueOf(curDiscountAmount).intValue());
                    notLastItemTotalDiscountAmount += orderItemAmountDetail.getDiscountsAmount();
                } else {
                    orderItemAmountDetail.setDiscountsAmount(discountAmount - notLastItemTotalDiscountAmount);
                }
            }
            orderItemAmountDetailList.add(orderItemAmountDetail);
        }
        calculateOrderAmountResult.setOrderAmountDetailList(orderItemAmountDetailList);
        calculateOrderAmountResult.setOrderCouponDetailList(orderCouponDetailList);
        return calculateOrderAmountResult;
    }

    /**
     * 计算优惠卷优惠金额
     *
     * @return
     */
    private List<MarketCouponDO> calculateCouponAmount(List<CalculateOrderAmountDTO.OrderCouponDTO> orderCouponList) {
        List<MarketCouponDO> list = new ArrayList<>();
        for (CalculateOrderAmountDTO.OrderCouponDTO orderCoupon : orderCouponList) {
            MarketCouponDO marketCoupon = marketCouponMapper.selectById(orderCoupon.getCouponId());
            if (marketCoupon == null) {
                throw ServiceExceptionUtil.exception(USER_COUPON_IS_NULL);
            }
            list.add(marketCoupon);
        }
        return list;
    }
}
