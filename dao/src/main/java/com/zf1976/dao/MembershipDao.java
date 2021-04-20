package com.zf1976.dao;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zf1976.pojo.po.Membership;
import org.springframework.stereotype.Repository;

/**
 * (Membership)表数据库访问层
 *
 * @author makejava
 * @since 2020-06-12 19:10:00
 */
@Repository
public interface MembershipDao extends BaseMapper<Membership> {

}
