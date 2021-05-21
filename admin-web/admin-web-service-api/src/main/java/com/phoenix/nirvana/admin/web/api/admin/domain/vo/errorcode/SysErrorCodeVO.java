package com.phoenix.nirvana.admin.web.api.admin.domain.vo.errorcode;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

@Data
@Accessors(chain = true)
public class SysErrorCodeVO {

    /**
     * 主键id
     */
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
     * 1、自动生成，2、手动便捷
     */
    private Integer type;

    /**
     * 分组名称
     */
    private String group;

    /**
     * 备注
     */
    private String memo;

    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 最后更新时间
     */
    private Date updateTime;

}
