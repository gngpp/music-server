package com.zf1976.pojo.vo.app;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

/**
 * @author mac
 * Create by Ant on 2020/6/8 8:54 下午
 */
@Data
@Builder
@ApiModel(value = "统计vo")
public class StatisticalVO {

    @ApiModelProperty(value = "id")
    private Integer id;

    @ApiModelProperty(value = "下载量")
    private Integer downloads;

    @ApiModelProperty(value = "播放量")
    private Integer playCount;

}
