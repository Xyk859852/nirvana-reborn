package com.phoenix.nirvana.product.service.catalog.impl;

import com.phoenix.nirvana.common.exception.util.ServiceExceptionUtil;
import com.phoenix.nirvana.common.vo.PageResult;
import com.phoenix.nirvana.product.convert.catalog.ProductCatalogConvert;
import com.phoenix.nirvana.product.dal.mysql.dataobject.catalog.ProductCatalogDO;
import com.phoenix.nirvana.product.dal.mysql.mapper.catalog.ProductCatalogMapper;
import com.phoenix.nirvana.product.enums.category.ProductCategoryIdEnum;
import com.phoenix.nirvana.product.rpc.catalog.domain.dto.ProductCatalogCreateDTO;
import com.phoenix.nirvana.product.rpc.catalog.domain.dto.ProductCatalogPageDTO;
import com.phoenix.nirvana.product.rpc.catalog.domain.dto.ProductCatalogUpdateDTO;
import com.phoenix.nirvana.product.rpc.catalog.domain.vo.ProductCatalogPageListVO;
import com.phoenix.nirvana.product.rpc.catalog.domain.vo.ProductCatalogTreeVO;
import com.phoenix.nirvana.product.service.catalog.ProductCatalogService;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.phoenix.nirvana.product.enums.ProductErrorCodeConstants.PRODUCT_CATEGORY_DELETE_ONLY_NO_CHILD;
import static com.phoenix.nirvana.product.enums.ProductErrorCodeConstants.PRODUCT_CATEGORY_PARENT_NOT_EXISTS;

/**
 * <p>
 * 商品三级分类 服务实现类
 * </p>
 *
 * @author xuyongkang
 * @since 2023-05-10
 */
@Service
@DubboService
public class ProductCatalogServiceImpl implements ProductCatalogService {

    @Autowired
    ProductCatalogMapper productCatalogMapper;


    @Override
    public PageResult<ProductCatalogPageListVO> pageList(ProductCatalogPageDTO productCatalogPage) {
        return ProductCatalogConvert.INTERFACE.convert(productCatalogMapper.pageList(productCatalogPage));
    }


    @Override
    public List<ProductCatalogTreeVO> treeList(Long pid) {
        List<ProductCatalogDO> productCatalogs = productCatalogMapper.selectListByPid(pid);
        return recursionTree(pid != null ? pid : ProductCategoryIdEnum.ROOT.getId(), productCatalogs);
    }

    @Override
    public Long create(ProductCatalogCreateDTO productCatalogCreate) {
        validParent(productCatalogCreate.getParentId());
        ProductCatalogDO catalog = ProductCatalogConvert.INTERFACE.convert(productCatalogCreate);
        productCatalogMapper.insert(catalog);
        return catalog.getCatalogId();
    }

    @Override
    public Boolean update(ProductCatalogUpdateDTO productCatalogUpdate) {
        validParent(productCatalogUpdate.getParentId());
        ProductCatalogDO catalog = ProductCatalogConvert.INTERFACE.convert(productCatalogUpdate);
        productCatalogMapper.updateById(catalog);
        return true;
    }

    @Override
    public Boolean delete(Long catalogId) {
        hasChild(catalogId);
        productCatalogMapper.deleteById(catalogId);
        return true;
    }

    /**
     * 分类tree结构数据
     *
     * @param parentId
     * @param productCatalog
     * @return
     */
    private static List<ProductCatalogTreeVO> recursionTree(Long parentId, List<ProductCatalogDO> productCatalog) {
        List<ProductCatalogTreeVO> list = new ArrayList<>();
        Optional.ofNullable(productCatalog)
                .ifPresent(categories ->
                        categories.stream().filter(catalog ->
                                        catalog.getParentId().equals(parentId))
                                .forEach(catalog -> {
                                    ProductCatalogTreeVO categoryVO = ProductCatalogConvert.INTERFACE.convert(catalog);
                                    List<ProductCatalogTreeVO> children = recursionTree(catalog.getCatalogId(), productCatalog);
                                    categoryVO.setChildren(children);
                                    list.add(categoryVO);
                                }));
        return list;
    }

    /**
     * 校验父分类
     *
     * @param pid
     */
    private void validParent(Long pid) {
        if (!ProductCategoryIdEnum.ROOT.getId().equals(pid)) {
            ProductCatalogDO parentCatalog = productCatalogMapper.selectById(pid);
            if (parentCatalog == null) {
                throw ServiceExceptionUtil.exception(PRODUCT_CATEGORY_PARENT_NOT_EXISTS);
            }
        }
    }


    /**
     * 判断是否有子分类
     *
     * @param id
     */
    private void hasChild(Long id) {
        if (productCatalogMapper.hasChild(id)) {
            throw ServiceExceptionUtil.exception(PRODUCT_CATEGORY_DELETE_ONLY_NO_CHILD);
        }
    }

}
