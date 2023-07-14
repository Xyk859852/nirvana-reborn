package com.phoenix.nirvana.service.system.test;

import com.phoenix.nirvana.service.system.SystemServiceApplication;
import com.phoenix.nirvana.service.system.mq.producer.loginuser.LoginUserProducer;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;

/**
 * @author xuyongkang
 * @version 1.0
 * @description: nirvana-reborn 事件
 * @date 2022/7/23 10:05 AM
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE, classes = SystemServiceApplication.class)
public class SpringCloudStreamBusTest {

    @Autowired
    LoginUserProducer loginUserProducer;

    @Test
    public void test() {
        loginUserProducer.sendCleanLoginUserTokenMessage(Arrays.asList(1l, 2l, 3l));
    }

}
