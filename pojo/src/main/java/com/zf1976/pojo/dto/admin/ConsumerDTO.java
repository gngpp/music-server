package com.zf1976.pojo.dto.admin;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.lang.NonNull;

import java.util.Date;

/**
 * @author ant
 * Create by Ant on 2020/5/20 下午9:01
 */
@Data
@ApiModel("后台添加用户DTO")
public class ConsumerDTO {

    @ApiModelProperty(value = "主键")
    private Integer id;

    @ApiModelProperty(value = "用户名",required = true)
    @NonNull
    private String username;

    @ApiModelProperty(value = "性别",required = true)
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

    @ApiModelProperty(value = "地点")
    private String location;

    @ApiModelProperty(value = "头像")
    private String avatar;
}
