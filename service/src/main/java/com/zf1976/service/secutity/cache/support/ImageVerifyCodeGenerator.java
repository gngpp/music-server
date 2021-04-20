package com.zf1976.service.secutity.cache.support;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.LineCaptcha;
import cn.hutool.captcha.generator.RandomGenerator;

import java.io.OutputStream;

/**
 * @author mac
 * Create by Ant on 2020/6/18 5:40 下午
 */
public class ImageVerifyCodeGenerator {

    private LineCaptcha lineCaptcha;

    private ImageVerifyCodeGenerator() {}

    public ImageVerifyCodeGenerator(String chars,
                                    int length,
                                    int height,
                                    int width) {

        RandomGenerator randomGenerator = new RandomGenerator(chars, length);
        lineCaptcha = CaptchaUtil.createLineCaptcha(width, height);
        lineCaptcha.setGenerator(randomGenerator);
    }

    public String generate(OutputStream outputStream) {
        lineCaptcha.createCode();
        final String result = lineCaptcha.getCode();
        lineCaptcha.write(outputStream);
        return result;
    }
}
