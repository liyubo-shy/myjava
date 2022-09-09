package basictype.io;

import java.io.*;

/**
 *反序列化
 */
public class ObjectInPutStreamTest {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        File path = new File("d:\\序列化.txt");
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(path));
        //要按照序列的顺序来反序列化
        System.out.println(ois.readInt());
        Object dog = ois.readObject();
        System.out.println(dog);
        ois.close();
    }
}
