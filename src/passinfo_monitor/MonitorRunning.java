package passinfo_monitor;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Author: bo
 * @DATE: 2022/10/8 2:53
 * 监控主方法
 **/
@SuppressWarnings("all")
public class MonitorRunning implements Runnable {
    @Override
    public void run() {

        //休眠10ms,让另一个线程先打印出提示
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //获得连接
        Connection connection = JDBCUtils.getConnection();
        //编写Sql
        String sql = "SELECT " +
                "FUNC_NAME," +
                "B.IT_NAME," +
                "DECODE(A.STATUS, '0','未处理','1','已处理','3','处理失败' ) 处理状态," +
                "COUNT(*) 数量," +
                "MIN(A.CREATED_DATE) 最早时间," +
                "MAX(A.CREATED_DATE) 最晚时间 " +
                "FROM WMS.MM_INTERFACE_DATA A " +
                "LEFT JOIN WMS.MM_INTERFACE_CODE_DEF B ON A.FUNC_NAME = B.IT_CODE " +
                "WHERE A.STATUS IN ('0','3') " +
                "GROUP BY " +
                "A.FUNC_NAME," +
                "B.IT_NAME," +
                "A.STATUS";
        //i用于计数
        int i = 1;
        //定义输出时间格式
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //循环查询

        while (true) {
            //得到与Sql关联的statement并执行sql查询
            PreparedStatement preparedStatement = null;
            ResultSet resultSet = null;
            try {
                preparedStatement = connection.prepareStatement(sql);
                resultSet = preparedStatement.executeQuery();
            } catch (SQLException e) {
                e.printStackTrace();
            }

            System.out.print("\033[m" + "第" + i + "次查询：    ");
            //获取当前时间
            String time = dateFormat.format(new Date());
            System.out.println("\033[34m" + time);

            //循环读取查询结果
            while (true) {
                try {
                    if (!resultSet.next()) break;
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                //从resultSet中获得列数据
                String funcName = null;
                String itName = null;
                String count = null;
                try {
                    funcName = resultSet.getString("FUNC_NAME");
                    itName = resultSet.getString("IT_NAME");
                    count = resultSet.getString("数量");
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                //输出控制台,数量大于5以红色显示
                if ((Integer.parseInt(count)) > 5) {
                    System.out.println("\033[31m" + itName + "   数量：" + count);
                } else {
                    System.out.println("\033[m" + itName + "   数量：" + count);
                }

                //写入日志文件
                LogWriter.logWriter(funcName, itName, count, time);

                //设置监控，当VehicOverPointInfo_AM0D未处理的数量达到5或OutSyncTicket_AW0B达到50时发出告警
                if (
                        funcName.equals("VehicOverPointInfo_AM0D") & ((Integer.parseInt(count)) > 5) ||
                                funcName.equals("OutSyncTicket_AW0B") & ((Integer.parseInt(count)) > 50)
                ) {
                    //提示音
                    java.awt.Toolkit.getDefaultToolkit().beep();
                    //弹出窗口
                    new Draw2();
                    break;
                }
            }
            System.out.println("\033[m" + "-------------------------------");
            i++;
            //每次读取完查询结果则关闭游标和结果集，下次循环重新生成
            JDBCUtils.closeRec(preparedStatement, resultSet);

            //休眠1分钟后再查询
            try {
                Thread.sleep(60000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
