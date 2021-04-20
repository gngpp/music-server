package com.zf1976.pojo.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Builder;
import lombok.Data;
/**
 * (Membership)实体类
 *
 * @author makejava
 * @since 2020-06-12 19:05:10
 */
@Data
@Builder
@TableName("membership")
public class Membership extends Model<Membership> {
 
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 续费时间
     */
    private Long renewalTime;
    
    /**
     * 到期时间
     */
    private Long expireTime;
    
    /**
     * 客户id
     */
    private Integer consumerId;
    
}