package com.zf1976.pojo.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Builder;
import lombok.Data;
/**
 * (ListSong)实体类
 *
 * @author makejava
 * @since 2020-05-20 00:17:26
 */
@Data
@Builder
@TableName("list_song")
public class ListSong extends Model<ListSong> {
 
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 歌曲id
     */
    private Integer songId;
    
    /**
     * 歌曲列表id
     */
    private Integer songListId;
    
}