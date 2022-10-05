package basictype.b9;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.TreeSet;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: bo
 * @Date: 2022/08/12/0:10
 * @Description: 在人间已是癫，何苦要上青天，不如温柔同眠
 */
public class Show {
    public static void showPoker(TreeSet<Integer> ts, HashMap<Integer, String> hm) {
        /*
         * @Author: bo
         * @Description: 显示牌
         * @DateTime: 2022/8/12 19:52
         * @In [ts, hm]
         * @Return void
         */
        for (Integer s : ts) {
            //拿到牌
            String s1 = hm.get(s);

            //判断花色输出
            if ((s1.contains("♥"))||(s1.contains("♦"))){
                String red = "\033[31m[" + hm.get(s) + "]" + " ";
                System.out.print(red);
            }else {
                if ((s1.equals("大王"))||(s1.equals("小王"))){
                    String king = "\033[32m[" + hm.get(s) + "]" + " ";
                    System.out.print(king);
                }else {
                    String normal = "\033[m[" + hm.get(s) + "]" + " ";
                    System.out.print(normal);
                }
            }
        }
        System.out.println();
    }
}
