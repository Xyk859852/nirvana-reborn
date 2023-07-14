package com.phoenix.nirvana.order.dal.mysql.mapper.order;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.phoenix.nirvana.common.exception.ServiceException;
import com.phoenix.nirvana.order.dal.mysql.dataobject.order.OrderAutoNoDO;
import org.apache.ibatis.annotations.*;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.phoenix.nirvana.order.enums.OrderErrorCodeConstants.ORDER_AUTO_NO_GEN_ERROR;

/**
 * 云路信息科技有限公司 版权所有 ©Copyright 2023
 *
 * @Description:
 * @Author: xuyongkang
 * @Date 2023/7/6 17:20
 */
@Mapper
public interface OrderAutoNoMapper extends BaseMapper<OrderAutoNoDO> {

    /**
     * 更新maxid
     *
     * @param bizTag 业务标识
     * @return 返回
     */
    @Update("UPDATE order_auto_no SET max_id = max_id + step WHERE biz_tag = #{bizTag}")
    int updateMaxId(@Param("bizTag") String bizTag);

    /**
     * bizTag查询
     * <p>
     * 查到我的这个事务视图里，可以看到的max_id和step，当前这条数据
     *
     * @param bizTag 业务标识
     * @return 返回
     */
    @Select("SELECT * FROM order_auto_no WHERE biz_tag = #{bizTag}")
    @Results(value = {@Result(id = true, column = "id", property = "id"), @Result(column = "biz_tag", property = "bizTag"), @Result(column = "max_id", property = "maxId"), @Result(column = "step", property = "step"), @Result(column = "desc", property = "desc"), @Result(column = "gmt_create", property = "gmtCreate"), @Result(column = "gmt_modified", property = "gmtModified")})
    OrderAutoNoDO findByBizTag(@Param("bizTag") String bizTag);

    /**
     * 使用动态计算出来额步长更新maxid
     *
     * @param bizTag      业务tag
     * @param dynamicStep 动态计算出来的步长
     * @return 返回
     */
    @Update("UPDATE order_auto_no SET max_id = max_id + #{step} WHERE biz_tag = #{bizTag}")
    int updateMaxIdByDynamicStep(@Param("bizTag") String bizTag, @Param("step") int dynamicStep);

    /**
     * 获取所有bizTag
     *
     * @return 返回
     */
    @Select("SELECT biz_tag FROM order_auto_no")
    List<String> listAllBizTag();


    @Transactional(rollbackFor = Exception.class)
    default OrderAutoNoDO updateMaxIdAndGet(String bizTag) {
        int ret = updateMaxId(bizTag);
        if (ret != 1) {
            throw new ServiceException(ORDER_AUTO_NO_GEN_ERROR);
        }
        return findByBizTag(bizTag);
    }

    @Transactional(rollbackFor = Exception.class)
    default OrderAutoNoDO updateMaxIdByDynamicStepAndGet(String bizTag, int nextStep) {
        // maxid=10000,5000，15000,10000~15000,是你的一个新的分段
        int ret = updateMaxIdByDynamicStep(bizTag, nextStep);
        if (ret != 1) {
            throw new ServiceException(ORDER_AUTO_NO_GEN_ERROR);
        }
        return findByBizTag(bizTag);
    }
}
