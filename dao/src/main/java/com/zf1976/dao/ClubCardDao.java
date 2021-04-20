package com.zf1976.dao;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zf1976.pojo.po.ClubCard;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/**
 * (ClubCard)表数据库访问层
 *
 * @author makejava
 * @since 2020-06-10 23:37:13
 */
@Repository
public interface ClubCardDao extends BaseMapper<ClubCard> {

    /**
     * 获取营业额
     *
     * @param isDelete 是否已经使用
     * @return 营业额
     */
    @Select("SELECT SUM(price) FROM club_card WHERE deleted = #{isDelete}")
    Integer getRepertory(Boolean isDelete);
}
