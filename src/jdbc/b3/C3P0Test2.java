package jdbc.b3;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * 使用xml配置文件连接c3p0
 */
public class C3P0Test2 {
    public static void main(String[] args) throws SQLException {
        ComboPooledDataSource comboPooledDataSource =
                new ComboPooledDataSource("bo_c3p0");
        long start = System.currentTimeMillis();
        for (int i = 0; i < 5000; i++) {
            Connection connection = comboPooledDataSource.getConnection();
            connection.close();
        }
        long end = System.currentTimeMillis();
        System.out.println("连接耗时：" + (end - start));
    }
}
