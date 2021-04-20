package com.zf1976.pojo.vo.app;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

/**
 * @author mac
 * Create by Ant on 2020/6/1 4:44 下午
 */
@Data
@Builder
@ApiModel(value = "用户登陆后信息")
public class UserMsgVO {

    @ApiModelProperty(value = "id")
    private Integer id;

    @ApiModelProperty(value = "用户名")
    private String username;

    @ApiModelProperty(value = "头像")
    private String avatar;

    @ApiModelProperty(value = "是否会员")
    private Boolean isMember;

    @ApiModelProperty(value = "token")
    private String token;
}
