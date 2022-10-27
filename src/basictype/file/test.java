package basictype.file;

/**
 * @Author: bo
 * @DATE: 2022/10/13 0:53
 **/

import java.io.File;

import java.io.FileOutputStream;
import java.io.IOException;

import java.util.ArrayList;


/**
 * Created with IntelliJ IDEA.
 *
 * @Author: bo
 * @Date: 2022/08/12/23:23
 * @Description:
 */
public class test {
    public static void main(String[] args) throws IOException {
        long l1 = System.currentTimeMillis();
        ArrayList<String> list = new ArrayList<>();
        File f = new File("C:\\");

        FileMethod.forFile(f,list);
        System.out.println("for finshed!find"+list.size()+"files");
        //FileMethod.show(list);
        FileMethod.save(list,new File("D:\\files_For.txt"));

        long l2 = System.currentTimeMillis();
        System.out.println("times:"+(l2-l1)/1000+"s");

    }



    public static void forFile(File file, ArrayList<String> list) throws IOException {
        File[] fArr = file.listFiles();
        if (fArr != null) {
            for (File file1 : fArr) {
                if (file1.isDirectory()) {
                    forFile(file1, list);
                } else {
                    list.add(file1.getAbsolutePath());
                    System.out.println("find" + list.size() + "files");
                }
            }
        }
    }

    public static void show(ArrayList<String> list) {
        for (String s : list) {
            System.out.println(s);
        }
        System.out.println(list.size() + "files");
    }

    public static void save (ArrayList<String> arr, File file) throws IOException {
        FileOutputStream out = new FileOutputStream(file);
        for (String s : arr) {
            byte[] bytes = s.getBytes();
            out.write(bytes);
            out.write("\r\n".getBytes());
        }
        out.close();

    }
}

