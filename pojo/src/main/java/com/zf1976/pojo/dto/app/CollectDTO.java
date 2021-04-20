package com.zf1976.pojo.dto.app;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author mac
 * Create by Ant on 2020/5/30 10:35 下午
 */
@Data
@ApiModel(value = "收藏dto")
public class CollectDTO {

    @ApiModelProperty(value = "用户id")
    private Integer userId;

    @ApiModelProperty(value = "收藏类型")
    private Integer type;

    @ApiModelProperty(value = "歌曲id")
    private Integer songId;

    @ApiModelProperty(value = "歌曲列表id")
    private Integer songListId;
}
