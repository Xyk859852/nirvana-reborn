package com.phoenix.nirvana.service.system.rpc.role.domain.vo;

import com.phoenix.nirvana.service.system.rpc.role.domain.base.RoleBase;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@ApiModel("角色列表返回")
@Accessors(chain = true)
public class RolePageItemVO extends RoleBase implements Serializable {

    @ApiModelProperty("id")
    private Long id;

    @ApiModelProperty("更新时间")
    private Date updateTime;

}
