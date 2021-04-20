package com.zf1976.pojo.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;


/**
 * @author ant
 * Create by Ant on 2020/5/22 下午5:21
 */
@Data
@Builder
@ApiModel("用户收藏集VO")
public class CollectVO {

    @ApiModelProperty(value = "id")
    private Integer id;

    @ApiModelProperty(value = "客户id")
    private Integer userId;

    @ApiModelProperty(value = "收藏类型")
    private Integer type;

    @ApiModelProperty(value = "歌曲id")
    private Integer songId;

    @ApiModelProperty(value = "歌曲列表id")
    private Integer songListId;

}
