package beSen.mysql;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.Configuration;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * oracle的
 * 备注：忘记数据库账号和密码怎么处理？
 * sqlplus /nolog
 * conn / as sysdba
 * select t.username,t.password from dba_users t where t.username = 'KANGPAN';
 * KANGPAN
 * 963CE51711BBC3C4
 * alter user KANGPAN identified by kangpan;
 * 查询用户创建的表
 * select table_name from user_tables;
 * 查看 service Name
 * SHOW PARAMETER service_names;
 */
@Configuration
@ConditionalOnBean(MysqlConConfig.class)
public class BsMysql implements SimpleExecutor{

    /**
     * 我想这里就初始化了
     */
    private BsSimpleExecutor bsSimpleExecutor;

    @Autowired
    private MysqlConConfig mysqlConConfig;

    @Override
    public void update(String sql, Object... args) throws SQLException {
        bsSimpleExecutor.update(sql, args);
    }

    @Override
    public <T> List<T> query(String sql, Class<T> cls, Object... args) throws Exception {
        return bsSimpleExecutor.query(sql, cls, args);
    }

    @Override
    public int count(String sql, Object... args) throws SQLException {
        return bsSimpleExecutor.count(sql, args);
    }

    private class BsSimpleExecutor extends AbstractSimpleExecutor {
        @Override
        public void setConnection(Connection connection) {
            super.setConnection(connection);
        }
    }

    // 本来想这里注入 Bean 的，但是 mysqlConConfig 为 null;
    // 之前使用的是 @Bean 注解，用属性构造解决了
    @Autowired
    public void setBsSimpleExecutor() throws Exception{
        if (bsSimpleExecutor == null) {
            synchronized (BsSimpleExecutor.class) {
                if (bsSimpleExecutor == null) {
                    bsSimpleExecutor = new BsSimpleExecutor();
                    bsSimpleExecutor.setConnection(mysqlConConfig.getConnection());
                }
            }
        }
    }
 }