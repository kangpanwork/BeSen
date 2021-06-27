package beSen.upc.controller;

import beSen.bsSimpleServer.HttpClient.BsHttpClient;
import beSen.bsSimpleServer.HttpClient.RequestEntity;
import beSen.upc.service.I18nService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/i18n")
public class I18nController {

    @Autowired
    private I18nService i18nService;

    @GetMapping("/getLanguage")
    public String getLanguage() throws Exception {
        return i18nService.getLanguage();
    }

    /**
     * 使用 httpclient 模拟RPC调用
     *
     * @return
     * @throws Exception
     */
    @GetMapping("/getLanguageByHttpClient")
    public String getLanguageByHttpClient() throws Exception {
        RequestEntity requestEntity = new RequestEntity("http://localhost:8080/i18n/getLanguage","","get","");
        BsHttpClient httpClient = new BsHttpClient(requestEntity);
        httpClient.service();
        String responseResult = requestEntity.getResponseResult();
        return responseResult;
    }
}
