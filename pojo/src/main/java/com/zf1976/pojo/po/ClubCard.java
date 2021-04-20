package com.zf1976.pojo.po;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Builder;
import lombok.Data;
/**
 * (ClubCard)实体类
 *
 * @author makejava
 * @since 2020-06-10 23:34:49
 */
@Data
@Builder
@TableName("club_card")
public class ClubCard extends Model<ClubCard> {

    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 卡号
     */
    private String cardNumber;

    /**
     * 卡密
     */
    private String cardPwd;

    /**
     * false表示未使用， true表示已使用过
     */
    @TableLogic
    private Boolean deleted;

    /**
     * 价格
     */
    private Integer price;

    /**
     * 卡类型（月卡，季卡，年卡）
     */
    private Integer type;

}
