package com.zf1976.service.secutity.impl;

import com.zf1976.pojo.po.Consumer;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

/**
 * @author mac
 * Create by Ant on 2020/6/16 6:14 下午
 */
public class JwtUser implements UserDetails {

    private final String username;
    private final String password;
    private final Collection<? extends GrantedAuthority> authorities;

    private JwtUser(Consumer consumer) {
        Integer id = consumer.getId();
        username = consumer.getUsername();
        password = consumer.getPassword();
        authorities = Collections.singleton(new SimpleGrantedAuthority(consumer.getRole()));
    }


    public static JwtUser create(Consumer consumer){
        return new JwtUser(consumer);
    }

    /**
     * 存角色
     *
     * @return 角色集合
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}


