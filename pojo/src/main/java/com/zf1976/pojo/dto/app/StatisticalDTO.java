package com.zf1976.pojo.dto.app;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author mac
 * Create by Ant on 2020/6/8 9:47 下午
 */
@Data
@ApiModel(value = "播放量下载量dto")
public class StatisticalDTO {

    @ApiModelProperty(value = "id")
    private Integer id;

    @ApiModelProperty(value = "下载量")
    private Integer downloads;

    @ApiModelProperty(value = "播放量")
    private Integer playCount;

}
