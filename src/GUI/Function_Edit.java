package GUI;

public class Function_Edit {
    ClientGUI gui;


    public Function_Edit (ClientGUI gui) {
       this.gui=gui;
    }
    public  void Undo(){
        gui.undoManager.undo();
    }
    public  void Redo(){
        gui.undoManager.redo();
    }
}
