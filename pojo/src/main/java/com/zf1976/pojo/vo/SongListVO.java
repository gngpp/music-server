package com.zf1976.pojo.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

/**
 * @author ant
 * Create by Ant on 2020/5/23 上午10:04
 */
@Data
@Builder
@ApiModel(value = "歌曲列表vo")
public class SongListVO {

    @ApiModelProperty(value = "id")
    private Integer id;

    @ApiModelProperty(value = "标题")
    private String title;

    @ApiModelProperty(value = "照片")
    private String pic;

    @ApiModelProperty(value = "说明")
    private String introduction;

    @ApiModelProperty(value = "样式")
    private String style;

}
