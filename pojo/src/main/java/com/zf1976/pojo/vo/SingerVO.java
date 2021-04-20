package com.zf1976.pojo.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

/**
 * @author ant
 * Create by Ant on 2020/5/23 上午10:07
 */
@Data
@Builder
@ApiModel(value = "歌手vo")
public class SingerVO {

    @ApiModelProperty(value = "id")
    private Integer id;

    @ApiModelProperty(value = "歌手名")
    private String name;

    @ApiModelProperty(value = "歌手性别")
    private Integer sex;

    @ApiModelProperty(value = "歌手照片")
    private String pic;

    @ApiModelProperty(value = "歌手生日")
    private Date birth;

    @ApiModelProperty(value = "歌手地区")
    private String location;

    @ApiModelProperty(value = "歌手说明")
    private String introduction;
}
