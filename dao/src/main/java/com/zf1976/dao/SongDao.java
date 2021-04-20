package com.zf1976.dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zf1976.pojo.po.Song;
import org.springframework.stereotype.Repository;

/**
 * (Song)表数据库访问层
 *
 * @author makejava
 * @since 2020-05-17 22:15:00
 */
@Repository
public interface SongDao extends BaseMapper<Song> {
    
}