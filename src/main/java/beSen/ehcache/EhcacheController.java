package beSen.ehcache;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * @author 康盼Java开发工程师
 */
@RequestMapping("/cache")
@RestController
public class EhcacheController {

    @Autowired
    private BsEhcache bsEhcache;

    @PostMapping(path = "/put",consumes = MediaType.APPLICATION_JSON_VALUE)
    public void putCache(@RequestBody keyValue keyValue) {
        bsEhcache.put(Ehcache.CACHE_NAME,keyValue.getKey(), keyValue.getValue());
    }

    @PostMapping(path = "/get",consumes = MediaType.APPLICATION_JSON_VALUE)
    public String getCache(@RequestBody keyValue keyValue) {
        return bsEhcache.get(Ehcache.CACHE_NAME,keyValue.getKey());
    }

}
