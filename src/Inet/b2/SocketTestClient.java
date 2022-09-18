package Inet.b2;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;

public class SocketTestClient {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket(InetAddress.getLocalHost(), 8848);
        System.out.println("连接服务端成功！");
        OutputStream outputStream = socket.getOutputStream();
        outputStream.write("hello excel".getBytes());
        outputStream.close();
        socket.close();
        System.out.println("退出连接");
    }
}
