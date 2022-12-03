package GUI;



import Clientx.ClientGUI;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;


import java.io.IOException;


public class Function_Build {
    GUI gui;
    ClientGUI clientGUI = new ClientGUI();

    public Function_Build(GUI gui) throws IOException, InterruptedException {
        this.gui = gui;
    }

    public void ChangeJavaProgramLanguage() {
        if (gui.ChkbJava.isSelected()) {
            gui.ChkbPython.setSelected(false);
            gui.ChkbJs.setSelected(false);
            gui.ChkbPhp.setSelected(false);
            // gui.ChkbPython.setSelected(false);
        }


    }

    public void ChangePHPProgramLanguage() {
        if (gui.ChkbPhp.isSelected()) {
            gui.ChkbPython.setSelected(false);
            gui.ChkbJava.setSelected(false);
            gui.ChkbJs.setSelected(false);

        }
    }

    public void ChangeJSProgramLanguage() {
        if (gui.ChkbJs.isSelected()) {
            gui.ChkbPython.setSelected(false);
            gui.ChkbJava.setSelected(false);
            gui.ChkbPhp.setSelected(false);

        }

    }

    public void ChangePythonProgramLanguage() {
        if (gui.ChkbPython.isSelected()) {
            gui.ChkbPhp.setSelected(false);
            gui.ChkbJava.setSelected(false);
            gui.ChkbJs.setSelected(false);

        }
    }

}
