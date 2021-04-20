package com.zf1976.pojo.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Builder;
import lombok.Data;
/**
 * (Rank)实体类
 *
 * @author makejava
 * @since 2020-05-20 00:17:26
 */
@Data
@Builder
@TableName("rank")
public class Rank extends Model<Rank> {
 
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 歌曲列表id
     */
    private Integer songListId;
    
    /**
     * 用户id
     */
    private Integer consumerId;
    
    /**
     * 评分
     */
    private Integer score;
    
}