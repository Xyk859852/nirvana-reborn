package com.phoenix.nirvana.service.system.mq.producer.loginuser;

import com.phoenix.nirvana.core.bus.AbstractBusProducer;
import com.phoenix.nirvana.service.system.mq.message.loginuser.LoginUserMessage;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class LoginUserProducer extends AbstractBusProducer {

    /**
     * 发送 {@link LoginUserMessage} 消息
     */
    public void sendCleanLoginUserTokenMessage(List<Long> ids) {
        publishEvent(new LoginUserMessage(ids,this, getBusId(), selfDestinationService()));
    }

}
