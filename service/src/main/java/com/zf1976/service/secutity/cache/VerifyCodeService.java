package com.zf1976.service.secutity.cache;

import java.io.OutputStream;
import java.util.concurrent.TimeUnit;

/**
 * @author mac
 * Create by Ant on 2020/6/18 5:37 下午
 */
public interface VerifyCodeService {

    /**
     * 发送验证码
     *
     *
     * @param prefix 前缀
     * @param key 唯一标识
     * @param value 需要发送的验证码
     * @param expire 有效时间
     * @param timeUnit 时间单位
     * @return 是否发送成功
     */
    Boolean sendVerifyCode(String prefix, String key, String value, long expire, TimeUnit timeUnit);

    /**
     * 校验验证码
     *
     *
     * @param prefix 前缀
     * @param key 唯一标识
     * @param code 需要校验的验证码
     * @return 是否正确
     */
    Boolean verifyCode(String prefix, String key, String code);

    /**
     * 清理验证码
     *
     * @param prefix 前缀
     * @param key 唯一标识
     */
    void clearVerifyCode(String prefix, String key);

    /**
     * 随机生成验证码
     *
     * @param outputStream 输出流, 图片验证码专用
     * @return 验证码具体内容
     */
    String generateCode(OutputStream outputStream);

}
