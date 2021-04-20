package com.zf1976.pojo.dto.admin;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author ant
 * Create by Ant on 2020/5/23 下午10:55
 */
@Data
@ApiModel(value = "列表歌曲DTO")
public class ListSongDTO {

    @ApiModelProperty(value = "歌曲id")
    private Integer songId;

    @ApiModelProperty(value = "歌曲列表id")
    private Integer songListId;
}
