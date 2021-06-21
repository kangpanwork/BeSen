package beSen.mysql;

import beSen.pool.Pool;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * 校验器来验证数据库连接是否有效
 */
public class JDBCConnectionValidator implements Pool.Validator<Connection> {
    @Override
    public boolean isValid(Connection connection) {
        if (connection == null) {
            return false;
        }
        try {
            return !connection.isClosed();
        } catch (SQLException sqlException) {
            return false;
        }
    }

    @Override
    public void invalidate(Connection connection) {
        try {
            connection.close();
        } catch (SQLException sqlException) {
        }
    }
}
