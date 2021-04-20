package com.zf1976.service.secutity.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zf1976.pojo.common.DataResult;
import com.zf1976.pojo.common.business.enums.BusinessMsgEnum;
import com.zf1976.pojo.common.convert.ConsumerConvert;
import com.zf1976.pojo.po.Consumer;
import com.zf1976.pojo.vo.app.UserMsgVO;
import com.zf1976.service.common.SpringUtil;
import com.zf1976.service.interfaces.ConsumerService;
import com.zf1976.service.secutity.VerifyCodeException;
import com.zf1976.service.secutity.cache.VerifyCodeService;
import com.zf1976.service.secutity.entity.UserLoginDTO;
import com.zf1976.service.secutity.JwtTokenUtils;
import com.zf1976.service.secutity.impl.JwtUser;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @author mac
 * Create by Ant on 2020/6/16 6:29 下午
 */
public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;

    private Boolean isRememberMe = false;

    public JwtAuthenticationFilter(AuthenticationManager authenticationManager){
        this.authenticationManager = authenticationManager;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request,
                                                HttpServletResponse response) throws AuthenticationException {


        final UserLoginDTO dto;
        try {
            dto = new ObjectMapper().readValue(request.getInputStream(), UserLoginDTO.class);

            final VerifyCodeService codeService = SpringUtil.getBean(VerifyCodeService.class);

            if (!codeService.verifyCode("", dto.getRandomCode(), dto.getVerifyCode())) {
                throw new VerifyCodeException(BusinessMsgEnum.CODE_ERROR.getMsg());
            } else {
                codeService.clearVerifyCode("",dto.getRandomCode());
            }

            this.isRememberMe = dto.getIsRememberMe();
            return authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(dto.getUsername(), dto.getPassword(), new ArrayList<>()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response,
                                            FilterChain chain,
                                            Authentication authResult) throws IOException, ServletException {
        final JwtUser user = (JwtUser) authResult.getPrincipal();
        AtomicReference<String> role = new AtomicReference<>("");
        user.getAuthorities().forEach((auth)->{
            role.set(auth.getAuthority());
        });
        final String token = JwtTokenUtils.createToken(user.getUsername(), role.get(), isRememberMe);

        final ConsumerConvert convert = SpringUtil.getBean(ConsumerConvert.class);
        final ConsumerService service = SpringUtil.getBean(ConsumerService.class);

        final Consumer consumer = service.findByUsername(user.getUsername());
        final UserMsgVO vo = convert.toUserMasVo(consumer);
        vo.setToken(token);
        final PrintWriter out = response.getWriter();
        out.write(new ObjectMapper().writeValueAsString(DataResult.success(vo)));
        out.flush();
        out.close();
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request,
                                              HttpServletResponse response,
                                              AuthenticationException failed) throws IOException, ServletException {
        response.getWriter().write("authentication failed, reason: " + failed.getMessage());
    }


}
