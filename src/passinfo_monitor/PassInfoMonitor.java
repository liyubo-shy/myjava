package passinfo_monitor;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * @Author: bo
 * @DATE: 2022/10/5 21:13
 **/
public class PassInfoMonitor {
    public static void main(String[] args) throws SQLException, InterruptedException, IOException {
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
        //定义文件名时间格式
        SimpleDateFormat df = new SimpleDateFormat("yyyy_MM_dd");
        //循环查询
        while (true) {
            //创建输出流，追加模式
            String path = "D:\\myjava\\src\\passinfo_monitor\\Log\\"+df.format(new Date())+"_PassInfoMonitor.log";
            FileWriter fileWriter = new FileWriter(path,true);
            BufferedWriter bfw = new BufferedWriter(fileWriter);
            //得到与Sql关联的statement并执行sql查询
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();

            System.out.print("第" + i + "次查询：    ");
            //获取当前时间
            String time = dateFormat.format(new Date());
            System.out.println(time);
            //循环读取查询结果
            while (resultSet.next()) {
                //从resultSet中获得列数据
                String funcName = resultSet.getString("FUNC_NAME");
                String count = resultSet.getString("数量");
                //输出控制台
                System.out.println(funcName + "   数量：" + count);
                //写入日志文件
                bfw.newLine();  //每次开始写入前先换行
                bfw.write(time+"    ");
                bfw.write("\\033[44m"+funcName + "{数量:" + count+"}  ");
                bfw.flush();    //使写入生效

                //设置监控，当VehicOverPointInfo_AM0D未处理的数量达到5时发出告警
                if ((funcName.equals("OrderTheOrder_AP0L")||funcName.equals("OutSyncTicket_AW0B")) & ((Integer.parseInt(count)) > 2)) {
                    //提示音
                    java.awt.Toolkit.getDefaultToolkit().beep();
                    //弹出窗口
                    new Draw2();
                    break;
                }
            }
            System.out.println("-------------------------");
            //查询间隔
            TimeUnit.MINUTES.sleep(1);
            i++;
        }
    }
}
