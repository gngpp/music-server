package com.zf1976.pojo.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

/**
 * @author ant
 * Create by Ant on 2020/5/21 上午8:16
 */
@Data
@Builder
@ApiModel("consumerVO")
public class ConsumerVO {

    @ApiModelProperty(value = "id")
    private Integer id;

    @ApiModelProperty(value = "用户名")
    private String username;

    @ApiModelProperty(value = "性别")
    private Integer sex;

    @ApiModelProperty(value = "手机号")
    private String phoneNum;

    @ApiModelProperty(value = "邮箱")
    private String email;

    @ApiModelProperty(value = "出生年月")
    private Date birth;

    @ApiModelProperty(value = "介绍")
    private String introduction;

    @ApiModelProperty(value = "地点")
    private String location;

    @ApiModelProperty(value = "头像")
    private String avatar;

    @ApiModelProperty(value = "是否会员")
    private Boolean isMember;
}
