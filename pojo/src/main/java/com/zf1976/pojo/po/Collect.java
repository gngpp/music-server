package com.zf1976.pojo.po;

import java.util.Date;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Builder;
import lombok.Data;
/**
 * (Collect)实体类
 *
 * @author makejava
 * @since 2020-05-20 00:17:26
 */
@Data
@Builder
@TableName("collect")
public class Collect extends Model<Collect> {
 
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 用户id
     */
    private Integer userId;
    
    /**
     * 收藏类型
     */
    private Integer type;
    
    /**
     * 歌曲id
     */
    private Integer songId;
    
    /**
     * 歌曲列表id
     */
    private Integer songListId;
    
    /**
     * 创建时间
     */
    private Date createTime;
    
}