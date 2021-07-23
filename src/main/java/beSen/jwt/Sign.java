package beSen.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.HashMap;
import java.util.Map;

/**
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

    private static Map<String, JWTVerifier> verifierMap = new HashMap<String, JWTVerifier>();
    private static Map<String, Algorithm> algorithmMap = new HashMap<String, Algorithm>();

    public static String generateToken(Map<String,Object> target,long time,String... audience) {
        // Add a specific Audience ("aud") claim to the Payload.
        JWTCreator.Builder build = JWT.create().withAudience(audience);
        return null;
    }


    /**
     * 校验token
     *
     * @param target 需要校验的字符串
     * @param token 生成的token
     * @return
     */
    public static DecodedJWT verifyToken(String target, String token) {
        JWTVerifier verifier = verifierMap.get(token);
        if (verifier == null) {
            synchronized (verifierMap) {
                verifier = verifierMap.get(token);
                if (verifier == null) {
                    Algorithm algorithm = getAlgorithm(token);
                    verifier = JWT.require(algorithm).build();
                    verifierMap.put(token, verifier);
                }
            }
        }

        DecodedJWT jwt = verifier.verify(target);
        return jwt;
    }

    private static Algorithm getAlgorithm(String token) {
        Algorithm algorithm = algorithmMap.get(token);
        if (algorithm == null) {
            synchronized (algorithmMap) {
                algorithm = algorithmMap.get(token);
                if (algorithm == null) {
                    algorithm = Algorithm.HMAC512(token);
                    algorithmMap.put(token, algorithm);
                }
            }
        }
        return algorithm;
    }
}
