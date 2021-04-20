package com.zf1976.service.secutity;

import org.springframework.security.core.AuthenticationException;

/**
 * @author mac
 * Create by Ant on 2020/6/18 9:50 下午
 */
public class VerifyCodeException extends RuntimeException{

    public VerifyCodeException(String msg) {
        super(msg);
    }
}
