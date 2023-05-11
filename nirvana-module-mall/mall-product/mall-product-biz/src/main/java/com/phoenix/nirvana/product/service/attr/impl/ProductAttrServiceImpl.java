package com.phoenix.nirvana.product.service.attr.impl;

import com.phoenix.nirvana.common.exception.util.ServiceExceptionUtil;
import com.phoenix.nirvana.product.convert.attr.ProductAttrConvert;
import com.phoenix.nirvana.product.dal.mysql.dataobject.attr.ProductAttrDO;
import com.phoenix.nirvana.product.dal.mysql.dataobject.attr.ProductAttrValueDO;
import com.phoenix.nirvana.product.dal.mysql.mapper.attr.ProductAttrMapper;
import com.phoenix.nirvana.product.dal.mysql.mapper.attr.ProductAttrValueMapper;
import com.phoenix.nirvana.product.rpc.attr.domain.dto.ProductAttrValuesCreateDTO;
import com.phoenix.nirvana.product.rpc.attr.domain.dto.ProductAttrValuesUpdateDTO;
import com.phoenix.nirvana.product.rpc.attr.domain.vo.ProductAttrValuesVO;
import com.phoenix.nirvana.product.service.attr.ProductAttrService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import static com.phoenix.nirvana.product.enums.ProductErrorCodeConstants.*;

/**
 * <p>
 * 商品属性 服务实现类
 * </p>
 *
 * @author xuyongkang
 * @since 2023-05-10
 */
@Service
public class ProductAttrServiceImpl implements ProductAttrService {

    @Autowired
    ProductAttrMapper productAttrMapper;

    @Autowired
    ProductAttrValueMapper productAttrValueMapper;


    @Override
    public ProductAttrValuesVO getAttrDetailById(Long attrId) {
        ProductAttrDO productAttr = productAttrMapper.selectById(attrId);
        if (productAttr == null) {
            throw ServiceExceptionUtil.exception(PRODUCT_ATTR_KEY_NOT_EXIST);
        }
        ProductAttrValuesVO attrValues = ProductAttrConvert.INTERFACE.convert(productAttr);
        List<ProductAttrValueDO> values = productAttrValueMapper.selectListByAttrId(productAttr.getAttrId());
        attrValues.setAttrValues(values.stream().map(ProductAttrValueDO::getAttrValueName).collect(Collectors.toList()));
        return attrValues;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean createAttrKeyValues(ProductAttrValuesCreateDTO productAttrCreate) {
        existsAttrName(productAttrCreate.getProductId(), productAttrCreate.getAttrName(), null);
        ProductAttrDO productAttr = ProductAttrConvert.INTERFACE.convert(productAttrCreate);
        productAttrMapper.insert(productAttr);
        saveAttrValues(productAttrCreate.getAttrValues(), productAttr.getAttrId());
        return true;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean updateAttrKeyValues(ProductAttrValuesUpdateDTO attrValuesUpdate) {
        existsAttrName(attrValuesUpdate.getProductId(), attrValuesUpdate.getAttrName(), attrValuesUpdate.getAttrId());
        ProductAttrDO productAttr = ProductAttrConvert.INTERFACE.convert(attrValuesUpdate);
        productAttrMapper.updateById(productAttr);
        // 先删除在添加
        productAttrValueMapper.deleteByAttrId(attrValuesUpdate.getAttrId());
        saveAttrValues(attrValuesUpdate.getAttrValues(), productAttr.getAttrId());
        return null;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean delete(Long attrId) {
        productAttrMapper.deleteById(attrId);
        productAttrValueMapper.deleteByAttrId(attrId);
        return true;
    }

    /**
     * 判断商品属性key值是否存在
     *
     * @param productId 商品id
     * @param attrName  key名称
     * @param attrId    key名称主键：新增为null，修改才有传递值
     */
    private void existsAttrName(Long productId, String attrName, Long attrId) {
        if (productAttrMapper.existsByProductIdAttrKey(productId, attrName, attrId)) {
            throw ServiceExceptionUtil.exception(PRODUCT_ATTR_KEY_EXISTS);
        }
    }

    /**
     * 商品属性值批量增加
     *
     * @param attrValues 商品属性集合
     * @param attrId     商品属性key值id
     */
    private void saveAttrValues(List<String> attrValues, Long attrId) {
        for (int i = 0; i < attrValues.size(); i++) {
            String value = attrValues.get(i);
            if (productAttrValueMapper.existsByAttrIdValueName(attrId, value)) {
                throw ServiceExceptionUtil.exception(PRODUCT_ATTR_VALUE_EXISTS);
            }
            ProductAttrValueDO attrValue = new ProductAttrValueDO();
            attrValue.setAttrValueName(value);
            attrValue.setAttrId(attrId);
            attrValue.setAttrValueSort(attrValues.indexOf(value));
            productAttrValueMapper.insert(attrValue);
        }
    }
}

