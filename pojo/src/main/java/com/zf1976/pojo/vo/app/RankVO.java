package com.zf1976.pojo.vo.app;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

/**
 * @author ant
 * Create by Ant on 2020/5/23 上午10:07
 */
@Data
@Builder
@ApiModel(value = "评分vo")
public class RankVO {

    @ApiModelProperty(value = "id")
    private Integer id;

    @ApiModelProperty(value = "歌曲列表id")
    private Integer songListId;

    @ApiModelProperty("客户id")
    private Integer consumerId;

    @ApiModelProperty(value = "评分")
    private Integer score;

}
