package com.phoenix.nirvana.inventory.rpc.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 取消订单(全部条目退)和手动售后(单笔条目退)共用的释放商品库存入参
 *
 * @author xuyongkang
 * @version 1.0
 */
@Data
@Accessors(chain = true)
public class ReleaseProductStockDTO extends DeductProductStockDTO implements Serializable {

    private static final long serialVersionUID = 8229493558996271243L;

    /**
     * skuCode 用于 手动售后
     */
    private String skuCode;


}