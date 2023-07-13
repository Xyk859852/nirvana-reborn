package com.phoenix.nirvana.order.service.generator.impl;

import com.phoenix.nirvana.common.exception.ServiceException;
import com.phoenix.nirvana.common.util.DateUtil;
import com.phoenix.nirvana.common.util.NumberUtil;
import com.phoenix.nirvana.order.enums.OrderNoTypeEnum;
import com.phoenix.nirvana.order.service.generator.GenOrderNoService;
import com.phoenix.nirvana.order.service.generator.SegmentIDGen;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

import static com.phoenix.nirvana.order.enums.OrderErrorCodeConstants.ORDER_NO_TYPE_ERROR;

/**
 * 基于美团leaf id生成
 *
 * @Description:
 * @Author: xuyongkang
 * @Date 2023/7/6 17:37
 */
@Service
public class GenOrderNoServiceImpl implements GenOrderNoService {

    @Autowired
    SegmentIDGen segmentIDGen;


    @Override
    public String genOrderNo(Integer orderNoType, String userId) {
        OrderNoTypeEnum orderNoTypeEnum = OrderNoTypeEnum.getByCode(orderNoType);
        if (orderNoTypeEnum == null) {
            throw new ServiceException(ORDER_NO_TYPE_ERROR);
        }
        return orderNoType + getOrderIdKey(orderNoType, userId);
    }

    /**
     * 获取订单ID
     */
    private String getOrderIdKey(Integer orderNoType, String userId) {
        // 他其实是一个字符串的拼接，这块订单号生成，其实一直都没太大的变化
        // 订单号的生成，yymmdd年月日 + 序列号 + 用户id后三位，订单号里可以反映出来，时间，当天第几个订单，哪个用户来生成的
        return getDateTimeKey() + getAutoNoKey(orderNoType) + getUserIdKey(userId);
    }

    /**
     * 生成订单号的中间6位日期
     */
    private String getDateTimeKey() {
        return DateUtil.format(new Date(), "yyMMdd");
    }

    /**
     * 生成订单号中间的8位序列号
     */
    private String getAutoNoKey(Integer orderNoType) {
        // 基于数据库的内存双缓冲发号，全局唯一的序号发号
        // 对于这个序号，我们是不能直接拼接在字符串，订单号是对外暴露出去的
        // 导致一个问题，订单号是全局增长的，竞对通过订单号，就可以猜出来你历史上一共有多少订单，泄漏商业机密
        // 混淆加密，数字序号，long，混淆，转换为一个其他的唯一的数字
        Long autoNo = segmentIDGen.genNewNo(orderNoType.toString());
        return String.valueOf(NumberUtil.genNo(autoNo, 8));
    }

    /**
     * 截取用户ID的后三位
     */
    private String getUserIdKey(String userId) {
        // 如果userId的长度大于或等于3，则直接返回
        if (userId.length() >= 3) {
            return userId.substring(userId.length() - 3);
        }

        // 如果userId的长度大于或等于3，则直接前面补0
        StringBuilder userIdKey = new StringBuilder(userId);
        while (userIdKey.length() != 3) {
            userIdKey.insert(0, "0");
        }
        return userIdKey.toString();
    }
}
