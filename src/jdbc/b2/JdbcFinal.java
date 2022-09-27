package jdbc.b2;

import org.junit.jupiter.api.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;
import java.util.Scanner;

public class JdbcFinal {
    public static void main(String[] args) throws Exception {
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
        //输入账号密码
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入用户名：");
        String inUser = scanner.nextLine();
        System.out.println("请输入密码：");
        String inPassword = scanner.nextLine();
        //定义匹配账号密码的Sql
        String selectSql = "SELECT * FROM sys_user WHERE USER = ? AND PASSWORD = ?";
        //得到与sql关联的prepareStatement对象
        PreparedStatement preparedStatement = connection.prepareStatement(selectSql);
        //给sql中的?赋值
        preparedStatement.setString(1, inUser);
        preparedStatement.setString(2, inPassword);
        //得到查询的结果集，匹配结果
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            System.out.println("登录成功~");
        } else {
            System.out.println("登录失败！");
        }
        //关闭资源
        resultSet.close();
        preparedStatement.close();
        connection.close();

    }
}




