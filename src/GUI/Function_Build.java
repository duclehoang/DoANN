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
            System.out.println("Java is selected :"+gui.ChkbJava.isSelected());
            gui.ChkbPython.setSelected(false);

            gui.ChkbC_CPP.setSelected(false);

            gui.ChkbCSharp.setSelected(false);

            // gui.ChkbJavascript.setSelected(false);
        }


    }

    public void ChangeCSharpProgramLanguage() {
        if (gui.ChkbCSharp.isSelected()) {
            System.out.println("CSharp is selected :"+gui.ChkbCSharp.isSelected());
            gui.ChkbJava.setSelected(false);
            gui.ChkbC_CPP.setSelected(false);
            gui.ChkbPython.setSelected(false);

        }
    }

    public void ChangeC_CPProgramLanguage() {
        if (gui.ChkbC_CPP.isSelected()) {
            System.out.println("C++ is selected :"+gui.ChkbC_CPP.isSelected());
            gui.ChkbPython.setSelected(false);
            gui.ChkbJava.setSelected(false);
            gui.ChkbCSharp.setSelected(false);

        }

    }

    public void ChangePythonProgramLanguage() {

        if (gui.ChkbPython.isSelected()) {
            System.out.println("Python is selected: "+gui.ChkbPython.isSelected());
            gui.ChkbJava.setSelected(false);
            gui.ChkbCSharp.setSelected(false);
            gui.ChkbC_CPP.setSelected(false);

        }
    }

}
