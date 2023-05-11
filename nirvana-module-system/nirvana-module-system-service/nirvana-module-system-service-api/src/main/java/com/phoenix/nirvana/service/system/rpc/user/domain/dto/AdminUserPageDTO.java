package com.phoenix.nirvana.service.system.rpc.user.domain.dto;

import com.phoenix.nirvana.common.dto.PageParam;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@ApiModel("管理员分页查询")
@Accessors(chain = true)
public class AdminUserPageDTO extends PageParam implements Serializable {

    @ApiModelProperty("模糊查询")
    private String keyboard;

}
