package com.zf1976.service.secutity.cache;

import java.util.concurrent.TimeUnit;

/**
 * @author mac
 * Create by Ant on 2020/6/18 5:43 下午
 */
public interface VerifyCodeRepository {

    /**
     * 保存验证码
     *
     * @param key key
     * @param value value
     * @param expire 过期时间戳
     * @param timeUnit 时间单位
     * @return boolean
     */
    Boolean save(String key, String value, long expire, TimeUnit timeUnit);

    /**
     * 获取存储验证码
     *
     * @param key key
     * @return 验证码
     */
    String get(String key);

    /**
     * 删除存储验证码
     *
     * @param key key
     * @return boolean
     */
    Boolean delete(String key);

}
