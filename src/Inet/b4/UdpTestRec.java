package Inet.b4;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

/**
 * 使用UDP协议传输数据，接收端
 */
public class UdpTestRec {
    public static void main(String[] args) throws IOException {
        //创建DatagramSocket对象，在3569端口监听
        DatagramSocket socket = new DatagramSocket(3569);
        //创建字节数组用于读取接收数据，并装包为packet对象
        byte[] buf = new byte[1024];
        DatagramPacket packet = new DatagramPacket(buf,buf.length);
        System.out.println("监听中...");
        //监听到数据放入packet中
        socket.receive(packet);
        //拆封
        byte[] data = packet.getData();
        int len = packet.getLength();
        //输出
        String s = new String(data,0,len) ;
        System.out.println(s);
        socket.close();

    }
}
