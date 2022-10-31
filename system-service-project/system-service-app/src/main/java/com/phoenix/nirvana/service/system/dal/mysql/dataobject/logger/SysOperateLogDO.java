package com.phoenix.nirvana.service.system.dal.mysql.dataobject.logger;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler;
import com.phoenix.nirvana.tenant.core.db.TenantBaseDO;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Map;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 操作日志记录
 * </p>
 *
 * @author xuyongkang
 * @since 2022-10-20
 */
@Data
@TableName("sys_operate_log")
@ApiModel(value = "SysOperateLogDO对象", description = "操作日志记录")
public class SysOperateLogDO extends TenantBaseDO {

    private static final long serialVersionUID = 1L;

    /**
     * {@link #javaMethodArgs} 的最大长度
     */
    public static final Integer JAVA_METHOD_ARGS_MAX_LENGTH = 8000;

    /**
     * {@link #resultData} 的最大长度
     */
    public static final Integer RESULT_MAX_LENGTH = 4000;


    @ApiModelProperty("日志主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty("链路追踪编号")
    @TableField("trace_id")
    private String traceId;

    @ApiModelProperty("用户编号")
    @TableField("user_id")
    private Long userId;

    @ApiModelProperty("模块标题")
    @TableField("module")
    private String module;

    @ApiModelProperty("操作名")
    @TableField("name")
    private String name;

    @ApiModelProperty("操作分类")
    @TableField("operate_type")
    private Long operateType;

    @ApiModelProperty("操作内容")
    @TableField("content")
    private String content;

    @ApiModelProperty("拓展字段")
    @TableField(typeHandler = JacksonTypeHandler.class)
    private Map<String, Object> exts;

    @ApiModelProperty("请求方法名")
    @TableField("request_method")
    private String requestMethod;

    @ApiModelProperty("请求地址")
    @TableField("request_url")
    private String requestUrl;

    @ApiModelProperty("用户 IP")
    @TableField("user_ip")
    private String userIp;

    @ApiModelProperty("浏览器 UA")
    @TableField("user_agent")
    private String userAgent;

    @ApiModelProperty("Java 方法名")
    @TableField("java_method")
    private String javaMethod;

    @ApiModelProperty("Java 方法的参数")
    @TableField("java_method_args")
    private String javaMethodArgs;

    @ApiModelProperty("操作时间")
    @TableField("start_time")
    private Timestamp startTime;

    @ApiModelProperty("执行时长")
    @TableField("duration")
    private Integer duration;

    @ApiModelProperty("结果码")
    @TableField("result_code")
    private Integer resultCode;

    @ApiModelProperty("结果提示")
    @TableField("result_msg")
    private String resultMsg;

    @ApiModelProperty("结果数据")
    @TableField("result_data")
    private String resultData;
}
