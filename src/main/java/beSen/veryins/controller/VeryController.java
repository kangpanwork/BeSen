package beSen.veryins.controller;

import beSen.veryins.model.Photo;
import beSen.veryins.service.VeryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("very")
@Slf4j
public class VeryController {

    @Autowired
    private VeryService veryService;

    @GetMapping("/getPhotoList/{category}")
    public List<Photo> getPhotoList(@PathVariable("category") String category) throws Exception {
        List<Photo> result = veryService.queryPhotoByCategory(category);
        log.info("result:{}",result);
        return result;
    }

    @GetMapping("/requestUrl/{category}")
    public String requestUrl(@PathVariable("category") String category) throws Exception {
        String result = veryService.requestUrl(category);
        log.info("result:{}",result);
        return result;
    }
}
