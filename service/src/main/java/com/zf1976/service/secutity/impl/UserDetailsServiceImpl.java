package com.zf1976.service.secutity.impl;

import com.zf1976.pojo.po.Consumer;
import com.zf1976.service.interfaces.ConsumerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @author mac
 * Create by Ant on 2020/6/15 4:58 下午
 */
@Service("userDetailsServiceImpl")
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    ConsumerService service;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        final Consumer consumer = service.findByUsername(username);
        return JwtUser.create(consumer);
    }
}
