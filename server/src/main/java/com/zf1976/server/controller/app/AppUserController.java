package com.zf1976.server.controller.app;

import com.zf1976.pojo.anno.AppRestController;
import com.zf1976.pojo.common.DataResult;
import com.zf1976.pojo.dto.app.ChangePassDTO;
import com.zf1976.pojo.dto.app.UserInfoDTO;
import com.zf1976.pojo.dto.app.UserSignUpDTO;
import com.zf1976.service.aspect.annotation.Log;
import com.zf1976.pojo.vo.app.UserInfoVO;
import com.zf1976.service.interfaces.ConsumerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @author mac
 */
@RestController
@AppRestController
@RequestMapping(value = "/api/app")
@SuppressWarnings("rawtypes")
@Api(tags = "前台用户接口")
public class AppUserController {

    @Autowired
    private ConsumerService service;

    @ApiOperation(value = "前台用户注册")
    @PostMapping("/sign_in")
    public DataResult signUp(@RequestBody UserSignUpDTO dto){
        return DataResult.success(service.signUp(dto));
    }

    @ApiOperation(value = "前台用户更新信息")
    @PutMapping("/user/update")
    public DataResult updateUserMsg(@RequestBody UserInfoDTO dto){
        return DataResult.success(service.updateUserMsg(dto));
    }

    @ApiOperation(value = "更新用户头像")
    @PostMapping("/user/avatar/update")
    public DataResult updateUserPic(@RequestParam("file")MultipartFile uploadFile, @RequestParam("id") Integer id){
        return DataResult.success(service.updateAvatar(uploadFile, id));
    }

    @ApiOperation(value = "根据用户id返回用户信息")
    @GetMapping("/user/detail")
    public  DataResult<UserInfoVO> getUserById(@RequestParam Integer id){
        return DataResult.success(service.getUserById(id));
    }

    @ApiOperation(value = "根据用户id集合返回用户")
    @GetMapping("/user/list/detail")
    public  DataResult<List<UserInfoVO>> getUserByIds(@RequestParam List<Integer> ids){
        return DataResult.success(service.getUserByIds(ids));
    }

    @ApiOperation(value = "根据用户id获取头像path")
    @GetMapping("/user/avatar")
    public DataResult<String> getUserAvatar(@RequestParam Integer id){
        return DataResult.success(service.getUserAvatar(id));
    }

    @ApiOperation(value = "前台用户修改密码")
    @PutMapping("/user/security")
    public DataResult changePass(@RequestBody ChangePassDTO dto){
        return DataResult.success(service.changePass(dto));
    }


}
