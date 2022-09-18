package Inet.b1;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class InetTest {
    public static void main(String[] args) throws UnknownHostException {
        InetAddress localHost = InetAddress.getByName("www.baidu.com");
        InetAddress localHost1 = InetAddress.getLocalHost();
        System.out.println(localHost);
        System.out.println(localHost1);
    }
}
