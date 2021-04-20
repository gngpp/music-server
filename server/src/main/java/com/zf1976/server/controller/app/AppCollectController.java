package com.zf1976.server.controller.app;

import com.zf1976.pojo.anno.AppRestController;
import com.zf1976.pojo.common.DataResult;
import com.zf1976.pojo.dto.app.CollectDTO;
import com.zf1976.pojo.vo.CollectVO;
import com.zf1976.service.interfaces.CollectService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author ant
 * Create by Ant on 2020/6/1 上午11:10
 */

@RestController
@AppRestController
@RequestMapping(value ="/api/app")
@SuppressWarnings("rawtypes")
@Api(tags = "前台用户收藏接口")
public class AppCollectController {

    @Autowired
    private CollectService service;

    @ApiOperation(value = "获取的指定用户ID的收藏列表")
    @GetMapping("/collection/detail")
    public DataResult<List<CollectVO>> getCollectionByUserId(@RequestParam Integer userId){
        return DataResult.success(service.getCollectByUserId(userId));
    }

    @ApiOperation(value = "添加收藏的歌曲 type: 0 代表歌曲， 1 代表歌单")
    @PostMapping("/collection/add")
    public DataResult addCollection(@RequestBody CollectDTO dto){
        return DataResult.success(service.addCollection(dto));
    }

    @ApiOperation(value = "取消收藏")
    @PostMapping("/collection/delete")
    public DataResult deleteCollection(@RequestBody CollectDTO dto){
        return DataResult.success(service.deleteCollection(dto));
    }
}
