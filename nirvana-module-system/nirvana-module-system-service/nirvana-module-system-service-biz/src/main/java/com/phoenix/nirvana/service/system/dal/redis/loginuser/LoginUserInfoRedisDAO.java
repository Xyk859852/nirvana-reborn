package com.phoenix.nirvana.service.system.dal.redis.loginuser;

import com.phoenix.nirvana.common.util.CollectionUtils;
import com.phoenix.nirvana.common.util.json.JsonUtils;
import com.phoenix.nirvana.service.system.rpc.auth.login.domain.vo.LoginUserInfoVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static com.phoenix.nirvana.service.system.dal.redis.RedisKeyConstants.LOGIN_USER_INFO;

@Component
public class LoginUserInfoRedisDAO {

    @Autowired
    StringRedisTemplate stringRedisTemplate;


    public boolean hasKey(Long userId) {
        return stringRedisTemplate.hasKey(String.valueOf(userId));
    }

    public LoginUserInfoVO get(Long userId) {
        String redisKey = formatKey(userId);
        return JsonUtils.parseObject(stringRedisTemplate.opsForValue().get(redisKey), LoginUserInfoVO.class);
    }

    public void set(LoginUserInfoVO userInfo) {
        String redisKey = formatKey(userInfo.getId());
        // 清理多余字段，避免缓存
        stringRedisTemplate.opsForValue().set(redisKey, JsonUtils.toJsonString(userInfo),
                System.currentTimeMillis(), TimeUnit.MILLISECONDS);
    }

    public void delete(Long userId) {
        String redisKey = formatKey(userId);
        stringRedisTemplate.delete(redisKey);
    }

    public void deleteList(Collection<Long> userIds) {
        List<String> redisKeys = CollectionUtils.convertList(userIds, this::formatKey);
        stringRedisTemplate.delete(redisKeys);
    }

    private String formatKey(Long userId) {
        return String.format(LOGIN_USER_INFO.getKeyTemplate(), userId);
    }
}
