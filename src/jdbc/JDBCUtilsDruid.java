package jdbc;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

/**
 * 德鲁伊连接工具类
 */
public class JDBCUtilsDruid {
    private static DataSource dataSource;

    static {
        try {
            Properties properties = new Properties();
            properties.load(new FileInputStream("src\\druid.properties"));
            dataSource = DruidDataSourceFactory.createDataSource(properties);
        } catch (Exception e) {
            throw  new RuntimeException(e);
        }
    }

    public static Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }
    //关闭资源
    public static void closeRec(ResultSet set, Statement statement, Connection connection) {
        try {
            set.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            throw  new RuntimeException(e);
        }
    }
    //方法重载
    public static void closeRec( Statement statement, Connection connection) {
        try {
            statement.close();
            connection.close();
        } catch (SQLException e) {
            throw  new RuntimeException(e);
        }
    }
    //方法重载
    public static void closeRec( Connection connection) {
        try {
            connection.close();
        } catch (SQLException e) {
            throw  new RuntimeException(e);
        }
    }
}
