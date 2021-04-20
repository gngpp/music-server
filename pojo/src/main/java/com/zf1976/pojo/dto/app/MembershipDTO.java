package com.zf1976.pojo.dto.app;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author mac
 * Create by Ant on 2020/6/12 9:51 下午
 */
@Data
@ApiModel(value = "会员服务dto")
public class MembershipDTO {

    @ApiModelProperty(value = "用户名")
    String username;

    @ApiModelProperty(value = "卡号")
    private String cardNumber;

    @ApiModelProperty(value = "卡密")
    private String cardPwd;

    @ApiModelProperty(value = "月卡，季卡，年卡")
    private Integer type;

}
