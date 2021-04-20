package com.zf1976.server.controller.admin;

import com.zf1976.pojo.anno.AdminRestController;
import com.zf1976.pojo.common.DataResult;
import com.zf1976.pojo.dto.admin.CommentDTO;
import com.zf1976.pojo.vo.CommentVO;
import com.zf1976.service.interfaces.CommentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author ant
 * Create by Ant on 2020/5/23 上午8:14
 */
@RestController
@AdminRestController
@RequestMapping(value = "/api/admin")
@SuppressWarnings("rawtypes")
@Api(tags = "后台客户评论管理接口")
public class CommentController {

    @Autowired
    private CommentService service;

    @ApiOperation(value = "根据歌曲id获取评论")
    @GetMapping("comment/song/detail")
    public DataResult<List<CommentVO>> getCommentBySongId(@RequestParam Integer songId){
        return DataResult.success(service.getCommentBySongId(songId));
    }

    @ApiOperation(value = "获得指定歌单ID的评论列表")
    @GetMapping("/comment/song_list/detail")
    public DataResult<List<CommentVO>> getCommentBySongListId(@RequestParam Integer songListId){
        return DataResult.success(service.getCommentBySongListId(songListId));
    }

    @ApiOperation(value = "更新评论")
    @PostMapping("/comment/update")
    public DataResult updateComment(@RequestBody CommentDTO dto){
        return DataResult.success(service.updateCommentMsg(dto));
    }

    @ApiOperation(value = "删除评论")
    @GetMapping("/comment/delete")
    public DataResult deleteComment(@RequestParam Integer id){
        return DataResult.success(service.deleteComment(id));
    }
}
