package com.phoenix.nirvana.product.service.attr;

import com.phoenix.nirvana.product.rpc.attr.domain.dto.ProductAttrValuesCreateDTO;
import com.phoenix.nirvana.product.rpc.attr.domain.dto.ProductAttrValuesUpdateDTO;
import com.phoenix.nirvana.product.rpc.attr.domain.vo.ProductAttrValuesVO;

/**
 * <p>
 * 商品属性兼容属性值 服务类
 * </p>
 *
 * @author xuyongkang
 * @since 2023-05-10
 */
public interface ProductAttrService {


    /**
     * 根据商品属性id 查询对应的 key - values
     *
     * @param attrId 属性key id
     * @return
     */
    ProductAttrValuesVO getAttrDetailById(Long attrId);


    /**
     * 创建商品属性名称与属性集合
     *
     * @param productAttrCreate
     * @return
     */
    Boolean createAttrKeyValues(ProductAttrValuesCreateDTO productAttrCreate);

    /**
     * 修改商品属性名称与属性集合
     *
     * @param attrValuesUpdate
     * @return
     */
    Boolean updateAttrKeyValues(ProductAttrValuesUpdateDTO attrValuesUpdate);

    /**
     * 根据商品属性key id 删除对应 key - values
     *
     * @param attrId
     * @return
     */
    Boolean delete(Long attrId);


}
