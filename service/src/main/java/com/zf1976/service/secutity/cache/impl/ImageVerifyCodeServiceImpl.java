package com.zf1976.service.secutity.cache.impl;

import cn.hutool.core.util.RandomUtil;
import com.zf1976.service.secutity.cache.VerifyCodeRepository;
import com.zf1976.service.secutity.cache.VerifyCodeService;
import com.zf1976.service.secutity.cache.support.ImageVerifyCodeGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.OutputStream;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * @author mac
 * Create by Ant on 2020/6/18 5:38 下午
 */
@Service("imageVerifyCodeService")
public class ImageVerifyCodeServiceImpl implements VerifyCodeService {

    @Autowired
    private VerifyCodeRepository verifyCodeRepository;

    private final ImageVerifyCodeGenerator imageVerifyCodeGenerator = new ImageVerifyCodeGenerator(RandomUtil.BASE_CHAR_NUMBER,
                                                                                                   4, 50, 150);
    /**
     * 发送验证码
     *
     *
     * @param prefix 前缀
     * @param key      唯一标识
     * @param value    验证码
     * @param expire   有效时间
     * @param timeUnit 时间单位
     * @return 是否发送成功
     */
    @Override
    public Boolean sendVerifyCode(String prefix, String key, String value, long expire, TimeUnit timeUnit) {
        if(Objects.isNull(key) || Objects.isNull(value)) {
            return false;
        }
        return verifyCodeRepository.save(prefix + key,value,expire,timeUnit);
    }

    /**
     * 校验验证码
     *
     *
     * @param prefix 前缀
     * @param key  唯一标识
     * @param code 需要校验的验证码
     * @return 是否正确
     */
    @Override
    public Boolean verifyCode(String prefix, String key, String code) {
        final String actualCode = verifyCodeRepository.get(prefix + key);
        if(Objects.nonNull(actualCode) && Objects.nonNull(code)) {
            return actualCode.equalsIgnoreCase(code);
        }
        return false;
    }

    /**
     * 清理验证码
     *
     * @param prefix 前缀
     * @param key 唯一标识
     */
    @Override
    public void clearVerifyCode(String prefix, String key) {
        verifyCodeRepository.delete(prefix + key);
    }

    /**
     * 随机生成验证码
     *
     * @param outputStream 输出流, 图片验证码专用
     * @return 验证码具体内容
     */
    @Override
    public String generateCode(OutputStream outputStream) {
        return imageVerifyCodeGenerator.generate(outputStream);
    }
}
