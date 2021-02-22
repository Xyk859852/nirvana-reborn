package com.phoenix.nirvana.common.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

@Data
@Accessors(chain = true)
@ApiModel("列表分页返回对象")
public class PageResult<T> implements Serializable {

    @ApiModelProperty("分页大小")
    private Integer pageSize;

    @ApiModelProperty("分页页码")
    private Integer pageNo;

    @ApiModelProperty("总页码")
    private Long totalPage;

    @ApiModelProperty("数据总数")
    private Long totalCount;

    @ApiModelProperty("分页数据")
    private List<T> list;

    /**
     * 空集合
     * @return
     */
    public static PageResult empty(){
        return new PageResult()
                .setList(Collections.EMPTY_LIST)
                .setTotalPage(0L)
                .setPageNo(0)
                .setTotalCount(0L);
    }
}
