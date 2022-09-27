package jdbc.b2;

import org.junit.jupiter.api.Test;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class JdbcFinal {
    public static void main(String[] args) {
    }

    @Test
    public void testConnect() throws Exception {
        //创建properties对象用于接收配置文件
        Properties properties = new Properties();
        //加载配置文件
        properties.load(new FileInputStream("D:\\myjava\\src\\jdbc\\b2\\connet.properties"));
        //获取配置信息
        String url = properties.getProperty("url");
        String user = properties.getProperty("user");
        String password = properties.getProperty("password");
        String driver = properties.getProperty("driver");
        //通过反射注册驱动
        Class.forName(driver);
        //得到连接
        Connection connection = DriverManager.getConnection(url, user, password);

        System.out.println(connection);

    }
}

