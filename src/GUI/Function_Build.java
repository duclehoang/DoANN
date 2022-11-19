package GUI;

public class Function_Build  {
    ClientGUI gui;
    public Function_Build(ClientGUI gui){
        this.gui=gui;
    }

    public void ChangeJavaProgramLanguage(){
        if (gui.ChkbJava.isSelected()) {
            gui.ChkbPython.setSelected(false);
            gui.ChkbJs.setSelected(false);
            gui.ChkbPhp.setSelected(false);
            // gui.ChkbPython.setSelected(false);
        }


    }

    public void ChangePHPProgramLanguage(){
         if (gui.ChkbPhp.isSelected()) {
            gui.ChkbPython.setSelected(false);
            gui.ChkbJava.setSelected(false);
            gui.ChkbJs.setSelected(false);

        }
    }

    public void ChangeJSProgramLanguage(){
         if (gui.ChkbJs.isSelected()) {
            gui.ChkbPython.setSelected(false);
            gui.ChkbJava.setSelected(false);
            gui.ChkbPhp.setSelected(false);

        }

    }
    public void ChangePythonProgramLanguage(){
             if (gui.ChkbPython.isSelected()) {
            gui.ChkbPhp.setSelected(false);
            gui.ChkbJava.setSelected(false);
            gui.ChkbJs.setSelected(false);

        }
    }
}
