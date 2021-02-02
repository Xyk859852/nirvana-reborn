package com.phoenix.nirvana.admin.web.impl.dataobject;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

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
@TableName("sys_department")
public class SysDepartmentDO implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 组织id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 上级组织id
     */
    private Long pid;

    /**
     * 组织名称
     */
    private String name;

    /**
     * 父节点id，0顶级
     */
    @TableField("parentId")
    private Long parentId;

    /**
     * 组织代码
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
    private LocalDateTime createTime;

    /**
     * 修改时间
     */
    @TableField("modifyTime")
    private LocalDateTime modifyTime;

    /**
     * 排序
     */
    private Integer sort;

    /**
     * 部门负责人id
     */
    private Long uid;


}
