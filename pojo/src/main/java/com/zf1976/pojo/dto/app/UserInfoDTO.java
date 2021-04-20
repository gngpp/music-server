package com.zf1976.pojo.dto.app;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.lang.NonNull;

import java.util.Date;

/**
 * @author mac
 */
@Data
@ApiModel(value = "前台用户个人信息dto")
public class UserInfoDTO {

    @ApiModelProperty(value = "主键")
    private Integer id;

    @ApiModelProperty(value = "用户名",required = true)
    @NonNull
    private String username;

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
