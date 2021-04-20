package com.zf1976.pojo.vo.app;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;
import org.springframework.lang.NonNull;

import java.util.Date;

/**
 * @author mac
 * Create by Ant on 2020/5/30 7:24 下午
 */
@Data
@Builder
@ApiModel("前台用户vo")
public class UserInfoVO {

    @ApiModelProperty(value = "主键")
    private Integer id;

    @ApiModelProperty(value = "用户名")
    @NonNull
    private String username;

    @ApiModelProperty(value = "性别")
    @NonNull
    private Integer sex;

    @ApiModelProperty(value = "手机号")
    @NonNull
    private String phoneNum;

    @ApiModelProperty(value = "邮箱")
    @NonNull
    private String email;

    @ApiModelProperty(value = "出生年月")
    @NonNull
    private Date birth;

    @ApiModelProperty(value = "介绍")
    private String introduction;

    @ApiModelProperty(value = "地点")
    private String location;

    @ApiModelProperty(value = "头像")
    private String avatar;

}
