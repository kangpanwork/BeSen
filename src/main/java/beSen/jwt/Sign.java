package beSen.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

/**
 * https://jwt.io/
 *
 * JWT含有三部分：头部（header）、载荷（payload）、签名（signature）
 * 载荷（payload）
 * 该部分一般存放一些有效的信息。JWT的标准定义包含五个字段：
 * iss：该JWT的签发者
 * sub: 该JWT所面向的用户
 * aud: 接收该JWT的一方
 * exp(expires): 什么时候过期，这里是一个Unix时间戳
 * iat(issued at): 在什么时候签发的
 * @author 康盼Java开发工程师
 */
public class Sign {

    private static Map<String, JWTVerifier> verifierMap = new HashMap<>();
    private static Map<String, Algorithm> algorithmMap = new HashMap<>();

    public static String generateToken(String target
            , Map<String, String> parameters, long time, String... audience) {
        // Add a specific Audience ("aud") claim to the Payload.
        Algorithm algorithm = getAlgorithm(target);
        Calendar calendar = new GregorianCalendar();
        Date date = new Date(System.currentTimeMillis() + time);
        calendar.setTime(date);
        calendar.add(Calendar.DATE,1);
        String token = JWT.create().withAudience(audience)
                .withClaim("name",parameters.get("name"))
                .withClaim("age",parameters.get("age"))
                .withClaim("sex",parameters.get("sex"))
                .withExpiresAt(calendar.getTime()).sign(algorithm);
        return token;
    }


    /**
     * 校验token
     *
     * @param target
     * @param token
     * @return
     */
    public static DecodedJWT verifyToken(String token, String target) {
        JWTVerifier verifier = verifierMap.get(token);
        if (verifier == null) {
            synchronized (verifierMap) {
                verifier = verifierMap.get(target);
                if (verifier == null) {
                    Algorithm algorithm = getAlgorithm(target);
                    verifier = JWT.require(algorithm).build();
                    verifierMap.put(target, verifier);
                }
            }
        }

        DecodedJWT jwt = verifier.verify(token);
        return jwt;
    }

    private static Algorithm getAlgorithm(String target) {
        Algorithm algorithm = algorithmMap.get(target);
        if (algorithm == null) {
            synchronized (algorithmMap) {
                algorithm = algorithmMap.get(target);
                if (algorithm == null) {
                    algorithm = Algorithm.HMAC512(target);
                    algorithmMap.put(target, algorithm);
                }
            }
        }
        return algorithm;
    }
}