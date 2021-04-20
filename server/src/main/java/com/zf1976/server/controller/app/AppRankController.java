package com.zf1976.server.controller.app;

import com.zf1976.pojo.anno.AppRestController;
import com.zf1976.pojo.common.DataResult;
import com.zf1976.pojo.dto.app.RankDTO;
import com.zf1976.service.interfaces.RankService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author ant
 * Create by Ant on 2020/6/1 上午11:15
 */

@RestController
@AppRestController
@RequestMapping(value ="/api/app")
@SuppressWarnings("rawtypes")
@Api(tags = "前台评分接口")
public class AppRankController {

    @Autowired
    private RankService service;

    @ApiOperation(value = "提交评分")
    @PostMapping("/rank/add")
    public DataResult addRank(@RequestBody RankDTO dto){
        return DataResult.success(service.addRank(dto));
    }

    @ApiOperation(value = "获取指定歌单的评分")
    @GetMapping("/rank")
    public DataResult<Integer> getRankBySongListId(@RequestParam Integer songListId){
        return DataResult.success(service.getSongListBankById(songListId));
    }

}
