package com.phoenix.nirvana.admin.web.impl.service;

import com.phoenix.nirvana.admin.web.api.OnlineUserService;
import com.phoenix.nirvana.admin.web.api.bo.OnlineUserBO;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
@DubboService
public class OnlineUserServiceImpl implements OnlineUserService {

    @Autowired
    RedisTemplate redisTemplate;

    @Override
    public OnlineUserBO getOnlineUserByToken(String token) {
        if (redisTemplate.hasKey(token)) {
            return (OnlineUserBO) redisTemplate.opsForValue().get(token);
        }
        return null;
    }


}
