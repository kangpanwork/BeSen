package beSen.test.c3p0;

import com.mchange.v2.c3p0.C3P0ProxyConnection;
import com.mchange.v2.c3p0.DataSources;

import javax.sql.DataSource;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class C3p0Test {
    public static void main(String[] args) throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        DataSource unPooledDataSource = DataSources.unpooledDataSource("jdbc:mysql://localhost:3306/kangpan?useSSL=false&useUnicode=true&characterEncoding=utf8&serverTimezone=UTC&useAffectedRows=true&allowMultiQueries=true", "root", "");
        DataSource dataSource = DataSources.pooledDataSource(unPooledDataSource);
        Connection connection = dataSource.getConnection(); // 连接池中获取的是代理的连接
        // com.mchange.v2.c3p0.impl.NewProxyConnection
        System.out.println(connection.getClass().getName());
        Field f1 = connection.getClass().getDeclaredField("inner");
//        public final class NewProxyConnection implements Connection, C3P0ProxyConnection {
//            protected Connection inner;
//        }
        f1.setAccessible(true);
        Object o1 = f1.get(connection); // 取得内部包装的 connection 真实的连接
        // com.mysql.cj.jdbc.ConnectionImpl
        System.out.println(o1.getClass().getName());
        try (Statement statement = connection.createStatement()) {
            ResultSet rs = statement.executeQuery("SELECT count(1) FROM kangpan.t_picture2;");
            while (rs.next()) {
                System.out.println(rs.getString(1));
            }
            rs.close();
        }
        connection.close();
        Thread.sleep(1000);// 等待连接池返回池中
        connection = dataSource.getConnection();
        System.out.println(connection.getClass().getName());
        Field f2 = connection.getClass().getDeclaredField("inner");
        f2.setAccessible(true);
        Object o2 = f2.get(connection);
        if (o1 == o2) { // 连接池复用
            System.out.println(o2.getClass().getName());
            try (Statement statement = connection.createStatement()) {
                ResultSet rs = statement.executeQuery("SELECT count(1) FROM kangpan.t_picture2;");
                while (rs.next()) {
                    System.out.println(rs.getString(1));
                }
                rs.close();
            }
        }
        connection.close();

    }
}
