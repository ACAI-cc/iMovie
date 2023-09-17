package com.gec.system.util;

import io.jsonwebtoken.*;
import org.springframework.util.StringUtils;

import java.util.Date;


/**
 * @author  大飞
 * @since  2019/03/12
 *
 *
 *   生成JSON Web令牌的工具类
 */
public class JwtHelper {

    //token过期时间
    private static long tokenExpiration = 365 * 24 * 60 * 60 * 1000;
    //加密秘钥
    private static String tokenSignKey = "123456";

    //根据用户id和用户名称生成token字符串
    public static String createToken(String userId, String username) {
        String token = Jwts.builder()

                // 头部 是固定的 所以不需要 设置

                // 载荷 playload
                .setSubject("DAFEI-USER")  // 主体  就是一个标记
                // 设置 令牌过期时间  从 当前时间 往后算的 过期时间   (我们设置的是一年)
                .setExpiration(new Date(System.currentTimeMillis() + tokenExpiration))
                // 设置用户信息
                .claim("userId", userId)
                .claim("username", username)



                // 使用什么加密算法 和什么秘钥
                .signWith(SignatureAlgorithm.HS512, tokenSignKey)


                //将生成 的 jwt 字符串 压缩 在一行显示
                .compressWith(CompressionCodecs.GZIP)
                // 连接起来 成一个 字符串 （拼接）
                .compact();
        return token;
    }

    //从token字符串获取userid
    public static String getUserId(String token) {
        try {
            if (StringUtils.isEmpty(token)) return null;

            Jws<Claims> claimsJws = Jwts.parser().setSigningKey(tokenSignKey).parseClaimsJws(token);
            Claims claims = claimsJws.getBody();
            String userId = (String) claims.get("userId");
            return userId;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    //从token字符串获取username
    public static String getUsername(String token) {
        try {
            if (StringUtils.isEmpty(token)) return "";

            Jws<Claims> claimsJws = Jwts.parser().setSigningKey(tokenSignKey).parseClaimsJws(token);
            Claims claims = claimsJws.getBody();
            return (String) claims.get("username");
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void main(String[] args) {

        // a.生成 jwt token
        String token = JwtHelper.createToken("4", "huangdaxi");
        System.out.println(token);

        //b. 从 token 中解密获取 userId
        String userId = JwtHelper.getUserId(token);
        System.out.println(userId);



        //c.从 token 中解密获取 username
        String username = JwtHelper.getUsername(token);
        System.out.println(username);
    }
}