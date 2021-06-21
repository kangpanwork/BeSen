package beSen.mysql;

import beSen.pool.ObjectFactory;

import java.sql.Connection;

public class JDBCObjectFactory implements ObjectFactory<Connection> {

    private MysqlConConfig mysqlConConfig;

    public JDBCObjectFactory(MysqlConConfig mysqlConConfig) {
        this.mysqlConConfig = mysqlConConfig;
    }

    @Override
    public Connection createNewObject() {
        try {
            return mysqlConConfig.getConnection();
        } catch (Exception exception) {
            throw new IllegalArgumentException("create new connection has some problem,pls check your argument");
        }
    }
}
