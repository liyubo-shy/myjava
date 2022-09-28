package jdbc.b4;


import com.alibaba.druid.pool.DruidDataSourceFactory;
import jdbc.JDBCUtilsDruid;
import org.junit.jupiter.api.Test;

import javax.sql.DataSource;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

/**
 * 使用德鲁伊连接池
 */
public class DruidTest {
    public static void main(String[] args) throws Exception {
        Properties properties = new Properties();
        properties.load(new FileInputStream("src\\druid.properties"));

        DataSource dataSource = DruidDataSourceFactory.createDataSource(properties);
        System.out.println("开始连接");
        long start = System.currentTimeMillis();
        for (int i = 0; i < 5000000; i++) {
            Connection connection = dataSource.getConnection();
            connection.close();
        }
        long end = System.currentTimeMillis();
        System.out.println("连接5000000次耗时:" + (end - start));
    }

    /**
     * 使用工具类
     */
    @Test
    public void DruidTest2() throws SQLException {
        System.out.println("开始连接..");
        long start = System.currentTimeMillis();
        for (int i = 0; i < 5000000; i++) {
            Connection connection = JDBCUtilsDruid.getConnection();
            JDBCUtilsDruid.closeRec(connection);
        }
        long end = System.currentTimeMillis();
        System.out.println("连接5000000次耗时:" + (end - start));
    }

}
