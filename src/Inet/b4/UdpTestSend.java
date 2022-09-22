package Inet.b4;

import java.io.IOException;
import java.net.*;

/**
 * 使用UDP协议传输数据，发送端
 */
public class UdpTestSend {
    public static void main(String[] args) throws IOException {
        //创建DatagramSocket对象，在3568端口监听
        DatagramSocket socket = new DatagramSocket(3568);
        //把数据装包，目标IP为本机地址，端口号3569
        byte[] data = "hello".getBytes();
        DatagramPacket packet = new DatagramPacket(data, data.length, InetAddress.getLocalHost(),3569);
        //发送packet
        socket.send(packet);
        //关闭资源
        socket.close();
    }
}
