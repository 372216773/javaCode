package com.wj.common.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

public class JwtUtils {
    //public static final long EXPIRE = 1000 * 60 * 60 * 24;//24小时
    //过期时间
    public static final long EXPIRE = 1000 * 60 * 60 * 24;//24小时
    public static final String APP_SECRET = "N9Re0ITD2tpmbgGtEpzMzjchtmH1AqS6";//私钥(随便设置)

    /**
     * 加密
     * 获取一个jwt字符串
     *
     * @param id 自定义字段  id
     * @return
     */
    public static String getJwtToken(String id,String name) {
        String jwtToken = Jwts.builder()
                //1. jwt的头信息
                .setHeaderParam("typ", "JWT")//类型为令牌类型
                .setHeaderParam("alg", "HS256")//算法使用签名签名

                //2. jwt的有效载荷(内容部分)
                .setSubject("wj-user")//主题
                .setIssuedAt(new Date())//发布时间(生成字符串时间)
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRE))//到期时间
                .claim("id", id).claim("name",name)//自定义字段

                //3. 签名哈希 (使用的什么签名,签名的私钥)
                .signWith(SignatureAlgorithm.HS256, APP_SECRET)//(私钥)防伪标志
                .compact();
        return jwtToken;
    }

    /**
     * 判断token是否存在与有效(是否过期)
     *
     * @param jwtToken token可以为""或者null,都是无效
     * @return
     */
    public static boolean checkToken(String jwtToken) {
        if (StringUtils.isEmpty(jwtToken)) {
            return false;
        }

        try {
            Jwts.parser().setSigningKey(APP_SECRET).parseClaimsJws(jwtToken);
        } catch (Exception e) {
//            e.printStackTrace();
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }


    /**
     * 判断token是否存在与有效
     *
     * @param request token可以为""或者null,都是无效
     * @return
     */
    public static boolean checkToken(HttpServletRequest request) {
        if (StringUtils.isEmpty(request.getHeader("token"))) {
            return false;
        }

        try {
            Jwts.parser().setSigningKey(APP_SECRET).parseClaimsJws(request.getHeader("token"));
        } catch (Exception e) {
//            e.printStackTrace();
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }


    /**
     * 根据token获取会员id
     *
     * @param jwtToken
     * @return
     */
    public static String getMemberIdByJwtToken(String jwtToken) {
        if (StringUtils.isEmpty(jwtToken)) return "";
        Jws<Claims> claimsJws = Jwts.parser().setSigningKey(APP_SECRET).parseClaimsJws(jwtToken);
        Claims claims = claimsJws.getBody();
        return (String) claims.get("id");
    }


    /**
     * 根据token字符串获取会员id
     *
     * @param request
     * @return
     */
    public static String getMemberIdByJwtToken(HttpServletRequest request) {
        String jwtToken = request.getHeader("token");
        if (StringUtils.isEmpty(jwtToken)) return "";
        Jws<Claims> claimsJws = Jwts.parser().setSigningKey(APP_SECRET).parseClaimsJws(jwtToken);
        Claims claims = claimsJws.getBody();
        return (String) claims.get("id");
    }
}
