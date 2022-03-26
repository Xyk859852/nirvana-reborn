package com.phoenix.nirvana.service.system.rpc.admin.domain.vo.department;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@ApiModel("平台端部门下拉列表数据查询对象")
@Accessors(chain = true)
public class DepartmentCascade implements Serializable {

    @ApiModelProperty("部门id")
    private Long id;

    @ApiModelProperty("部门名称")
    private String name;
}
