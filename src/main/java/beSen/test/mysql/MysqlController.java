package beSen.test.mysql;

import beSen.mysql.BsMysql;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/mysql")
public class MysqlController {

    @Autowired
    private BsMysql bsMysql;

    @GetMapping("/test")
    public int test() throws Exception {
        int result = bsMysql.count("SELECT count(1) FROM kangpan.t_picture2;",null);
        return result;
    }
}
