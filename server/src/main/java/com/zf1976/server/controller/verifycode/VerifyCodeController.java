package com.zf1976.server.controller.verifycode;

import com.zf1976.pojo.anno.AppRestController;
import com.zf1976.service.secutity.cache.VerifyCodeService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * @author ant
 * Create by Ant on 2020/6/13 下午5:22
 */
@RestController
@AppRestController
@RequestMapping(value = "/api/app/verifyCode")
@Slf4j
@Api(value = "验证码接口")
public class VerifyCodeController {

    @Autowired
    @Qualifier("imageVerifyCodeService")
    private VerifyCodeService verifyCodeService;

    @GetMapping("/{randomCode}")
    public void get(@PathVariable("randomCode") String randomCode, HttpServletResponse response) throws IOException {
        final ServletOutputStream out = response.getOutputStream();
        final String code = verifyCodeService.generateCode(out);
        response.setStatus(HttpServletResponse.SC_OK);
        response.setContentType("image/jpeg");
        response.setHeader("Pragma", "No-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expire", 0);
        if(!verifyCodeService.sendVerifyCode("", randomCode, code, 1, TimeUnit.MINUTES)) {
            log.error("Get image verify code fail, save verify code to repository fail!");
        }
        if(log.isInfoEnabled()) {
            log.info("Generate image verify code with: {}", code);
        }
        out.flush();
        out.close();
    }
}
