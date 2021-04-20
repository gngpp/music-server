package com.zf1976.server.controller.admin;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zf1976.pojo.anno.AdminRestController;
import com.zf1976.pojo.common.DataResult;
import com.zf1976.pojo.common.RequestPage;
import com.zf1976.pojo.dto.admin.SongListDTO;
import com.zf1976.pojo.vo.SongListVO;
import com.zf1976.service.interfaces.SongListService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


/**
 * @author ant
 * Create by Ant on 2020/5/24 下午1:05
 */
@RestController
@AdminRestController
@RequestMapping(value = "/api/admin")
@SuppressWarnings("rawtypes")
@Api(tags = "后台歌单管理接口")
public class SongListController {

    @Autowired
    private SongListService service;

    @ApiOperation(value = "分页获取歌单")
    @PostMapping("/song_list")
    public DataResult<IPage<SongListVO>> getAllSongList(@RequestBody RequestPage<SongListDTO> requestPage){
        return DataResult.success(service.getSongListPage(requestPage));
    }

    @ApiOperation(value = "添加歌单")
    @PostMapping("/song_list/add")
    public DataResult addSongList(@RequestBody SongListDTO dto){
        return DataResult.success(service.addSongList(dto));
    }

    @ApiOperation(value = "根据id删除歌单")
    @GetMapping("/song_list/delete")
    public DataResult deleteSongList(@RequestParam Integer id){
        return DataResult.success(service.deleteSongList(id));
    }

    @ApiOperation(value = "更新歌单信息")
    @PostMapping("/song_list/update")
    public DataResult updateSongList(@RequestBody SongListDTO dto){
        return DataResult.success(service.updateSongListMsg(dto));
    }

    @ApiOperation(value = "更新歌单封面")
    @PostMapping("/song_list/img/update")
    public DataResult updateSongListPic(@RequestParam("file") MultipartFile uploadFile,@RequestParam("id") Integer id){
        return DataResult.success(service.updateSongListPic(uploadFile, id));
    }
}
