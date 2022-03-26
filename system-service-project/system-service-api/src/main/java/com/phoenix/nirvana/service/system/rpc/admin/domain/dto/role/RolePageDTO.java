package com.phoenix.nirvana.service.system.rpc.admin.domain.dto.role;

import com.phoenix.nirvana.common.dto.CommonPage;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@ApiModel("角色分页查询")
@Accessors(chain = true)
public class RolePageDTO extends CommonPage implements Serializable {

    @ApiModelProperty("模糊查询")
    private String keyboard;

}
