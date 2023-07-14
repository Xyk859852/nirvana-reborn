package com.phoenix.nirvana.service.system.dal.mysql.dataobject.user;

import com.baomidou.mybatisplus.annotation.*;
import com.phoenix.nirvana.mybatis.core.dataobject.BaseDO;
import com.phoenix.nirvana.tenant.core.db.TenantBaseDO;
import lombok.Data;
import lombok.EqualsAndHashCode;
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
@EqualsAndHashCode(callSuper = false)
@TableName("sys_user")
public class SysUserDO extends TenantBaseDO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 用户名
     */
    private String userName;

    /**
     * 用户手机号
     */
    private String phone;

    /**
     * 密码
     */
    private String password;

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
    private Long deptId;

    /**
     * 岗位id
     */
    private Long postId;

    /**
     * 角色id
     */
    private Long roleId;

    /**
     * 0、正常 1、禁用
     */
    private Boolean enable;


}
