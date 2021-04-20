package com.zf1976.pojo.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 主键(Admin)实体类
 *
 * @author makejava
 * @since 2020-05-20 00:17:26
 */
@Data
@Builder
@TableName("admin")
public class Admin extends Model<Admin> {
 
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 用户名
     */
    private String username;
    
    /**
     * 密码
     */
    private String password;
    
}