package basictype.io;

import java.io.*;

/**
 * 使用包装流
 */

public class BufferedTest {
    public static void main(String[] args) throws IOException {
        File path = new File("D:\\idea破解\\1653399923-68844c084bad353\\使用前必读.txt");
        BufferedReader bufferedReader = new BufferedReader(new FileReader(path));
        //普通字符读取
        /*
        int cha;
        while ((cha =bufferedReader.read()) != -1){
            System.out.print((char)cha);
        }
        */

        //按行读取,返回值是String,读到文本末尾时返回null
        String str ;
        while ((str = bufferedReader.readLine())!= null){
            System.out.println(str);
        }

        bufferedReader.close();
    }
}
