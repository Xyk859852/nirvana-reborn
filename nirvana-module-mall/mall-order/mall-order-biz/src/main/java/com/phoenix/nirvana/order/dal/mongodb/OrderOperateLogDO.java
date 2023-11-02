package com.phoenix.nirvana.order.dal.mongodb;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.IndexDirection;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 订单操作日志表
 * </p>
 *
 * @author xuyongkang
 */
@Data
@Document("order_operate_log") // 我们要把写入mongodb的数据打上一些注解标注
public class OrderOperateLogDO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @Id
    @Indexed
    private String id;

    /**
     * 创建时间
     */
    @Field(value = "gmt_create")
    private Date gmtCreate;

    /**
     * 更新时间
     */
    @Field(value = "gmt_modified")
    private Date gmtModified;

    /**
     * 订单编号
     */
    @Indexed(name = "idx_order_no", direction = IndexDirection.ASCENDING, background = true)
    @Field(value = "order_no")
    private String orderNo;

    /**
     * 操作类型
     */
    @Field(value = "operate_type")
    private Integer operateType;

    /**
     * 前置状态
     */
    @Field(value = "pre_status")
    private Integer preStatus;

    /**
     * 当前状态
     */
    @Field(value = "current_status")
    private Integer currentStatus;

    /**
     * 备注说明
     */
    @Field(value = "remark")
    private String remark;

}
