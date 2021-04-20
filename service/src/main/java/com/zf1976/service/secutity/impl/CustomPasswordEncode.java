package com.zf1976.service.secutity.impl;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.DigestUtils;

import java.util.Objects;

/**
 * @author mac
 * Create by Ant on 2020/6/19 9:24 上午
 */
public class CustomPasswordEncode implements PasswordEncoder {
    @Override
    public String encode(CharSequence charSequence) {
        return DigestUtils.md5DigestAsHex(charSequence.toString().getBytes());
    }

    @Override
    public boolean matches(CharSequence charSequence, String s) {
        final String digest = DigestUtils.md5DigestAsHex(charSequence.toString().getBytes());
        return Objects.equals(digest, s);
    }

}
