package com.zf1976.pojo.common;

import lombok.Builder;
import lombok.Data;

/**
 * @author mac
 * Create by Ant on 2020/6/15 6:34 下午
 */
@Builder
@Data
public class LoginMsg {

    private String username;

    private String token;
}
