package com.phoenix.nirvana.product.rpc.sku.domain.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 * 根据 skuCode 查询对应商品
 *
 * @Author: xuyongkang
 * @Date 2023/7/17 11:28
 */
@Data
@Accessors(chain = true)
public class ListProductSkuDTO implements Serializable {

    /**
     * 商品skuCode 集合
     */
    private List<String> skuCodes;


    /**
     * 商家id
     */
    private Long shopId;
}
