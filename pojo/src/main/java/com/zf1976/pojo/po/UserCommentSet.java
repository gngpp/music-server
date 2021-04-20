package com.zf1976.pojo.po;

import com.baomidou.mybatisplus.annotation.TableName;
import com.zf1976.pojo.vo.CommentVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;
import org.springframework.lang.NonNull;

import java.util.Date;
import java.util.List;

/**
 * @author mac
 * Create by Ant on 2020/6/1 11:10 下午
 */
@Data
@Builder
@TableName("comment,consumer")
public class UserCommentSet {

    @ApiModelProperty(value = "id")
    private Integer id;

    @ApiModelProperty(value = "用户id")
    private Integer userId;

    @ApiModelProperty(value = "歌曲id")
    private Integer songId;

    @ApiModelProperty(value = "歌曲列表id")
    private Integer songListId;

    @ApiModelProperty(value = "评论内容")
    private String content;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @ApiModelProperty(value = "类型")
    private Integer type;

    @ApiModelProperty(value = "up主")
    private Integer up;

    @ApiModelProperty(value = "用户名")
    private String username;

    @ApiModelProperty(value = "头像")
    private String avatar;
}
