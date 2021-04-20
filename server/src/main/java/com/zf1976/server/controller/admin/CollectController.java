package com.zf1976.server.controller.admin;

import com.zf1976.pojo.anno.AdminRestController;
import com.zf1976.pojo.common.DataResult;
import com.zf1976.pojo.vo.CollectVO;
import com.zf1976.service.interfaces.CollectService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author ant
 * Create by Ant on 2020/5/22 下午5:15
 */
@RestController
@AdminRestController
@SuppressWarnings("rawtypes")
@RequestMapping(value = "/api/admin")
@Api(tags = "后台客户收藏管理接口")
public class CollectController {

    @Autowired
    private CollectService service;

    @ApiOperation(value = "返回指定用户收藏集")
    @GetMapping("/collection/detail")
    public DataResult<List<CollectVO>> getCollectByUserId(@RequestParam Integer userId){
        return DataResult.success(service.getCollectByUserId(userId));
    }

    @ApiOperation(value = "根据客户id与歌曲id删除收藏")
    @GetMapping("/collection/delete")
    public DataResult deleteCollection(@RequestParam Integer userId,@RequestParam Integer songId){
        return DataResult.success(service.deleteCollect(userId, songId));
    }

}
