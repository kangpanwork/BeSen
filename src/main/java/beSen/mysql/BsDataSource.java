package beSen.mysql;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.lang.NonNull;


/**
 * 配置数据源
 */
@Configuration("dataSource")
public class BsDataSource extends DriverManagerDataSource {

    @Override
    @Value("${mysql.url}")
    @NonNull
    public void setUrl(String url) {
        super.setUrl(url);
    }

    @Override
    @Value("${mysql.user}")
    @NonNull
    public void setUsername(String username) {
        super.setUsername(username);
    }

    @Override
    @Value("${mysql.password}")
    @NonNull
    public String getPassword() {
        return super.getPassword();
    }
}
