package GUI;

import java.awt.*;

public class Function_fomat {
    GUI gui;
    Font arial,comícanMS,timeNewRoman;
    String selectedFont;

    public Function_fomat(GUI gui){
        this.gui=gui;
    }
    public void wordWrap(){
        if (gui.WrapOn==false){
            gui.WrapOn=true;
            gui.jTextArea.setLineWrap(true);
            gui.jTextArea.setWrapStyleWord(true);
            gui.itWrap.setText("Wrod Wrap: On");

        }else if (gui.WrapOn==true){
            gui.WrapOn=false;
            gui.jTextArea.setLineWrap(false);
            gui.jTextArea.setWrapStyleWord(false);
            gui.itWrap.setText("Wrod Wrap: Off");
        }
    }
    public void creaFont(int fontSize){
        arial=new Font("Arial",Font.PLAIN,fontSize);
        comícanMS=new Font("Comic Sans MS",Font.PLAIN,fontSize);
        timeNewRoman=new Font("Times New Roman",Font.PLAIN,fontSize);

        setFont(selectedFont);

    }
    public void setFont(String font){
        selectedFont=font;
        switch (selectedFont){
            case  "Arial":
                gui.jTextArea.setFont(arial);
                break;
            case  "Comic Sans MS":
                gui.jTextArea.setFont(comícanMS);
                break;
            case  "Times New Roman":
                gui.jTextArea.setFont(timeNewRoman);
                break;
            case  "ZoomIn":
                gui.jTextArea.setFont(arial);
                break;
            case  "ZoomOut":
                gui.jTextArea.setFont(arial);
                break;
        }

    }


}
