package beSen.jwt;

import cn.hutool.core.util.StrUtil;
import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.util.CollectionUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.List;

/**
 * @author 康盼Java开发工程师
 */
public class JwtInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 如果不是映射到方法直接通过
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();
        // java.lang.Class类的isAnnotationPresent()方法用于检查此类中是否存在指定注释类型的注释
        if(method.isAnnotationPresent(JwtToken.class)) {
            JwtToken jwtToken = method.getAnnotation(JwtToken.class);
            if (jwtToken != null) {
                // 请求头中获取token
                String token = request.getHeader("token");
                if (StrUtil.isEmpty(token)) {
                    throw new RuntimeException("请重新登录");
                }
                // 获取用户ID
                String id = "";
                List<String> list = JWT.decode(token).getAudience();
                if(!CollectionUtils.isEmpty(list)) {
                    id = list.get(0);
                }
                // 根据 ID 查询数据库 获取对应的password
                String password = Hash.encode("password","123456");
                // 通过password获取其它信息
                DecodedJWT decodedJWT = Sign.verifyToken(token,password);
                String name = decodedJWT.getClaims().get("name").asString();
                String age = decodedJWT.getClaims().get("age").asString();
                String sex = decodedJWT.getClaims().get("sex").asString();
                // 验证数据库中的数据是否一致
            }

        }
        return true;
    }
}
