package com.phoenix.nirvana.service.system.dal.mysql.dataobject.permission;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.phoenix.nirvana.mybatis.core.dataobject.BaseDO;
import com.phoenix.nirvana.service.system.rpc.auth.permission.tree.PermissionOperation;
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
@TableName("sys_permission")
public class SysPermissionDO extends BaseDO implements Serializable {

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
    private Integer platForm;

    /**
     * 是否是外链，0是，1否
     */
    @TableField("iFrame")
    private Boolean iFrame;

    /**
     * 权限编码
     */
    private String permCode;

    /**
     * 图标
     */
    private String icon;

    /**
     * 子节点数量
     */
    private Integer subCount;

    /**
     * 是否使用
     */
    private Boolean enable;

    public <T> T execute(PermissionOperation<T> permissionOperation){
        return permissionOperation.doExecute(this);
    }

}
