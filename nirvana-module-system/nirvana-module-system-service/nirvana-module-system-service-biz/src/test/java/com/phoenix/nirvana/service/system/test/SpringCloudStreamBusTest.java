package com.phoenix.nirvana.service.system.test;

import cn.hutool.core.thread.ThreadUtil;
import com.phoenix.nirvana.core.bus.AbstractBusProducer;
import com.phoenix.nirvana.service.system.SystemServiceApplication;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.bus.event.Destination;
import org.springframework.cloud.bus.event.RemoteApplicationEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.TimeUnit;

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



}
