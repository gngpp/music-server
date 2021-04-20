package com.zf1976.pojo.dto.app;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author mac
 * Create by Ant on 2020/6/11 9:01 下午
 */
@Data
@ApiModel(value = "修改密码dto")
public class ChangePassDTO {

    @ApiModelProperty(value = "用户名")
    private String username;

    @ApiModelProperty(value = "旧密码")
    private String oldPass;

    @ApiModelProperty(value = "新密码")
    private String pass;

    @ApiModelProperty(value = "确认新密码")
    private String checkPass;

}
