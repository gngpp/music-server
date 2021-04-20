package com.zf1976.pojo.dto.app;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author mac
 * Create by Ant on 2020/5/31 4:31 下午
 */
@Data
@ApiModel("评分dto")
public class RankDTO {

    @ApiModelProperty(value = "歌曲列表id")
    private Integer songListId;

    @ApiModelProperty("客户id")
    private Integer consumerId;

    @ApiModelProperty(value = "评分")
    private Integer score;

}
