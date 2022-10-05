package basictype.io;

import java.io.*;

/**
 * 序列化输出
 */

public class ObjectOutPutStreamTest {
    public static void main(String[] args) throws IOException {
        File path = new File("D:\\序列化.txt");
        Dog mi = new Dog("  mi", 12);
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(path));
        oos.writeInt(100);
        oos.writeObject(mi);

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

    @Override
    public String toString() {
        return "Dog{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}