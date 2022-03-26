package com.phoenix.nirvana.service.system.service.online;

import com.phoenix.nirvana.cache.redis.utils.RedisUtils;
import com.phoenix.nirvana.service.system.convert.SysUserConvert;
import com.phoenix.nirvana.service.system.dal.mysql.mapper.SysUserMapper;
import com.phoenix.nirvana.service.system.rpc.admin.domain.bo.OnlineUserBO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OnlineUserService {

    @Autowired
    RedisUtils redisUtils;

    @Autowired
    SysUserMapper userMapper;

    public OnlineUserBO getOnlineUserByToken(String token) {
        if (redisUtils.hasKey(token)) {
            return (OnlineUserBO) redisUtils.get(token);
        }
        return null;
    }

    public OnlineUserBO getOnlineUserByUserName(String userName) {
        return SysUserConvert.INTERFACE.convert(userMapper.selectOneByUserName(userName));
    }
}
