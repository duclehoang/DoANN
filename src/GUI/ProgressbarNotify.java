package GUI;

import javax.swing.*;
import java.awt.*;

public class ProgressbarNotify implements Runnable {
    public static JFrame f;
    public static JProgressBar b;

    public ProgressbarNotify() {
        // create a frame
        f=new JFrame();
        // create a panel
        // create a progressbar
        b = new JProgressBar();
        b.setBackground(Color.white);
        b.setForeground(new Color(69, 211, 70));
        // set initial value

        b.setValue(0);
        b.setFont(new Font("Arial", Font.BOLD,16));
        b.setStringPainted(true);
        f.add(b);

        // add progressbar
        f.setLocationRelativeTo(null);


        f.setBounds(450,340,520,50);

        f.setUndecorated(true);
        f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        f.setVisible(true);
        // add panel

        fill();
        // set the size of the frame



    }

    public static void fill() {

        int i = 0;
        try {
            while (i <= 100) {
                // set text accoring to the level to which the bar is filled
                if (i > 30 && i < 70)
                    b.setString(i+"%"+" Please Wait");
                else if (i > 70)
                    b.setString(i+"%"+" Success");
                else
                    b.setString(i+"%"+" Execute Started ");
                // fill the menu bar
                b.setValue(i + 10);
                // delay the thread
                Thread.sleep(50);
                i += 2;
            }
            f.setVisible(false);
        } catch (Exception e) {
        }
    }


    @Override
    public void run() {
        new ProgressBar();
    }
}
