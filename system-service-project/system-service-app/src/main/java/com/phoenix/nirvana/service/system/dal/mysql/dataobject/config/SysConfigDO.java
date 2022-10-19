package com.phoenix.nirvana.service.system.dal.mysql.dataobject.config;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 参数配置表
 * </p>
 *
 * @author xuyongkang
 * @since 2022-10-19
 */
@Getter
@Setter
@TableName("sys_config")
@ApiModel(value = "SysConfigDO对象", description = "参数配置表")
public class SysConfigDO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("参数主键")
    @TableId(value = "config_id", type = IdType.AUTO)
    private Integer configId;

    @ApiModelProperty("参数名称")
    @TableField("config_name")
    private String configName;

    @ApiModelProperty("参数键名")
    @TableField("config_key")
    private String configKey;

    @ApiModelProperty("参数键值")
    @TableField("config_value")
    private String configValue;

    @ApiModelProperty("系统内置（Y是 N否）")
    @TableField("config_type")
    private String configType;

    @ApiModelProperty("创建者")
    @TableField("create_by")
    private String createBy;

    @ApiModelProperty("创建时间")
    @TableField("create_time")
    private Date createTime;

    @ApiModelProperty("更新者")
    @TableField("update_by")
    private String updateBy;

    @ApiModelProperty("更新时间")
    @TableField("update_time")
    private Date updateTime;

    @ApiModelProperty("备注")
    @TableField("remark")
    private String remark;
}
