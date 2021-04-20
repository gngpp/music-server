package com.zf1976.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zf1976.pojo.po.Collect;
import org.springframework.stereotype.Repository;
import com.zf1976.pojo.po.Comment;
/**
 * (Collect)表数据库访问层
 *
 * @author makejava
 * @since 2020-05-17 22:15:00
 */
@Repository
public interface CollectDao extends BaseMapper<Collect> {
    
}