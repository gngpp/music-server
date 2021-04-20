package com.zf1976.server.controller.app;

import com.zf1976.pojo.anno.AppRestController;
import com.zf1976.pojo.common.DataResult;
import com.zf1976.pojo.dto.app.MembershipDTO;
import com.zf1976.service.interfaces.MembershipService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author mac
 * Create by Ant on 2020/6/12 9:54 下午
 */
@RestController
@AppRestController
@RequestMapping(value ="/api/app")
@SuppressWarnings("rawtypes")
@Api(tags = "前台用户会员接口")
public class AppBusinessController {

    @Autowired
    private MembershipService service;

    @ApiOperation(value = "会员续费")
    @PostMapping("/club_card/membership")
    public DataResult membershipBusiness(@RequestBody MembershipDTO dto){
        return DataResult.success(service.membershipOpen(dto));
    }

    @ApiOperation(value = "获取开通会员后过期时间")
    @GetMapping("/club_card/membership/after_expire")
    public DataResult<Long> getExpireTime(@RequestParam Integer consumerId,@RequestParam Integer afterMonth){
        return DataResult.success(service.getAfterExpireTime(consumerId, afterMonth));
    }

    @ApiOperation(value = "获取会员过期时间")
    @GetMapping("/club_card/membership/current_expire")
    public DataResult<Long> getExpireTime(@RequestParam Integer consumerId){
        return DataResult.success(service.getCurrentExpireTime(consumerId));
    }
}
