package com.phoenix.nirvana.service.system.dal.mysql.dataobject.dict;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;
import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 字典表
 * </p>
 *
 * @author Xuyk
 * @since 2022-09-13
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("sys_dict")
@ApiModel(value="SysDict对象", description="字典表")
public class SysDictDO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "字典id")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "字典名称")
    private String name;

    @ApiModelProperty(value = "字典值")
    private String value;

    @ApiModelProperty(value = "类型id")
    private Long pid;

    @ApiModelProperty(value = "0.类型 1.字典")
    private Integer type;

    @ApiModelProperty(value = "创建人")
    private Long creator;

    @ApiModelProperty(value = "修改人")
    private Long updater;

    @ApiModelProperty(value = "创建日期")
    private Date createTime;

    @ApiModelProperty(value = "修改日期")
    private Date updateTime;

    @ApiModelProperty(value = "是否删除 0未删除，1删除")
    private Boolean deleted;


}
