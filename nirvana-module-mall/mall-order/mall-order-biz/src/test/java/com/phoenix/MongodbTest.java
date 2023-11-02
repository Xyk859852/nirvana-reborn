package com.phoenix;

import cn.hutool.core.lang.UUID;
import com.phoenix.nirvana.order.MallOrderApplication;
import com.phoenix.nirvana.order.dal.mongodb.OrderOperateLogDO;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE, classes = MallOrderApplication.class)
public class MongodbTest {

    @Autowired
    MongoTemplate mongoTemplate;

    @Test
    public void test() {
        OrderOperateLogDO orderOperateLog = new OrderOperateLogDO();
        orderOperateLog.setCurrentStatus(0);
        orderOperateLog.setGmtModified(new Date());
        orderOperateLog.setGmtCreate(new Date());
        orderOperateLog.setRemark("测试订单");
        orderOperateLog.setOrderNo(UUID.fastUUID().toString(true));
        orderOperateLog.setPreStatus(1);
        orderOperateLog.setRemark("lalalala");
        orderOperateLog.setOperateType(2);
        mongoTemplate.insert(orderOperateLog);

        // 创建时间倒序查询
        Sort orderBy = Sort.by(Sort.Direction.DESC, "gmtCreate");
        Query query = new Query();
        query.with(orderBy);
        List<OrderOperateLogDO> orderOperateLogList = mongoTemplate.find(query, OrderOperateLogDO.class);
        log.info("orderOperateLogList:{}", orderOperateLogList);
    }
}
