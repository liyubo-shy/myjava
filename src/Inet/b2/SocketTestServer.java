package Inet.b2;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketTestServer {
    public static void main(String[] args) throws IOException {
        //创建ServerSocket，监听8848端口
        ServerSocket serverSocket = new ServerSocket(8848);
        System.out.println("服务端正在等待连接...");
        //连接成功创建socket对象
        Socket socket = serverSocket.accept();
        System.out.println("连接成功！");
        //通过socket获取跟该连接关联的流对象
        InputStream inputStream = socket.getInputStream();
        //开始读取客户端数据
        byte[] buf = new  byte[1024];
        int len = 0;
        while ((len = inputStream.read(buf)) != -1){
            System.out.println(new String(buf,0,len));
        }
        //返回信息
        OutputStream outputStream = socket.getOutputStream();
        outputStream.write("hello,ppt".getBytes());
        //输出结束标语
        socket.shutdownOutput();
        //关闭资源
        serverSocket.close();
        inputStream.close();
        socket.close();
    }
}
