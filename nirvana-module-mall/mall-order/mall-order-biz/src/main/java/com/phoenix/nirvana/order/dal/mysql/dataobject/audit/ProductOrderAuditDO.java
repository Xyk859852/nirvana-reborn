package com.phoenix.nirvana.order.dal.mysql.dataobject.audit;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.sql.Timestamp;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 商品订单审核明细表
 * </p>
 *
 * @author xuyongkang
 * @since 2023-06-29
 */
@Getter
@Setter
@TableName("product_order_audit")
@ApiModel(value = "ProductOrderAuditDO对象", description = "商品订单审核明细表")
public class ProductOrderAuditDO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("订单审核明细id ")
    @TableId(value = "order_audit_id", type = IdType.AUTO)
    private Long orderAuditId;

    @ApiModelProperty("订单编号 ")
    @TableField("order_no")
    private Long orderNo;

    @ApiModelProperty("申请人id")
    @TableField("apply_user_id")
    private Long applyUserId;

    @ApiModelProperty("申请人姓名")
    @TableField("apply_user_name")
    private String applyUserName;

    @ApiModelProperty("审核人id")
    @TableField("audit_user_id")
    private Long auditUserId;

    @ApiModelProperty("审核人姓名")
    @TableField("audit_user_name")
    private String auditUserName;

    @ApiModelProperty("审核状态 -1 审核拒绝  0审核中  1审核通过")
    @TableField("status")
    private String status;

    @ApiModelProperty("删除标志（0代表存在 1代表删除）")
    @TableField("deleted")
    private String deleted;

    @ApiModelProperty("创建者")
    @TableField("creator")
    private Long creator;

    @ApiModelProperty("创建时间")
    @TableField("create_time")
    private Timestamp createTime;

    @ApiModelProperty("更新者")
    @TableField("updater")
    private Long updater;

    @ApiModelProperty("更新时间")
    @TableField("update_time")
    private Timestamp updateTime;

    @ApiModelProperty("备注")
    @TableField("remark")
    private String remark;
}
