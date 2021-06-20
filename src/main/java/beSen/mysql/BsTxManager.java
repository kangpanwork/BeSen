package beSen.mysql;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

/**
 * 配置事务管理
 */
@Component
public class BsTxManager extends DataSourceTransactionManager {

    @Override
    @Autowired
    public void setDataSource(DataSource dataSource) {
        super.setDataSource(dataSource);
    }
}
