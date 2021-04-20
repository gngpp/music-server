package com.zf1976.pojo.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

/**
 * @author ant
 * Create by Ant on 2020/5/23 上午10:01
 */
@Data
@Builder
@ApiModel(value = "列表歌曲vo")
public class ListSongVO {

    @ApiModelProperty(value = "id")
    private Integer id;

    @ApiModelProperty(value = "歌曲id")
    private Integer songId;

    @ApiModelProperty(value = "歌曲列表id")
    private Integer songListId;
}
