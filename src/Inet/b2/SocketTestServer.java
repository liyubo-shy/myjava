package Inet.b2;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketTestServer {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(8848);
        System.out.println("服务端正在等待连接...");
        Socket socket = serverSocket.accept();
        System.out.println("连接成功！");
        InputStream inputStream = socket.getInputStream();
        byte[] buf = new  byte[1024];
        int len = 0;
        while ((len = inputStream.read(buf)) != -1){
            System.out.println(new String(buf,0,len));
        }
        serverSocket.close();
        inputStream.close();
        socket.close();
    }
}
