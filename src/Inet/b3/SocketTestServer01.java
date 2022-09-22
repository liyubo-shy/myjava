package Inet.b3;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 网络传输图片
 */
public class SocketTestServer01 {
    public static void main(String[] args) throws IOException {
        //创建ServerSocket，监听8848端口
        ServerSocket serverSocket = new ServerSocket(9527);
        System.out.println("服务端正在等待连接...");
        //连接成功创建socket对象
        Socket socket = serverSocket.accept();
        System.out.println("连接成功！");
        //通过socket获取跟该连接关联的流对象
        InputStream inputStream = socket.getInputStream();
        FileOutputStream fileOutputStream = new FileOutputStream("C:\\Users\\lian'xiang\\Pictures\\xiaoxiao.jpg");
        byte[] buf = new byte[1024];
        int len = 0;
        while ((len = inputStream.read(buf)) != -1) {
            fileOutputStream.write(buf, 0, len);
        }
        //关闭资源
        serverSocket.close();
        inputStream.close();
        socket.close();
    }
}
