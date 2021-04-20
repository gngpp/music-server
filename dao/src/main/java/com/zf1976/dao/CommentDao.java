package com.zf1976.dao;



import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zf1976.pojo.po.Comment;
import com.zf1976.pojo.po.UserCommentSet;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * (CommentVO)表数据库访问层
 *
 * @author makejava
 * @since 2020-05-17 22:15:00
 */
@Repository
public interface CommentDao extends BaseMapper<Comment> {

    /**
     * 根据歌单id查询歌单评论
     * @param songListId id
     * @return List<UserCommentSet>
     */
    @Select("SELECT `comment`.*,consumer.avatar,consumer.username\n" +
            "FROM `comment`,consumer\n" +
            "WHERE `comment`.user_id=consumer.id AND comment.song_list_id=#{songListId};")
    List<UserCommentSet> getUserCommentBySongListId(Integer songListId);

    /**
     * 根据歌曲id查询评论
     * @param songId id
     * @return List<UserCommentSet>
     */
    @Select("SELECT `comment`.*,consumer.avatar,consumer.username\n" +
            "FROM `comment`,consumer\n" +
            "WHERE `comment`.user_id=consumer.id AND comment.song_id=#{songId};")
    List<UserCommentSet> getUserCommentBySongId(Integer songId);

}
