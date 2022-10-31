package com.phoenix.nirvana.service.system.dal.redis.onlineuser;

import com.phoenix.nirvana.cache.redis.core.dao.AbstractRedisDAO;
import com.phoenix.nirvana.service.system.rpc.admin.domain.bo.OnlineUserBO;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import static com.phoenix.nirvana.service.system.dal.redis.RedisKeyConstants.ONLINE_USER;

@Component
public class OnlineUserRedisDAO extends AbstractRedisDAO<OnlineUserBO> {

    public OnlineUserRedisDAO(StringRedisTemplate stringRedisTemplate) {
        super(OnlineUserBO.class, ONLINE_USER.getTimeout(), stringRedisTemplate);
    }

    @Override
    public String formatKey(Object key) {
        return String.format(ONLINE_USER.getKeyTemplate(), key);
    }
}
