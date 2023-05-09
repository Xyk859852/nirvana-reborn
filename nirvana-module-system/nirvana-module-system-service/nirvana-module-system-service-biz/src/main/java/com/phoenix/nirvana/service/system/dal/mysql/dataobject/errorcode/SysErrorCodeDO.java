package com.phoenix.nirvana.service.system.dal.mysql.dataobject.errorcode;

import com.baomidou.mybatisplus.annotation.*;
import com.phoenix.nirvana.mybatis.core.dataobject.BaseDO;
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
@TableName("sys_error_code")
public class SysErrorCodeDO implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 主键id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 错误代码
     */
    private Integer code;

    /**
     * 错误提示
     */
    private String message;

    /**
     * 1、自动生成，2、手动编辑
     */
    private Integer type;

    /**
     * 分组名称
     */
    @TableField("`group`")
    private String group;

    /**
     * 备注
     */
    private String memo;

    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;
    /**
     * 最后更新时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

}
