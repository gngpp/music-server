package com.zf1976.service.secutity;

import cn.hutool.core.lang.UUID;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;
import java.util.HashMap;

/**
 * @author mac
 * Create by Ant on 2020/6/16 6:11 下午
 */
public class JwtTokenUtils {

    public static final String TOKEN_HEADER = "Authorization";
    public static final String TOKEN_PREFIX = "Bearer ";
    private static final String SECRET = "com.zf1976";
    private static final String ISS = "1976";
    private static final String ROLE_CLAIMS = "role";

    /**
     * 过期时间是3600秒，既是1个小时
     */
    private static final long EXPIRATION = 3600*24L;

    /**
     * 选择了记住我之后的过期时间为7天
     */
    private static final long EXPIRATION_REMEMBER = 604800L;

    /**
     * 创建token
     *
     * @param username 用户名
     * @param isRememberMe 是否记住
     * @return token
     */
    public static String createToken(String username,String role,boolean isRememberMe) {
        long expiration = isRememberMe ? EXPIRATION_REMEMBER : EXPIRATION;
        final HashMap<String, Object> map = new HashMap<>(2);
        map.put(ROLE_CLAIMS, role);
        map.put(username, UUID.randomUUID(true));
        return Jwts.builder()
                   .signWith(SignatureAlgorithm.HS512, SECRET)
                   .setClaims(map)
                   .setIssuer(ISS)
                   .setSubject(username)
                   .setIssuedAt(new Date())
                   .setExpiration(new Date(System.currentTimeMillis() + expiration * 1000))
                   .compact();
    }

    /**
     * 从token中获取用户名
     *
     * @param token token
     * @return 用户名
     */
    public static String getUsername(String token){
        return getClaimsByToken(token).getSubject();
    }

    /**
     * 从token中获取用户角色
     *
     * @param token token
     * @return 角色
     */
    public static String getUserRole(String token){
        return getClaimsByToken(token).get(ROLE_CLAIMS, String.class);
    }

    /**
     * 是否已过期
     *
     * @param token token
     * @return boolean
     */
    public static boolean isExpiration(String token){
        return getClaimsByToken(token).getExpiration().before(new Date());
    }

    /**
     * 获取token主体
     *
     * @param token token
     * @return 主体
     */
    private static Claims getClaimsByToken(String token){
        return Jwts.parser()
                   .setSigningKey(SECRET)
                   .parseClaimsJws(token)
                   .getBody();
    }
}

