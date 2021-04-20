package com.zf1976.dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zf1976.pojo.po.Rank;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/**
 * (Rank)表数据库访问层
 *
 * @author makejava
 * @since 2020-05-17 22:15:00
 */
@Repository
public interface RankDao extends BaseMapper<Rank> {

    /**
     * 查询评分（平均分）
     *
     * @param songListId 歌单id
     * @return int
     */
    @Select("SELECT AVG(score) FROM rank WHERE song_list_id=#{songListId}")
    Integer getAvgScore(int songListId);

}
