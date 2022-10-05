package jdbc;


import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

/**
 * JDBC连接工具类\\mysql
 */

public class JDBCUtils {
    private static String user;
    private static String psw;
    private static String url;
    private static String driver;

    //静态代码块，获取配置文件的信息
    static {
        try {
            Properties properties = new Properties();
            properties.load(new FileInputStream("D:\\myjava\\src\\jdbc\\connet.properties"));
            user = properties.getProperty("user");
            psw = properties.getProperty("psw");
            url = properties.getProperty("url");
            driver = properties.getProperty("driver");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    //获取connection
    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(url, user, psw);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
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

    public static String getUser() {
        return user;
    }

    public static String getPsw() {
        return psw;
    }

    public static String getUrl() {
        return url;
    }

    public static String getDriver() {
        return driver;
    }
}
