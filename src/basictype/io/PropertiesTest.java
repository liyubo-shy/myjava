package basictype.io;

import java.io.*;
import java.util.Properties;

public class PropertiesTest {
    public static void main(String[] args) throws IOException {
        String path = "pro.properties";
        Properties pro = new Properties();
        pro.load(new FileReader(path));
        System.out.println(pro.getProperty("username"));

    }
}
