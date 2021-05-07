package com.phoenix.nirvana.admin.web.impl.dataobject;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 菜单表
 * </p>
 *
 * @author Xuyk
 * @since 2021-02-01
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@TableName("sys_menu")
public class SysMenuDO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 上级ID
     */
    private Long pid;

    /**
     * 标题
     */
    private String title;

    /**
     * 类型 0、菜单 1、功能
     */
    private Integer type;

    /**
     * 排序
     */
    private Integer sort;

    /**
     * 地址
     */
    private String url;

    /**
     * 0、管理平台1、App
     */
    @TableField("platform")
    private Integer platform;

    /**
     * 是否是外链，0是，1否
     */
    @TableField("iFrame")
    private Boolean iFrame;

    /**
     * 权限编码
     */
    @TableField("permCode")
    private String permCode;

    /**
     * 图标
     */
    private String icon;

    /**
     * 子节点数量
     */
    @TableField("subCount")
    private Integer subCount;

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
     * 是否使用
     */
    @TableField("isEnable")
    private Boolean isEnable;

}
