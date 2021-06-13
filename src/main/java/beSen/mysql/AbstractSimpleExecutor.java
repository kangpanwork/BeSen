package beSen.mysql;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractSimpleExecutor implements SimpleExecutor {

    private Connection connection;

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void update(String sql, Object... args) throws SQLException {
        PreparedStatement preparedStatement = preparedStatement(sql, args);
        preparedStatement.executeUpdate();
    }

    @Override
    public <T> List<T> query(String sql, Class<T> cls, Object... args) throws Exception {
        List<T> list = new ArrayList<>();
        PreparedStatement preparedStatement = preparedStatement(sql, args);
        try (ResultSet resultSet = preparedStatement.executeQuery()) {
            ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
            int columnCount = resultSetMetaData.getColumnCount();
            if (resultSet.next()) {
                T obj = cls.newInstance();
                for (int i = 1; i < columnCount; ++i) {
                    Object value = resultSet.getObject(i);
                    String columnName = resultSetMetaData.getColumnName(i).toLowerCase();
                    if (value == null) {
                        continue;
                    }
                    if (hasFields(cls, columnName)) {
                        Field field = cls.getDeclaredField(columnName);
                        field.setAccessible(true);
                        field.set(obj, value);
                    }
                }
                list.add(obj);
            }
        }
        return list;
    }

    @Override
    public int count(String sql, Object... args) throws SQLException {
        PreparedStatement preparedStatement = preparedStatement(sql, args);
        try (ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                return resultSet.getInt(1);
            }
        }
        return 0;
    }

    private PreparedStatement preparedStatement(String sql, Object... args) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        if (args != null) {
            for (int i = 0; i < args.length; ++i) {
                preparedStatement.setObject(i + 1, args[i]);
            }
        }
        return preparedStatement;
    }

    private <T> boolean hasFields(Class<T> cls, String columnName) {
        Field[] fields = cls.getDeclaredFields();
        for (int i = 0; i < fields.length; ++i) {
            if (columnName.equals(fields[i].getName())) {
                return true;
            }
        }
        return false;
    }
}
