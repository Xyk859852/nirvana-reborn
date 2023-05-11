package com.phoenix.nirvana.service.system.rpc.config.domain.dto;

import com.phoenix.nirvana.common.dto.PageParam;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 系统配置分页查询条件
 *
 * @Description:
 * @Author: xuyongkang
 * @Date 2023/2/20 2:46 PM
 */
@Data
@ApiModel("系统配置分页查询条件")
public class ConfigPageDTO extends PageParam implements Serializable {

    @ApiModelProperty("模糊查询")
    private String keyboard;
}
