package GUI;

import javax.swing.*;
import java.awt.*;

public class ProgressBar implements Runnable {
    public static JFrame f;
    public static JProgressBar b;

    public ProgressBar() {
        // create a frame
        f=new JFrame();
        // create a panel
        // create a progressbar
        b = new JProgressBar();
        b.setBackground(Color.white);
        b.setForeground(new Color(212, 71, 67));
        // set initial value

        b.setValue(0);
        b.setFont(new Font("Arial", Font.BOLD,16));
        b.setStringPainted(true);
        f.add(b);

        // add progressbar
        f.setLocationRelativeTo(null);


        f.setBounds(600,350,520,70);

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
                    b.setString("wait for laoding program");
                else if (i > 70)
                    b.setString("almost finished loading");
                else
                    b.setString("Program started");
                // fill the menu bar
                b.setValue(i + 10);
                // delay the thread
                Thread.sleep(5);
                i += 1;
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








