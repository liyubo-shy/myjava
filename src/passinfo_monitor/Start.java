package passinfo_monitor;


/**
 * @Author: bo
 * @DATE: 2022/10/8 2:54
 *过点信息监控程序启动类
 **/
@SuppressWarnings("all")
public class Start {
    public static void main(String[] args) {
        //创建对象
        MonitorRunning monitorRunning = new MonitorRunning();
        DoFlag doFlag = new DoFlag();
        //创建线程
        Thread thread1 = new Thread(monitorRunning);
        Thread thread2 = new Thread(doFlag);
        //启动线程
        thread1.start();
        thread2.start();
    }
}
