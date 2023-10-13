package com.phoenix.nirvana.order.dal.mysql.dataobject;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.phoenix.nirvana.mybatis.core.dataobject.BaseDO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

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
public class ProductOrderAuditDO extends BaseDO implements Serializable {

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

    @ApiModelProperty("备注")
    @TableField("remark")
    private String remark;
}
