package passinfo_monitor;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

/**
 * @Author: bo
 * @DATE: 2022/10/8 3:01
 * 持续等待键盘输出,输入q,Q时结束程序运行,并输出运行时长
 **/
@SuppressWarnings("all")
public class DoFlag implements Runnable{
    @Override
    public void run() {
        System.out.println("\033[31m" +"程序开始运行,键盘输入任意字符退出程序");
        System.out.println("\033[31m" +"程序开始运行,键盘输入任意字符退出程序");
        System.out.println("\033[31m" +"程序开始运行,键盘输入任意字符退出程序");
        //定义时间格式
        DateFormat dateFormat = new SimpleDateFormat("HH时:mm分:ss秒");
        //记录程序开启时间
        long start = System.currentTimeMillis();

        Scanner scanner = new Scanner(System.in);
        while (true) {
            //开启键盘录入
            String s = scanner.nextLine();

            //键盘录入Q或q时,改变flag,结束程序
            if (s!=null) {
                //记录程序结束时间
                long end = System.currentTimeMillis();
                Date date = new Date(end-start-28800000);   //减去时区的8小时,28800000
                //格式化时间
                String format = dateFormat.format(date);
                //输出
                System.out.println("本次程序运行时长:"+format);
                //退出程序
                System.exit(0);
            }
        }
    }
}
