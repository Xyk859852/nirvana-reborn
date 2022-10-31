package com.phoenix.nirvana.service.system.dal.redis;


import com.phoenix.nirvana.cache.redis.core.RedisKeyDefine;
import com.phoenix.nirvana.service.system.rpc.auth.login.domain.vo.LoginUserInfoVO;

import java.time.Duration;

import static com.phoenix.nirvana.cache.redis.core.RedisKeyDefine.KeyTypeEnum.STRING;


/**
 * System Redis Key 枚举类
 *
 * @author xuyongkang
 */
public interface RedisKeyConstants {

    RedisKeyDefine LOGIN_USER_INFO = new RedisKeyDefine("登录用户详细缓存",
            "login_user_info:%s", // 参数为访问令牌 token
            STRING, LoginUserInfoVO.class, RedisKeyDefine.TimeoutTypeEnum.DYNAMIC);

    RedisKeyDefine ONLINE_USER = new RedisKeyDefine("根据token获取用户信息",
            "online_user:%s", // 参数为 state
            STRING, String.class, Duration.ofHours(24)); // 值为 state

}
