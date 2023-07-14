package com.phoenix;

import cn.hutool.core.io.FileUtil;
import com.phoenix.nirvana.file.core.client.FileClient;
import com.phoenix.nirvana.file.core.client.FileClientFactory;
import com.phoenix.nirvana.file.core.enums.FileStorageEnum;
import com.phoenix.nirvana.file.core.utils.FileTypeUtils;
import com.phoenix.nirvana.product.MallProductBizApplication;
import com.phoenix.nirvana.product.service.sku.ProductSkuInfoService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.io.File;

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

    @Autowired
    FileClientFactory clientFactory;

    @Test
    public void test() throws Exception {
        FileClient fileClient = clientFactory.getFileClient(FileStorageEnum.S3.getConfigId());
        File file = new File("/Users/xuyongkang/Desktop/1675391296538.jpg");
        byte[] content = FileUtil.readBytes(file);
        String mineType = FileTypeUtils.getMineType("1675391296538.jpg");
        String upload = fileClient.upload(content, "nirvana-reborn/mall-product-biz/1675391296538.jpg", "image/jpg");
        log.info("http upload : {}", upload);

    }


}
