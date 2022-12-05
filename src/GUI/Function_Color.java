package GUI;

import javax.swing.*;
import java.awt.*;

public class Function_Color {
    GUI gui;
    Image Jtextthem;
    public Function_Color(GUI gui){
        this.gui=gui;
    }

    public void changeColor(String color){
        switch (color){
            case "White":
                gui.windown.getContentPane().setBackground(Color.white);
                gui.jTextArea.setBackground(new Color(151, 217, 208));
                gui.jTextArea.setForeground(Color.BLACK);

                break;
            case "Black":
                gui.windown.getContentPane().setBackground(Color.black);
                gui.jTextArea.setBackground(new Color(23, 63, 94));
                gui.jTextArea.setForeground(Color.WHITE);
                break;
            case "Blue":
                gui.windown.getContentPane().setBackground(Color.BLUE);
                gui.jTextArea.setBackground(new Color(214, 159, 255));
                gui.jTextArea.setForeground(Color.BLACK);
                break;
        }

    }

}
