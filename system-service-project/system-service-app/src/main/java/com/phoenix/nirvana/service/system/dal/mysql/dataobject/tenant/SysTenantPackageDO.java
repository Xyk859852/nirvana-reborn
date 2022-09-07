package com.phoenix.nirvana.service.system.dal.mysql.dataobject.tenant;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.phoenix.nirvana.common.enums.CommonStatusEnum;
import com.phoenix.nirvana.mybatis.core.dataobject.BaseDO;
import com.phoenix.nirvana.mybatis.core.type.JsonLongSetTypeHandler;
import lombok.*;

import java.util.Set;

/**
 * 租户套餐 DO
 *
 * @author xuyongkang
 */
@TableName(value = "sys_tenant_package", autoResultMap = true)
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SysTenantPackageDO extends BaseDO {

    /**
     * 套餐编号，自增
     */
    private Long id;
    /**
     * 套餐名，唯一
     */
    private String name;
    /**
     * 租户状态
     *
     * 枚举 {@link CommonStatusEnum}
     */
    private Integer status;
    /**
     * 备注
     */
    private String remark;
    /**
     * 关联的菜单编号
     */
    @TableField(typeHandler = JsonLongSetTypeHandler.class)
    private Set<Long> menuIds;

}
