package jdbc.b3;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import jdbc.JDBCUtils;
import org.junit.jupiter.api.Test;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.SQLException;

public class C3P0Test {
    /**
     * 测试使用传统jdbc连接数据库5000次所用的时间  --7117
     */
    public static void main(String[] args) throws SQLException {
        System.out.println("开始连接");
        long start = System.currentTimeMillis();
        for (int i = 0; i < 5000; i++) {
            //使用JDBC工具类获取connection
            Connection connection = JDBCUtils.getConnection();
            JDBCUtils.closeRec( connection);
        }
        long end = System.currentTimeMillis();
        System.out.println("耗时：" + (end - start));


    }

    @Test
    /**
     * 使用C3P0连接池连接5000次  --325
     */
    public void C3P0_1() throws PropertyVetoException, SQLException {
        //创建C3P0对象
        ComboPooledDataSource comboPooledDataSource = new ComboPooledDataSource();
        //配置C3P0连接池的参数,这里使用JDBC工具类获取参数
        //连接四件套
        comboPooledDataSource.setUser(JDBCUtils.getUser());
        comboPooledDataSource.setPassword(JDBCUtils.getPsw());
        comboPooledDataSource.setDriverClass(JDBCUtils.getDriver());
        comboPooledDataSource.setJdbcUrl(JDBCUtils.getUrl());
        //初始化连接数
        comboPooledDataSource.setInitialPoolSize(10);
        //连接池最大连接数
        comboPooledDataSource.setMaxPoolSize(50);


        System.out.println("开始连接");
        long start = System.currentTimeMillis();
        for (int i = 0; i < 5000; i++) {
            Connection connection = comboPooledDataSource.getConnection();
            JDBCUtils.closeRec(connection);
        }
        long end = System.currentTimeMillis();
        System.out.println("连接5000次耗时：" + (end - start));
    }
}
