package com.phoenix.nirvana.product.dal.mysql.mapper.attr;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.phoenix.nirvana.mybatis.core.query.LambdaQueryWrapperX;
import com.phoenix.nirvana.product.dal.mysql.dataobject.attr.ProductAttrValueDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 * 商品属性 Mapper 接口
 * </p>
 *
 * @author xuyongkang
 * @since 2023-05-10
 */
@Mapper
public interface ProductAttrValueMapper extends BaseMapper<ProductAttrValueDO> {


    default List<ProductAttrValueDO> selectListByAttrId(Long attrId) {
        return this.selectList(new LambdaQueryWrapperX<ProductAttrValueDO>()
                .eq(ProductAttrValueDO::getAttrId, attrId)
                .orderByAsc(ProductAttrValueDO::getAttrValueSort)
        );
    }

    default Boolean existsByAttrIdValueName(Long attrId, String valueName) {
        return this.exists(new LambdaQueryWrapperX<ProductAttrValueDO>()
                .eq(ProductAttrValueDO::getAttrId, attrId)
                .eq(ProductAttrValueDO::getAttrValueName, valueName)
        );
    }

    default int deleteByAttrId(Long attrId) {
        return this.delete(new LambdaQueryWrapperX<ProductAttrValueDO>()
                .eq(ProductAttrValueDO::getAttrId, attrId)
        );
    }
}
