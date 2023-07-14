package com.phoenix.nirvana.service.system.rpc.role.domain.dto;

import com.phoenix.nirvana.common.dto.PageParam;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@ApiModel("角色分页查询")
@Accessors(chain = true)
public class RolePageDTO extends PageParam implements Serializable {

    @ApiModelProperty("模糊查询")
    private String keyboard;

}
