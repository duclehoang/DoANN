package GUI;

import java.awt.*;

public class Function_Color {
    GUI gui;
    public Function_Color(GUI gui){
        this.gui=gui;
    }

    public void changeColor(String color){
        switch (color){
            case "White":
                gui.windown.getContentPane().setBackground(Color.white);
                gui.jTextArea.setBackground(Color.white);
                gui.jTextArea.setForeground(Color.BLACK);
                break;
            case "Black":
                gui.windown.getContentPane().setBackground(Color.black);
                gui.jTextArea.setBackground(Color.BLACK);
                gui.jTextArea.setForeground(Color.WHITE);
                break;
            case "Blue":
                gui.windown.getContentPane().setBackground(Color.BLUE);
                gui.jTextArea.setBackground(Color.BLUE);
                gui.jTextArea.setForeground(Color.white);
                break;
        }

    }
}
