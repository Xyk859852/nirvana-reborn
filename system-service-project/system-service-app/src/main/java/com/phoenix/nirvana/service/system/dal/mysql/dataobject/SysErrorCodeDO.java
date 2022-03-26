package com.phoenix.nirvana.service.system.dal.mysql.dataobject;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.phoenix.nirvana.mybatis.core.dataobject.BaseDO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

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
public class SysErrorCodeDO extends BaseDO implements Serializable {

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


}
