package com.zf1976.dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zf1976.pojo.po.SongList;
import org.springframework.stereotype.Repository;

/**
 * (SongList)表数据库访问层
 *
 * @author makejava
 * @since 2020-05-17 22:15:00
 */
@Repository
public interface SongListDao extends BaseMapper<SongList> {
    
}