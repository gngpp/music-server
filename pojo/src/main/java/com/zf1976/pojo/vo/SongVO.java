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
@ApiModel(value = "歌曲vo")
public class SongVO {

    @ApiModelProperty(value = "id")
    private Integer id;

    @ApiModelProperty(value = "歌手id")
    private Integer singerId;

    @ApiModelProperty(value = "歌曲名")
    private String name;

    @ApiModelProperty(value = "歌曲说明")
    private String introduction;

    @ApiModelProperty("发行时间")
    private Date createTime;

    @ApiModelProperty(value = "更新时间")
    private Date updateTime;

    @ApiModelProperty(value = "照片")
    private String pic;

    @ApiModelProperty(value = "抒情的")
    private String lyric;

    @ApiModelProperty(value = "url")
    private String url;

    @ApiModelProperty(value = "下载量")
    private Integer downloads;

    @ApiModelProperty(value = "播放量")
    private Integer playCount;

    @ApiModelProperty(value = "是否付费")
    private Boolean isPay;

}
