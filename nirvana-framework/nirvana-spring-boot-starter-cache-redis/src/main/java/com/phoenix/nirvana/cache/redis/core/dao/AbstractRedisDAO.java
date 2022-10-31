package com.phoenix.nirvana.cache.redis.core.dao;

import com.phoenix.nirvana.common.util.CollectionUtils;
import com.phoenix.nirvana.common.util.json.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.time.Duration;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * redis 对象增删改查操作抽象类
 *
 * @author xuyongkang
 */
public abstract class AbstractRedisDAO<T> {

    public Class<T> sourceClass;

    public Duration timeOut;

    public StringRedisTemplate stringRedisTemplate;

    public AbstractRedisDAO(Class<T> sourceClass, Duration timeOut, StringRedisTemplate stringRedisTemplate) {
        this.sourceClass = sourceClass;
        this.timeOut = timeOut;
        this.stringRedisTemplate = stringRedisTemplate;
    }

    public boolean hasKey(Object key) {
        return stringRedisTemplate.hasKey(formatKey(key));
    }

    public T get(Object key) {
        String redisKey = formatKey(key);
        return JsonUtils.parseObject(stringRedisTemplate.opsForValue().get(redisKey), sourceClass);
    }

    public void set(Object key, Object o) {
        String redisKey = formatKey(key);
        stringRedisTemplate.opsForValue().set(redisKey, JsonUtils.toJsonString(o), timeOut);
    }

    public void delete(Object key) {
        String redisKey = formatKey(key);
        stringRedisTemplate.delete(redisKey);
    }

    public void deleteList(Collection<Long> userIds) {
        List<String> redisKeys = CollectionUtils.convertList(userIds, this::formatKey);
        stringRedisTemplate.delete(redisKeys);
    }

    public abstract String formatKey(Object key);
}
