package com.phoenix.nirvana.cache.redis.core;

import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * redis common lock
 *
 * @author xuyongkang
 */
@Slf4j
public class RedisLock {

    RedissonClient redissonClient;

    public RedisLock(RedissonClient redissonClient) {
        this.redissonClient = redissonClient;
    }

    /**
     * 互斥锁，seconds秒后自动失效
     *
     * @param key
     * @param seconds
     */
    public boolean tryLock(String key, int seconds) {
        RLock rLock = redissonClient.getLock(key);
        try {
            return rLock.tryLock(seconds, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            return false;
        }
    }

    /**
     * 互斥锁，自动续期
     *
     * @param key
     */
    public void lock(String key) {
        RLock rLock = redissonClient.getLock(key);
        rLock.lock();
    }


    /**
     * 互斥锁，自动续期
     *
     * @param key
     */
    public boolean tryLock(String key) {
        RLock rLock = redissonClient.getLock(key);
        boolean locked = rLock.tryLock();
        log.info("tryLock: key={},locked={}", key, locked);
        return locked;
    }

    /**
     * 手动释放锁
     *
     * @param key
     */
    public void unlock(String key) {
        RLock rLock = redissonClient.getLock(key);
        if (rLock.isLocked()) {
            rLock.unlock();
            log.info("unlock: key={}", key);
        }
    }

    /**
     * 联锁 加锁
     */
    public boolean multiLock(List<String> redisKeyList) {
        try {
            RLock multiLock = getMultiLock(redisKeyList);
            return multiLock.tryLock();
        } catch (Exception e) {
            throw new UnsupportedOperationException();
        }
    }

    private RLock getMultiLock(List<String> redisKeyList) {
        RLock[] locks = new RLock[redisKeyList.size()];
        for (int i = 0; i < redisKeyList.size(); i++) {
            RLock lock = redissonClient.getLock(redisKeyList.get(i));
            locks[i] = lock;
        }
        return redissonClient.getMultiLock(locks);
    }

    /**
     * 联锁 解锁
     */
    public void unMultiLock(List<String> redisKeyList) {
        RLock multiLock = getMultiLock(redisKeyList);
        multiLock.unlock();
    }

}
