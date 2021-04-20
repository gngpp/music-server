package com.zf1976.service.secutity.cache.support;

import com.zf1976.service.secutity.cache.VerifyCodeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * @author mac
 * Create by Ant on 2020/6/18 5:40 下午
 */
@Component
public class RedisVerifyCodeRepository implements VerifyCodeRepository {

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Override
    public Boolean save(String key, String value, long expire, TimeUnit timeUnit) {
        try {
            redisTemplate.opsForValue().set(wrapperKey(key),value,expire,timeUnit);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public String get(String key) {
        try {
            return redisTemplate.opsForValue().get(wrapperKey(key));
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public Boolean delete(String key) {
        try {
            return Boolean.TRUE.equals(redisTemplate.delete(wrapperKey(key)));
        } catch (Exception e) {
            return false;
        }
    }

    private String wrapperKey(String key) {
        return String.format("%s-%s", "vc", key);
    }
}
