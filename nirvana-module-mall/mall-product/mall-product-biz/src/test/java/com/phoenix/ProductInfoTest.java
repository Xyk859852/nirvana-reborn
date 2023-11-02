package com.phoenix;

import com.phoenix.nirvana.product.MallProductBizApplication;
import com.phoenix.nirvana.product.manager.spu.ProductSpuInfoManager;
import com.phoenix.nirvana.product.rpc.spu.domain.dto.ProductInfoCreateDTO;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 商品信息测试
 *
 * @Author: xuyongkang
 * @Date 2023/8/3 15:33
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE, classes = MallProductBizApplication.class)
@ActiveProfiles("local") // 设置使用 application-local 配置文件
public class ProductInfoTest {


    @Autowired
    ProductSpuInfoManager productSpuInfoManager;

    @Test
    public void createProductTest() {
        ProductInfoCreateDTO productInfoCreate = new ProductInfoCreateDTO();
        productInfoCreate.setProductCode("1000001");
        productInfoCreate.setProductHot("1");
        productInfoCreate.setProductName("2024款 macBookPro");
        productInfoCreate.setProductNew("1");
        productInfoCreate.setProductDesc("运行速度很快，非常好");
        productInfoCreate.setProductLabel("mac，电脑，苹果");
        productInfoCreate.setProductUnit("个");
        productInfoCreate.setProductOnsale("0");
        productInfoCreate.setBrandId(1l);
        productInfoCreate.setCatalogId(7l);
        productInfoCreate.setHasSku("1");
        productInfoCreate.setNewPrice(BigDecimal.valueOf(20000).movePointRight(3).intValue());
        productInfoCreate.setOldPrice(BigDecimal.valueOf(18000).movePointRight(3).intValue());
        productInfoCreate.setPackFee(5000);
        productInfoCreate.setSaleStartUnit(1);
        productInfoCreate.setSaleStartTime(LocalDateTime.now());
        productInfoCreate.setSaleEndTime(LocalDateTime.now());
        productInfoCreate.setShopId(7l);
        productInfoCreate.setMustChoose("1");
        productInfoCreate.setProductDefaultImg("test");
        List<ProductInfoCreateDTO.ProductAttr> productAttrList = new ArrayList<>();
        productAttrList.add(new ProductInfoCreateDTO.ProductAttr()
                .setAttrName("颜色")
                .setStatus(0)
                .setStock(100)
                .setWeight(2000)
                .setOldPrice(BigDecimal.valueOf(18000).movePointRight(3).intValue())
                .setNewPrice(BigDecimal.valueOf(20000).movePointRight(3).intValue())
                .setAttrValues(Arrays.asList("银色", "灰色"))
        );
        productAttrList.add(new ProductInfoCreateDTO.ProductAttr()
                .setAttrName("内存")
                .setStatus(0)
                .setStock(100)
                .setWeight(2000)
                .setOldPrice(BigDecimal.valueOf(18000).movePointRight(3).intValue())
                .setNewPrice(BigDecimal.valueOf(20000).movePointRight(3).intValue())
                .setAttrValues(Arrays.asList("512G", "1TB", "2TB"))
        );
        productInfoCreate.setProductAttrList(productAttrList);
        productSpuInfoManager.createProduct(productInfoCreate);
    }
}
