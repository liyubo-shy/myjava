package basictype.io;

import java.io.*;

/**
 * 序列化输出
 */

public class ObjectOutPutStreamTest {
    public static void main(String[] args) throws IOException {
        File path = new File("D:\\序列化.txt");
        Dog kity = new Dog("kity", 12);
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(path));
        oos.write(100);
        oos.writeObject(kity);

        oos.close();
    }
}

class Dog implements Serializable{
    private String name;
    private int age;

    public Dog() {
    }

    public Dog(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}