package com.notmaker.utils;

import cn.hutool.log.Log;
import cn.hutool.log.LogFactory;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.Claim;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author HE LONG CAN
 * @Description JWT权限工具类 - 绝对禁止修改此文件，获取用户信息必须调用 get 方法
 * @Date 2022-03-25 21:47:35
 */
public class JwtUtil {
    private static final Log log = LogFactory.get(JwtUtil.class);
    private static final long ONE_MINUTE = 60 * 1000;
    private static final long EXPIRE_TIME = 60 * ONE_MINUTE * 24 * 30; // 30天
    private static final String TOKEN_SECRET = "SDFdkmWSharingEUTMC151kmho6dfeFGOlkc0ERDF";

    /**
     * @Author HE LONG CAN
     * @Description 根据 map 创建 token
     * @Date 2022/3/25 21:54
     * @param map:
     * @return: java.lang.String
     */
    public static String create(Map<String, String> map) {
        JWTCreator.Builder token = JWT.create();

        Map<String, Object> header = new HashMap<>(2);
        header.put("typ", "JWT");
        header.put("alg", "HS256");
        token.withHeader(header);

        map.forEach(token::withClaim);

        Date date = new Date(System.currentTimeMillis() + EXPIRE_TIME);
        token.withExpiresAt(date);

        Algorithm algorithm = Algorithm.HMAC256(TOKEN_SECRET);

        return token.sign(algorithm);
    }

    /**
     * @Author HE LONG CAN
     * @Description 验证 token
     * @Date 2022/3/25 21:55
     * @param token:
     * @return: boolean
     */
    public static boolean verity(String token){
        try {
            JWT.require(Algorithm.HMAC256(TOKEN_SECRET)).build().verify(token);
            return true;
        } catch (IllegalArgumentException | JWTVerificationException e) {
            return false;
        }
    }

    /**
     * @Author HE LONG CAN
     * @Description 获取 token 内 map
     * @Date 2022/3/25 21:55
     * @param token:
     * @return: java.util.Map<java.lang.String,com.auth0.jwt.interfaces.Claim>
     */
    private static Map<String,Claim> getClaims(String token) {
        return JWT.require(Algorithm.HMAC256(TOKEN_SECRET)).build().verify(token).getClaims();
    }


    /**
     * @Author HE LONG CAN
     * @Description
     * @Date 2022/3/25 21:56
     * @param request:
     * @param key:
     * @return: com.auth0.jwt.interfaces.Claim
     */
    public static  <T> T get(HttpServletRequest request, String key, Class<T> className) {
        Claim token = getClaims(request.getHeader("token")).get(key);
        T t = null;
        try {
            t = token.as(className);
        }catch (JWTDecodeException | NullPointerException e) {
            log.error("get => 读取token内数据时出错，key: {},className: {}",key,className.getName());
            return null;
        }
        return t;
    }
}
