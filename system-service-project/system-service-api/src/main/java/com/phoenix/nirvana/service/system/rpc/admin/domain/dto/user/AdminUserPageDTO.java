package com.phoenix.nirvana.service.system.rpc.admin.domain.dto.user;

import com.phoenix.nirvana.common.vo.PageParam;
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
