package com.phoenix.nirvana.admin.web.impl.dataobject;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;


/**
 * <p>
 * 系统用户
 * </p>
 *
 * @author Xuyk
 * @since 2021-01-26
 */
@Data
@Accessors(chain = true)
@TableName("sys_user")
public class SysUserDO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 用户名
     */
    @TableField("userName")
    private String userName;

    /**
     * 用户手机号
     */
    private String phone;

    /**
     * 密码
     */
    @TableField("passWord")
    private String passWord;

    /**
     * 用户性别
     */
    private String sex;

    /**
     * 用户邮箱
     */
    private String email;

    /**
     * 用户头像地址
     */
    @TableField("avatar")
    private String avatar;


    /**
     * 部门id
     */
    @TableField("departmentId")
    private Long departmentId;

    /**
     * 岗位id
     */
    @TableField("postId")
    private Long postId;

    /**
     * 角色id
     */
    @TableField("roleId")
    private Long roleId;

    /**
     * 创建时间
     */
    @TableField("createTime")
    private Date createTime;

    /**
     * 是否删除:0:正常，1:删除
     */
    @TableLogic
    @TableField("isDeleted")
    private Boolean isDeleted;

    /**
     * 0、正常 1、禁用
     */
    @TableField("isEnable")
    private Boolean isEnable;

    /**
     * 最后修改日期的时间
     */
    @TableField("lastPasswordResetTime")
    private Date lastPasswordResetTime;


}
