package com.phoenix.nirvana.service.system.service.online;

import com.phoenix.nirvana.cache.redis.core.RedisCache;
import com.phoenix.nirvana.service.system.dal.mysql.mapper.user.SysUserMapper;
import com.phoenix.nirvana.service.system.dal.redis.loginuser.LoginUserInfoRedisDAO;
import com.phoenix.nirvana.service.system.dal.redis.onlineuser.OnlineUserRedisDAO;
import com.phoenix.nirvana.service.system.rpc.admin.domain.bo.OnlineUserBO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OnlineUserService {

    @Autowired
    RedisCache redisCache;

    @Autowired
    SysUserMapper userMapper;

    @Autowired
    LoginUserInfoRedisDAO loginUserInfoRedisDao;

    @Autowired
    OnlineUserRedisDAO onlineUserRedisDAO;

    public OnlineUserBO getOnlineUserByToken(String token) {
        if (onlineUserRedisDAO.hasKey(token)) {
            return onlineUserRedisDAO.get(token);
        }
        return null;
    }

}
