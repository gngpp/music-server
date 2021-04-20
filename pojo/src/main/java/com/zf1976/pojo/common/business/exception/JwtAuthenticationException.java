package com.zf1976.pojo.common.business.exception;

import org.springframework.security.core.AuthenticationException;

/**
 * @author mac
 * Create by Ant on 2020/6/17 9:13 下午
 */
public class JwtAuthenticationException extends AuthenticationException {

    public JwtAuthenticationException(String msg) {
        super(msg);
    }
}
