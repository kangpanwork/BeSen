package beSen.mysql;

import java.sql.SQLException;
import java.util.List;

public interface SimpleExecutor {

    void update(String sql, Object... args) throws SQLException;

    <T> List<T> query(String sql, Class<T> cls, Object... args) throws Exception;

    int count(String sql, Object... args) throws SQLException;

}
