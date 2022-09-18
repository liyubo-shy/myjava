package Inet.b2;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;

public class SocketTestClient {
    public static void main(String[] args) throws IOException {
        //连接本机的8848端口服务，连接成功创建socket
        Socket socket = new Socket(InetAddress.getLocalHost(), 8848);
        System.out.println("连接服务端成功！");
        //通过socket获取跟该连接关联的流对象
        OutputStream outputStream = socket.getOutputStream();
        //输出数据
        outputStream.write("hello excel".getBytes());
        //关闭资源
        outputStream.close();
        socket.close();
        System.out.println("退出连接");
    }
}
