package beSen.test.controller;

import beSen.jwt.Hash;
import beSen.jwt.JwtToken;
import beSen.jwt.Sign;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author 康盼Java开发工程师
 */

@RestController
public class JwtController {


    @JwtToken
    @GetMapping("/getMessage")
    public String getMessage() {
        return "hello world";
    }

    @GetMapping("/login")
    public String login() {
        // 假设登录成功，数据来源数据库 (362426199100000,康盼,18,男,123456)
        Map<String,String> map = new HashMap<>(10);
        map.put("name","康盼");
        map.put("age","18");
        map.put("sex","男");
        String password = Hash.encode("password","123456");
        String token = Sign.generateToken(password,map,10L,"362426199100000");
        return token;
    }
}
