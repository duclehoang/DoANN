package GUI;

import javax.swing.*;
import javax.swing.event.UndoableEditEvent;
import javax.swing.event.UndoableEditListener;
import javax.swing.undo.UndoManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ClientGUI implements ActionListener {
    JFrame windown;
    JTextArea jTextArea;
    boolean WrapOn=false;
    JScrollPane jScrollPane;
    JMenuBar jMenuBar;
    JMenu menuFile,menuEdit,menuColor,menuFomat,menuBuild;
    JMenuItem itNew,itOpen,itSave,ítSaveAs,itExit,itUndo,itRedo;


    JMenuItem itWrap,itFontArial,itFontCSMS,itFontTNR,itFontSize8,itFontSize12,ItFontSize16,ItFontSize20,ItFontSize24,ItFontSize28;
    JMenu menuFont,menuFontSize;

    JMenuItem itColor1,itColor2,itColor3;

    JMenuItem itRun,itfomat, itRunandFomat;
    JRadioButtonMenuItem ChkbJava, ChkbPhp,ChkbJs,ChkbPython;


    ImageIcon ButtonIcon = new ImageIcon("src\\Images\\play_15px.png","Run the code");

    ImageIcon FomatIcon=new ImageIcon("src\\Images\\pagelines_15px.png","Fomat your code");
    ImageIcon NewIcon=new ImageIcon("src\\Images\\new_copy_15px.png");
    ImageIcon OpenIcon=new ImageIcon("src\\Images\\opened_folder_15px.png");
    ImageIcon SaveIcon=new ImageIcon("src\\Images\\save_15px.png");
    ImageIcon SaveAsIcon=new ImageIcon("src\\Images\\save_as_15px.png");
    ImageIcon UndoIcon=new ImageIcon("src\\Images\\undo_15px.png");
    ImageIcon RdoIcon=new ImageIcon("src\\Images\\redo_15px.png");
    ImageIcon ExitIcon=new ImageIcon("src\\Images\\exit_sign_15px.png");
    ImageIcon EditorIcon=new ImageIcon("src\\Images\\edit_20px.png");



    Function_file function_file=new Function_file(this);
    Function_fomat function_fomat=new Function_fomat(this);
    Function_Color function_color=new Function_Color(this);
    Function_Edit function_edit=new Function_Edit(this);
    UndoManager undoManager=new UndoManager();
    Function_Build function_build=new Function_Build(this);
    public  static  void main(String args[]){
        new ClientGUI();
    }
    public ClientGUI() {
       createWindown();
       createTextarea();
       createMenuber();
        createMenuFile();
        createColorMenu();
        createEditMenu();
        createFomatMenu();
        createMenuBuild();


        function_fomat.selectedFont="Arial";
        function_fomat.creaFont(16);
        function_fomat.wordWrap();
        function_color.changeColor("white");
       windown.setVisible(true);



    }

    public  void createWindown(){
        windown=new JFrame("Editor Check And Excute Code");
        windown.setSize(800,600);
        windown.setIconImage(EditorIcon.getImage());
        windown.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

    }

    public void createTextarea(){
        jTextArea =new JTextArea();
        jTextArea.getDocument().addUndoableEditListener(new UndoableEditListener() {
            @Override
            public void undoableEditHappened(UndoableEditEvent e) {
                undoManager.addEdit(e.getEdit());
            }
        });

        jScrollPane=new JScrollPane(jTextArea,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        //windown.add(jTextArea);
        jScrollPane.setBorder(BorderFactory.createEmptyBorder());
        windown.add(jScrollPane);

    }

    public void createMenuBuild(){



        itRun=new JMenuItem("Run",ButtonIcon);
        itRun.addActionListener(this);
        itRun.setActionCommand("Run");
        menuBuild.add(itRun);



        itfomat=new JMenuItem("Fomat",FomatIcon);
        itfomat.addActionListener(this);
        itfomat.setActionCommand("Fomat");
        menuBuild.add(itfomat);

        itRunandFomat=new JMenuItem("Run & Fomat",ButtonIcon);
        itRunandFomat.addActionListener(this);
        itRunandFomat.setActionCommand("Run & Fomat");
        menuBuild.add(itRunandFomat);


        ChkbJava=new JRadioButtonMenuItem("Java",true);
        ChkbJava.addActionListener(this);
        ChkbJava.setActionCommand("Java");
        menuBuild.add(ChkbJava);

        ChkbPhp=new JRadioButtonMenuItem("PHP");
        ChkbPhp.addActionListener(this);
        ChkbPhp.setActionCommand("PHP");
        menuBuild.add(ChkbPhp);

        ChkbJs=new JRadioButtonMenuItem("JavaScript");
        ChkbJs.addActionListener(this);
        ChkbJs.setActionCommand("JavaScript");
        menuBuild.add(ChkbJs);

        ChkbPython=new JRadioButtonMenuItem("Python");
        ChkbPython.addActionListener(this);
        ChkbPython.setActionCommand("Python");
        menuBuild.add(ChkbPython);
    }
    public void  createMenuber(){
        jMenuBar=new JMenuBar();
        windown.setJMenuBar(jMenuBar);
        menuFile=new JMenu("File");
        jMenuBar.add(menuFile);
        menuEdit=new JMenu("Edit");
        jMenuBar.add(menuEdit);
        menuFomat=new JMenu("Fomat");
        jMenuBar.add(menuFomat);
        menuColor=new JMenu("Color");
        jMenuBar.add(menuColor);
        menuBuild=new JMenu("Build");
        jMenuBar.add(menuBuild);

    }
    public  void createMenuFile(){
        itNew=new JMenuItem("New",NewIcon);
        itNew.addActionListener(this);
        itNew.setActionCommand("New");
        menuFile.add(itNew);

        itOpen=new JMenuItem("Open",OpenIcon);
        itOpen.addActionListener(this);
        itOpen.setActionCommand("Open");
        menuFile.add(itOpen);

        itSave=new JMenuItem("Save",SaveIcon);
        itSave.addActionListener(this);
        itSave.setActionCommand("Save");
        menuFile.add(itSave);

        ítSaveAs=new JMenuItem("Save As",SaveAsIcon);
        menuFile.add(ítSaveAs);
        ítSaveAs.addActionListener(this);
        ítSaveAs.setActionCommand("SaveAs");

        itExit=new JMenuItem("Exit",ExitIcon);
        itExit.addActionListener(this);
        itExit.setActionCommand("Exit");
        menuFile.add(itExit);
    }
    public void createColorMenu(){
        itColor1=new JMenuItem("White");
        itColor1.addActionListener(this);
        itColor1.setActionCommand("White");
        menuColor.add(itColor1);

        itColor2=new JMenuItem("Black");
        itColor2.addActionListener(this);
        itColor2.setActionCommand("Black");
        menuColor.add(itColor2);

        itColor3=new JMenuItem("Blue");
        itColor3.addActionListener(this);
        itColor3.setActionCommand("Blue");
        menuColor.add(itColor3);
    }
    public void createFomatMenu(){
        itWrap =new JMenuItem("Word wrap: Off");
        itWrap.addActionListener(this);
        itWrap.setActionCommand("Word Wrap");
        menuFomat.add(itWrap);

        menuFont=new JMenu("Font");
        menuFomat.add(menuFont);

        itFontArial=new JMenuItem("Arial");
        itFontArial.addActionListener(this);
        itFontArial.setActionCommand("Arial");
        menuFont.add(itFontArial);

        itFontCSMS=new JMenuItem("Comic Sans MS");
        itFontCSMS.addActionListener(this);
        itFontCSMS.setActionCommand("Comic Sans MS");
        menuFont.add(itFontCSMS);

        itFontTNR=new JMenuItem("Times New Roman");
        itFontTNR.addActionListener(this);
        itFontTNR.setActionCommand("Times New Roman");
        menuFont.add(itFontTNR);


        menuFontSize=new JMenu("Font Size");
        menuFomat.add(menuFontSize);

        itFontSize8=new JMenuItem("8");
        itFontSize8.addActionListener(this);
        itFontSize8.setActionCommand("size8");
        menuFontSize.add(itFontSize8);

        itFontSize12=new JMenuItem("12");
        itFontSize12.addActionListener(this);
        itFontSize12.setActionCommand("size12");
        menuFontSize.add(itFontSize12);

        ItFontSize16=new JMenuItem("16");
        ItFontSize16.addActionListener(this);
        ItFontSize16.setActionCommand("size16");
        menuFontSize.add(ItFontSize16);

        ItFontSize20=new JMenuItem("20");
        ItFontSize20.addActionListener(this);
        ItFontSize20.setActionCommand("size20");
        menuFontSize.add(ItFontSize20);

        ItFontSize24=new JMenuItem("24");
        ItFontSize24.addActionListener(this);
        ItFontSize24.setActionCommand("size24");
        menuFontSize.add(ItFontSize24);

        ItFontSize28=new JMenuItem("28");
        ItFontSize28.addActionListener(this);
        ItFontSize28.setActionCommand("size28");
        menuFontSize.add(ItFontSize28);


    }
    public void createEditMenu(){
        itUndo=new JMenuItem("Undo",UndoIcon);
        itUndo.addActionListener(this);
        itUndo.setActionCommand("Undo");
        menuEdit.add(itUndo);

        itRedo=new JMenuItem("Redo",RdoIcon);
        itRedo.addActionListener(this);
        itRedo.setActionCommand("Redo");
        menuEdit.add(itRedo);


    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String commmand=e.getActionCommand();
        switch (commmand){
            case "New":function_file.newFile();
            break;
            case  "Open":function_file.openFile();
            break;
            case  "Save":function_file.saveFile();
                break;
            case  "SaveAs":function_file.saveAs();
                break;
            case  "Exit":function_file.exit();
                break;
            case  "Undo":function_edit.Undo();
                break;
            case  "Redo":function_edit.Redo();
                break;
            case  "Word Wrap":function_fomat.wordWrap();
                break;
            case  "size8":function_fomat.creaFont(8);
                break;
            case  "size12":function_fomat.creaFont(12);
                break;
            case  "size16":function_fomat.creaFont(16);
                break;
            case  "size20":function_fomat.creaFont(20);
                break;
            case  "size24":function_fomat.creaFont(24);
                break;
            case  "size28":function_fomat.creaFont(28);
                break;
            case  "Arial":function_fomat.setFont(commmand);
                break;
            case  "Comic Sans MS":function_fomat.setFont(commmand);
                break;
            case  "Times New Roman":function_fomat.setFont(commmand);
                break;
            case  "White":function_color.changeColor(commmand);
                break;
            case  "Black":function_color.changeColor(commmand);
                break;
            case  "Blue":function_color.changeColor(commmand);
                break;
            case  "Java":function_build.ChangeJavaProgramLanguage();
                break;
            case  "PHP":function_build.ChangePHPProgramLanguage();
                break;
            case  "JavaScript":function_build.ChangeJSProgramLanguage();
                break;
            case  "Python":function_build.ChangePythonProgramLanguage();
                break;
        }

    }
}
