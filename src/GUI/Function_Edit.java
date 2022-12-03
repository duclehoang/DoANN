package GUI;

public class Function_Edit {
    GUI gui;


    public Function_Edit (GUI gui) {
       this.gui=gui;
    }
    public  void Undo(){
        gui.undoManager.undo();
    }
    public  void Redo(){
        gui.undoManager.redo();
    }
}
