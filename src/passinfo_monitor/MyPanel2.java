package passinfo_monitor;

import javax.swing.*;
import java.awt.*;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: bo
 * @Date: 2022/08/30/23:39
 * @Description: 在人间已是癫，何苦要上青天，不如温柔同眠
 */
public class MyPanel2 extends JPanel {
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.setColor(Color.red);
        g.drawLine(1,1,300,300);
        g.fillRect(1,1,300,300);
    }
}
