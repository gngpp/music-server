package com.zf1976.server.controller;


import com.zf1976.pojo.anno.AdminRestController;
import com.zf1976.pojo.common.DataResult;
import com.zf1976.service.interfaces.AdminService;
import com.zf1976.pojo.dto.admin.AdminLoginDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author ant
 * Create by Ant on 2020/5/18 下午7:43
 */
@RestController
@AdminRestController
@RequestMapping("/api/admin")
@Api("SecurityController")
@SuppressWarnings("rawtypes")
public class SecurityController {

    @Autowired
    private AdminService adminService;

    @RequestMapping(value = "/login/status",method = RequestMethod.POST)
    @ApiOperation(value = "登陆接口")
    public DataResult doLogin(@RequestBody AdminLoginDTO adminLoginDTO) {
        return DataResult.success(adminService.checkLogin(adminLoginDTO));
    }

}
