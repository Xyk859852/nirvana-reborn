package com.phoenix.nirvana.inventory.dal.mysql.mapper.stock;

import com.phoenix.nirvana.inventory.dal.mysql.dataobject.stock.InventoryProductStockDO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.phoenix.nirvana.mybatis.core.query.LambdaQueryWrapperX;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

/**
 * <p>
 * 库存中心的商品库存表 Mapper 接口
 * </p>
 *
 * @author xuyongkang
 * @since 2023-07-14
 */
@Mapper
public interface InventoryProductStockMapper extends BaseMapper<InventoryProductStockDO> {


    /**
     * 扣减商品库存
     *
     * @param skuCode
     * @param saleQuantity
     * @return
     */
    @Update(" update inventory_product_stock " +
            "        set sale_stock_quantity = sale_stock_quantity - #{saleQuantity} " +
            "        , update_time = now() " +
            "        where sku_code = #{skuCode} " +
            "          and sale_stock_quantity >= #{saleQuantity}")
    int deductProductStock(@Param("skuCode") String skuCode, @Param("saleQuantity") Integer saleQuantity);

    /**
     * 释放商品库存
     *
     * @param skuCode
     * @param saleQuantity
     * @return
     */
    @Update(" update inventory_product_stock " +
            "        set sale_stock_quantity  = sale_stock_quantity + #{saleQuantity}, " +
            "            saled_stock_quantity = saled_stock_quantity - #{saleQuantity} " +
            "        where sku_code = #{skuCode} " +
            "          and saled_stock_quantity >= #{saleQuantity}")
    int releaseProductStock(@Param("skuCode") String skuCode, @Param("saleQuantity") Integer saleQuantity);

    default InventoryProductStockDO getBySkuCode(String skuCode) {
        return selectOne(new LambdaQueryWrapperX<InventoryProductStockDO>()
                .eqIfPresent(InventoryProductStockDO::getSkuCode, skuCode));
    }
}
