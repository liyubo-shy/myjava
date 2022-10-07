package passinfo_monitor;


import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Author: bo
 * @DATE: 2022/10/8 2:06
 * 写日志工具类
 **/
public class LogWriter {
    //定义文件名时间格式
    public static SimpleDateFormat df = new SimpleDateFormat("yyyy_MM_dd");
    public static BufferedWriter bufferedWriter = null;

    public static void logWriter(String name, String itName, String count, String time) {
        try {
            String path = "D:\\myjava\\src\\passinfo_monitor\\Log\\" + df.format(new Date()) + "_PassInfoMonitor.log";
            bufferedWriter = new BufferedWriter(new FileWriter(path, true));
            bufferedWriter.newLine();  //每次开始写入前先换行
            bufferedWriter.write(time + "    ");
            bufferedWriter.write(name + " " + itName + "{数量:" + count + "}  ");
            bufferedWriter.flush();    //使写入生效
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (bufferedWriter != null) {
                try {
                    bufferedWriter.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
