package jdbc.b1;

import com.mysql.jdbc.Driver;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class JdbcTest {
    public static void main(String[] args) throws SQLException {
        //注册驱动
        Driver driver = new Driver();
        //定义连接的url，包括ip，端口，数据库名
        String url = "jdbc:mysql://localhost:3306/bobo";
        //定义配置对象，配置好用户名和密码
        Properties properties = new Properties();
        properties.setProperty("user","root");
        properties.setProperty("password","qiongbb");
        //获取连接
        Connection connect = driver.connect(url, properties);
        //定义Sql语句
        String sql = "UPDATE emp SET job = '吃苦瓜' WHERE empno = '100006'";
        //statement对象用于发送Sql到数据库执行
        Statement statement = connect.createStatement();
        //返回的是受影响的行数
        int rows = statement.executeUpdate(sql);
        System.out.println(rows);
        //关闭资源
        statement.close();
        connect.close();
    }
}
