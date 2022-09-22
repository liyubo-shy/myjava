package Inet.b3;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;

public class SocketTestClient01 {
    public static void main(String[] args) throws IOException {
        //连接本机的8848端口服务，连接成功创建socket
        Socket socket = new Socket(InetAddress.getLocalHost(), 9527);
        System.out.println("连接服务端成功！");
        //通过socket获取跟该连接关联的流对象
        OutputStream outputStream = socket.getOutputStream();
        FileInputStream fileInputStream = new FileInputStream("C:\\Users\\lian'xiang\\Pictures\\meimei.jpg");
        byte[] buf = new byte[1024];
        int len = 0;
        while ((len = fileInputStream.read(buf)) != -1) {
            outputStream.write(buf, 0, len);
        }
        //关闭资源
        outputStream.close();
        socket.close();
        System.out.println("退出连接");
    }
}
