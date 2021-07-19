package beSen.test.controller;

import beSen.spring.model.Coffee;
import beSen.spring.valid.ResponseEntity;
import beSen.spring.valid.ValidExceptionUtil;
import cn.hutool.json.JSONUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
import java.time.LocalDateTime;

/**
 * @author 康盼Java开发工程师
 */
@Controller
@RequestMapping("/coffee")
public class CoffeeController {

    /**
     * 测试
     * {"coffeeDesc":"九折出售","price":0,"state":0,"id":0}
     *
     * @param coffee
     * @param bindingResult
     * @return
     */
    @PostMapping(path = "/insert",consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity insert(@RequestBody @Valid Coffee coffee, BindingResult bindingResult) {
        // 两种校验对比
        ValidExceptionUtil.validField(coffee);
        ValidExceptionUtil.validation(bindingResult.hasErrors(), bindingResult);
        return ResponseEntity.builder().data(coffee)
                .time(LocalDateTime.now())
                .status(HttpStatus.OK.value())
                .message("insert is ok")
                .build();
    }

    /**
     * 获取测试参数
     * {"coffeeDesc":"九折出售","price":100,"name":"摩卡","state":100,"id":0}
     *
     * @param args
     */
    public static void main(String[] args) {
        Coffee coffee = new Coffee("摩卡",100,100.00,"九折出售");
        System.out.println(JSONUtil.toJsonStr(coffee));
    }
}
