package com.phoenix.nirvana.service.system.dal.mysql.dataobject;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 *
 * </p>
 *
 * @author Xuyk
 * @since 2021-02-01
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@TableName("sys_post")
public class SysPostDO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 职务id
     */
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 部门id
     */
    @TableField("deptId")
    private Long deptId;

    /**
     * 职务名称
     */
    private String name;

    /**
     * 职务代码
     */
    private String code;

    /**
     * 是否可用
     */
    @TableField("isEnable")
    private Boolean isEnable;

    /**
     * 是否删除
     */
    @TableField("isDeleted")
    private Boolean isDeleted;

    /**
     * 创建时间
     */
    @TableField("createTime")
    private Date createTime;

    /**
     * 修改时间
     */
    @TableField("modifyTime")
    private Date modifyTime;

    /**
     * 排序
     */
    private Integer sort;


}
