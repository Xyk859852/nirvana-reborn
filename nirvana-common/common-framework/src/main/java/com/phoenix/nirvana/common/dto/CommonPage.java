package com.phoenix.nirvana.common.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@Accessors
@ApiModel("分页数据")
public abstract class CommonPage implements Serializable {

    @ApiModelProperty("每页显示条数")
    @NotNull(message = "查询每页显示数据不能为null")
    private Integer size = 10;

    @ApiModelProperty("当前页")
    @NotNull(message = "查询当前页不能为null")
    private Integer current = 1;

}
