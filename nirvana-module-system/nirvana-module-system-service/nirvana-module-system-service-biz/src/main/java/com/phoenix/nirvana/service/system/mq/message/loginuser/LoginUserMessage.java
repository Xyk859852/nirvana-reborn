package com.phoenix.nirvana.service.system.mq.message.loginuser;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.cloud.bus.event.Destination;
import org.springframework.cloud.bus.event.RemoteApplicationEvent;

import java.util.List;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class LoginUserMessage extends RemoteApplicationEvent {


    private List<Long> ids;

    public LoginUserMessage(List<Long> ids, Object source, String originService, Destination destination) {
        super(source, originService, destination);
        this.ids = ids;
    }
}
