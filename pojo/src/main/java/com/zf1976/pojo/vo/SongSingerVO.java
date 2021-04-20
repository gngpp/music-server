package com.zf1976.pojo.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

/**
 * @author mac
 * Create by Ant on 2020/6/3 6:22 下午
 */
@Data
@Builder
@ApiModel(value = "歌手歌曲vo")
public class SongSingerVO {

    @ApiModelProperty(value = "歌曲id")
    private Integer songId;

    @ApiModelProperty(value = "歌曲名")
    private String songName;

    @ApiModelProperty(value = "歌手名")
    private String singerName;

}
