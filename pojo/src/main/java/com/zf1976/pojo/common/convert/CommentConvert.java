package com.zf1976.pojo.common.convert;

import com.zf1976.pojo.dto.admin.CommentDTO;
import com.zf1976.pojo.po.Comment;
import com.zf1976.pojo.vo.CommentVO;
import com.zf1976.pojo.po.UserCommentSet;
import com.zf1976.pojo.vo.app.UserCommentSetVO;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author ant
 * Create by Ant on 2020/5/23 上午11:31
 */
@Mapper(componentModel = "spring")
public interface CommentConvert {

    /**
     * 转vo
     *
     * @param comment po
     * @return vo
     */
    CommentVO toVo(Comment comment);

    /**
     * 转po
     *
     * @param dto dto
     * @return vo
     */
    Comment toPo(CommentDTO dto);

    /**
     * 转vo list
     *
     * @param comments po list
     * @return vo list
     */
    List<CommentVO> toVoList(List<Comment> comments);

    /**
     * 转vo list
     *
     * @param sets po list
     * @return vo list
     */
    List<UserCommentSetVO> toUserCommentSetVoList(List<UserCommentSet> sets);

    /**
     * 转vo
     *
     * @param set po
     * @return vo
     */
    UserCommentSetVO toUserCommentSetVo(UserCommentSet set);

    /**
     * 转po
     *
     * @param vo vo
     * @return po
     */
    Comment toPo(CommentVO vo);

}
