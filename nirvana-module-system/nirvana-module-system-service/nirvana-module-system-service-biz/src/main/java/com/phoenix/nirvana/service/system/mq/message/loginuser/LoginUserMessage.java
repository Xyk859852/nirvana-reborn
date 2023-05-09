package com.phoenix.nirvana.service.system.mq.message.loginuser;

import lombok.Data;
import org.springframework.cloud.bus.event.Destination;
import org.springframework.cloud.bus.event.RemoteApplicationEvent;

import java.util.List;

@Data
public class LoginUserMessage extends RemoteApplicationEvent {

    private List<Long> ids;

    public LoginUserMessage(List<Long> ids, Object source, String originService, Destination destination) {
        super(source, originService, destination);
        this.ids = ids;
    }
}
