package com.phoenix.nirvana.service.system.mq.consumer.loginuser;

import com.phoenix.nirvana.service.system.mq.message.loginuser.LoginUserMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 * @Description:
 * @Author: xuyongkang
 * @Date 2023/5/14 17:41
 */
@Component
@Slf4j
public class LoginUserConsumer {

    @EventListener
    public void execute(LoginUserMessage message) {
        log.info("rocket mq content:{}", message);
    }
}
