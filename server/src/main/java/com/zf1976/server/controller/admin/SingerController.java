package com.zf1976.server.controller.admin;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zf1976.pojo.anno.AdminRestController;
import com.zf1976.pojo.common.DataResult;
import com.zf1976.pojo.common.RequestPage;
import com.zf1976.pojo.dto.admin.SingerDTO;
import com.zf1976.pojo.vo.SingerVO;
import com.zf1976.service.interfaces.SingerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author ant
 * Create by Ant on 2020/5/24 下午7:26
 */
@RestController
@AdminRestController
@RequestMapping(value = "/api/admin")
@SuppressWarnings("rawtypes")
@Api(tags = "后台歌手管理接口")
public class SingerController {

    @Autowired
    private SingerService service;

    @ApiOperation(value = "获取所有歌手")
    @PostMapping("/singer")
    public DataResult<IPage<SingerVO>> getAllSinger(@RequestBody RequestPage requestPage){
        return DataResult.success(service.getSingerPage(requestPage));
    }

    @ApiOperation(value = "添加歌手")
    @PostMapping("/singer/add")
    public DataResult addSinger(@RequestBody SingerDTO dto){
        return DataResult.success(service.addSinger(dto));
    }

    @ApiOperation(value = "根据id删除歌手")
    @GetMapping("/singer/delete")
    public DataResult deleteSinger(@RequestParam Integer id){
        return DataResult.success(service.deleteSinger(id));
    }

    @ApiOperation(value = "更新歌手信息")
    @PostMapping("/singer/update")
    public DataResult updateSinger(@RequestBody SingerDTO dto){
        return DataResult.success(service.updateSingerMsg(dto));
    }

    @ApiOperation(value ="更新歌手照片")
    @PostMapping("/singer/avatar/update")
    public DataResult updatePic(@RequestParam("file") MultipartFile uploadFile,@RequestParam("id") Integer id){
        return DataResult.success(service.updateSingerPic(uploadFile, id));
    }

}
