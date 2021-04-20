package com.zf1976.server.controller.admin;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zf1976.pojo.anno.AdminRestController;
import com.zf1976.pojo.common.DataResult;
import com.zf1976.pojo.common.RequestPage;
import com.zf1976.pojo.dto.admin.SongDTO;
import com.zf1976.pojo.vo.SongVO;
import com.zf1976.service.interfaces.SongService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import java.util.List;

/**
 * @author ant
 * Create by Ant on 2020/5/24 下午7:25
 */
@RestController
@AdminRestController
@RequestMapping(value = "/api/admin")
@SuppressWarnings("rawtypes")
@Api(tags = "后台歌曲管理接口")
public class SongController {

    @Autowired
    private SongService service;

    @ApiOperation(value = "分页获取歌曲")
    @PostMapping("/song")
    public DataResult<IPage<SongVO>> getAllSong(@RequestBody RequestPage<SongDTO> requestPage){
        return DataResult.success(service.getSongPage(requestPage));
    }

    @ApiOperation(value = "返回指定歌手ID歌曲")
    @GetMapping("/song/singer/detail")
    public DataResult<List<SongVO>> getSongBySingerId(@RequestParam Integer singerId){
        return DataResult.success(service.getSongBySingerId(singerId));
    }

    @ApiOperation(value = "返回的指定ID歌曲")
    @GetMapping("/song/detail")
    public DataResult<SongVO> getSongById(@RequestParam Integer id){
        return DataResult.success(service.getSongById(id));
    }

    @ApiOperation(value = "返回指定歌手名歌曲")
    @GetMapping("/song/singer_name")
    public DataResult<List<SongVO>> getSongBySingerName(@RequestParam String name){
        return DataResult.success(service.getSongByLikeSingerName(name));
    }

    @ApiOperation(value = "根据歌曲id更新歌曲信息")
    @PostMapping("/song/update")
    public DataResult updateSong(@RequestBody SongDTO dto){
        return DataResult.success(service.updateSongMsg(dto));
    }

    @ApiOperation(value = "添加歌曲")
    @PostMapping(value = "/song/add")
    public DataResult addSong(@RequestParam("file") MultipartFile uploadFile, SongDTO dto){
        return DataResult.success(service.addSong(uploadFile, dto));
    }

    @ApiOperation(value = "根据歌曲id删除歌曲")
    @GetMapping("/song/delete")
    public DataResult deleteSong(@RequestParam Integer id){
        return DataResult.success(service.deleteSong(id));
    }

    @ApiOperation(value = "更新歌曲封面")
    @PostMapping("/song/img/update")
    public DataResult updateSongPic(@RequestParam("file") MultipartFile uploadFile,@RequestParam("id") Integer id){
        return DataResult.success(service.uploadSongPic(uploadFile, id));
    }

    @ApiOperation(value = "更新歌曲资源")
    @PostMapping("/song/url/update")
    public DataResult updateSongUrl(@RequestParam("file") MultipartFile uploadFile,@RequestParam("id") Integer id){
        return DataResult.success(service.uploadSongUrl(uploadFile, id));
    }
}
