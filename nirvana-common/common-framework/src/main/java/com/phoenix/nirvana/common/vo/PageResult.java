package com.phoenix.nirvana.common.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Data
@Accessors(chain = true)
@ApiModel("列表分页返回对象")
public class PageResult<T> implements Serializable {

    @ApiModelProperty("分页大小")
    private Integer pageSize = 0;

    @ApiModelProperty("分页页码")
    private Integer pageNo = 0;

    @ApiModelProperty("总页码")
    private Long totalPage = 0l;

    @ApiModelProperty("数据总数")
    private Long totalCount = 0l;

    @ApiModelProperty("分页数据")
    private List<T> data = Collections.EMPTY_LIST;

    public PageResult() {
    }

    public PageResult(List<T> data, Long total) {
        this.data = data;
        this.totalCount = total;
    }

    public PageResult(List<T> data, Long total, Integer pageNo, Integer pageSize, Long totalPage) {
        this.data = data;
        this.totalCount = total;
        this.pageNo = pageNo;
        this.pageSize = pageSize;
        this.totalPage = totalPage;
    }

    public PageResult(Long total) {
        this.data = new ArrayList<>();
        this.totalCount = total;
    }


    public static <T> PageResult<T> empty(Long total) {
        return new PageResult<>(total);
    }

    /**
     * 空集合
     *
     * @return
     */
    public static PageResult empty() {
        return new PageResult();
    }
}
