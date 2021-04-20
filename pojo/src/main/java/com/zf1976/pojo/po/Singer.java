package com.zf1976.pojo.po;

import java.util.Date;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Builder;
import lombok.Data;
/**
 * (Singer)实体类
 *
 * @author makejava
 * @since 2020-05-20 00:17:26
 */
@Data
@Builder
@TableName("singer")
public class Singer extends Model<Singer> {
 
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 歌手名
     */
    private String name;
    
    /**
     * 性别
     */
    private Integer sex;
    
    /**
     * 照片
     */
    private String pic;
    
    /**
     * 出生年月
     */
    private Date birth;
    
    /**
     * 地点
     */
    private String location;
    
    /**
     * 说明
     */
    private String introduction;
    
}