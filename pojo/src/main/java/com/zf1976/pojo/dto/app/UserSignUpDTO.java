package com.zf1976.pojo.dto.app;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.lang.NonNull;

import java.util.Date;

/**
 * @author mac
 * Create by Ant on 2020/6/18 10:54 下午
 */
@Data
@ApiModel(value = "前台用户注册dto")
public class UserSignUpDTO {

    @ApiModelProperty(value = "用户名",required = true)
    @NonNull
    private String username;

    @ApiModelProperty(value = "密码",required = true)
    @NonNull
    private String password;

    @ApiModelProperty(value = "性别" ,required = true)
    @NonNull
    private Integer sex;

    @ApiModelProperty(value = "手机号",required = true)
    @NonNull
    private String phoneNum;

    @ApiModelProperty(value = "邮箱",required = true)
    @NonNull
    private String email;

    @ApiModelProperty(value = "出生年月",required = true)
    @NonNull
    private Date birth;

    @ApiModelProperty(value = "介绍")
    private String introduction;

    @ApiModelProperty(value = "地区")
    private String location;

}
