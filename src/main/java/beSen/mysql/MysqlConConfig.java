package beSen.mysql;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * mysql 配置
 */
@Configuration
@Data
public class MysqlConConfig {
    @Value("${mysql.className}")
    private String className;
    @Value("${mysql.url}")
    private String url;
    @Value("${mysql.user}")
    private String user;
    @Value("${mysql.password}")
    private String password;

    public Connection getConnection() throws Exception {
        Class.forName(className);
        return DriverManager.getConnection(url, user, password);
    }
}
