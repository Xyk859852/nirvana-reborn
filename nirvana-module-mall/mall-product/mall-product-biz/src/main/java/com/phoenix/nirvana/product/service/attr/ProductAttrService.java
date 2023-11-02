package com.phoenix.nirvana.product.service.attr;

import com.phoenix.nirvana.product.rpc.attr.domain.dto.ProductAttrValuesCreateDTO;
import com.phoenix.nirvana.product.rpc.attr.domain.dto.ProductAttrValuesUpdateDTO;
import com.phoenix.nirvana.product.rpc.attr.domain.vo.ProductAttrVO;

import java.util.List;

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
    ProductAttrVO getAttrDetailById(Long attrId);


    /**
     * 根据商品属性id 查询对应的 key - values
     *
     * @param attrId 属性key id
     * @return
     */
    ProductAttrVO getAttrDetailByProductIdAndAttrName(Long productId, String attrName);



    /**
     * 判断商品属性是否存在
     *
     * @param productId
     * @param attrName
     * @param attrId
     */
    Boolean existsAttrName(Long productId, String attrName, Long attrId);

    /**
     * 判断属性值是否存在
     *
     * @param value
     * @param attrId
     */
    Boolean existsAttrValue(String value, Long attrId);


    /**
     * 创建商品属性名称与属性集合
     *
     * @param productAttrCreate
     * @return
     */
    ProductAttrVO createAttrKeyValues(ProductAttrValuesCreateDTO productAttrCreate);

    /**
     * 插入对应属性key的value
     *
     * @param attrValues
     * @param attrId
     * @return
     */
    void createAttrValues(List<String> attrValues, Long attrId);

    /**
     * 修改商品属性名称与属性集合
     *
     * @param attrValuesUpdate
     * @return
     */
    ProductAttrVO updateAttrKeyValues(ProductAttrValuesUpdateDTO attrValuesUpdate);

    /**
     * 根据商品属性key id 删除对应 key - values
     *
     * @param attrId
     * @return
     */
    Boolean delete(Long attrId);


}
