package beSen.upc.controller;

import beSen.upc.service.I18nService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/i18n")
public class I18nController {

    @Autowired private I18nService i18nService;

    @GetMapping("/getLanguage")
    public String getLanguage() throws Exception{
        return i18nService.getLanguage();
    }
}
