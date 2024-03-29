package com.phoenix.nirvana.service.system.dal.mysql.dataobject.dept;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.phoenix.nirvana.tenant.core.db.TenantBaseDO;
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
@TableName("sys_department")
public class SysDepartmentDO extends TenantBaseDO implements Serializable {

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
    private Long parentId;

    /**
     * 组织代码
     */
    private String code;

    /**
     * 是否可用
     */
    private Boolean enable;

    /**
     * 排序
     */
    private Integer sort;

    /**
     * 部门负责人id
     */
    private Long uid;


}
