package com.phoenix.nirvana.order.dal.mysql.dataobject;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * CREATE TABLE `order_auto_no` (
 * `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
 * `biz_tag` varchar(32) NOT NULL COMMENT '业务标识',
 * `max_id` bigint(20) NOT NULL COMMENT '最大ID',
 * `step` int(11) NOT NULL COMMENT '步长',
 * `desc` varchar(255) DEFAULT NULL COMMENT '备注',
 * `gmt_create` datetime NOT NULL COMMENT '创建时间',
 * `gmt_modified` datetime NOT NULL COMMENT '更新时间',
 * PRIMARY KEY (`id`) USING BTREE,
 * UNIQUE KEY `uk_biz_tag` (`biz_tag`) USING BTREE
 * ) ENGINE=InnoDB AUTO_INCREMENT=104 DEFAULT CHARSET=utf8 COMMENT='订单编号表';
 *
 * @author xuyongkang
 * @version 1.0
 */
@Data
@TableName("order_auto_no")
public class OrderAutoNoDO implements Serializable {

    /**
     * 主键ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 业务标识
     */
    private String bizTag;

    /**
     * 号段最大值
     */
    private Long maxId;

    /**
     * 下一个号段的步长
     */
    private Integer step;

    /**
     * 说明
     */
    private String desc;

    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private Date gmtCreate;

    /**
     * 更新时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date gmtModified;

}
