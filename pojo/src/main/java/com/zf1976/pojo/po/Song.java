package com.zf1976.pojo.po;

import java.util.Date;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;
/**
 * (Song)实体类
 *
 * @author makejava
 * @since 2020-05-20 00:17:26
 */
@Data
@Builder
@TableName("song")
public class Song extends Model<Song> {

    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 歌手id
     */
    private Integer singerId;

    /**
     * 歌曲名
     */
    private String name;

    /**
     * 说明
     */
    private String introduction;

    /**
     * 发行时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 照片
     */
    private String pic;

    /**
     * 抒情的
     */
    private String lyric;

    /**
     * url
     */
    private String url;

    /**
     * 下载量
     */
    private Integer downloads;

    /**
     * 播放量
     */
    private Integer playCount;

    /**
     * 是否付费
     */
    private Boolean isPay;

}
