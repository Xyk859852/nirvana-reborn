package com.phoenix;

import com.phoenix.nirvana.product.MallProductBizApplication;
import com.phoenix.nirvana.product.dal.mysql.dataobject.sku.ProductSkuInfoDO;
import com.phoenix.nirvana.product.service.sku.ProductSkuInfoService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.math.BigDecimal;

/**
 * Unit test for simple App.
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE, classes = MallProductBizApplication.class)
@ActiveProfiles("local") // 设置使用 application-local 配置文件
public class AppTest {

    @Resource
    ProductSkuInfoService productSkuInfoService;


    @Test
    public void test() {
        ProductSkuInfoDO productSkuInfoDO = new ProductSkuInfoDO();
//        productSkuInfoDO.setCreateTime(new Timestamp(new Date().getTime()));
        productSkuInfoDO.setSkuName("test");
        productSkuInfoDO.setOldPrice(BigDecimal.valueOf(5.21));

    }

}
