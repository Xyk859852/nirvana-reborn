package com.phoenix.nirvana.service.system.dal.mysql.dataobject.tenant;

import com.baomidou.mybatisplus.annotation.TableName;
import com.phoenix.nirvana.common.enums.CommonStatusEnum;
import com.phoenix.nirvana.mybatis.core.dataobject.BaseDO;
import com.phoenix.nirvana.service.system.dal.mysql.dataobject.user.SysUserDO;
import lombok.*;

import java.util.Date;

/**
 * 租户 DO
 *
 * @author xuyongkang
 */
@TableName(value = "sys_tenant", autoResultMap = true)
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SysTenantDO extends BaseDO {

    /**
     * 套餐编号 - 系统
     */
    public static final Long PACKAGE_ID_SYSTEM = 0L;

    /**
     * 租户编号，自增
     */
    private Long id;
    /**
     * 租户名，唯一
     */
    private String name;
    /**
     * 联系人的用户编号
     *
     * 关联 {@link SysUserDO#getId()}
     */
    private Long contactUserId;
    /**
     * 联系人
     */
    private String contactName;
    /**
     * 联系手机
     */
    private String contactMobile;
    /**
     * 租户状态
     *
     * 枚举 {@link CommonStatusEnum}
     */
    private Integer status;
    /**
     * 绑定域名
     *
     * TODO xyk：目前是预留字段，未来会支持根据域名，自动查询到对应的租户。等等
     */
    private String domain;
    /**
     * 租户套餐编号
     *
     * 关联 {@link SysTenantPackageDO#getId()}
     * 特殊逻辑：系统内置租户，不使用套餐，暂时使用 {@link #PACKAGE_ID_SYSTEM} 标识
     */
    private Long packageId;
    /**
     * 过期时间
     */
    private Date expireTime;
    /**
     * 账号数量
     */
    private Integer accountCount;

}
