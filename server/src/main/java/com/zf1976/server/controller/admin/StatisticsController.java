package com.zf1976.server.controller.admin;

import com.zf1976.pojo.anno.AdminRestController;
import com.zf1976.pojo.common.DataResult;
import com.zf1976.service.interfaces.MembershipService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author mac
 * Create by Ant on 2020/6/14 1:49 下午
 */
@RestController
@AdminRestController
@RequestMapping(value = "/api/admin")
@SuppressWarnings("rawtypes")
@Api(tags = "后台统计业务接口")
public class StatisticsController {

    @Autowired
    private MembershipService service;

    @ApiOperation(value = "获取总营业额")
    @GetMapping("/statistics/turnover")
    public DataResult getTurnover(){
        return DataResult.success(service.getTurnover());
    }

    @ApiOperation(value = "获取待售营业额")
    @GetMapping("/statistics/unsold")
    public DataResult getUnsold(){
        return DataResult.success(service.getUnsold());
    }
}
