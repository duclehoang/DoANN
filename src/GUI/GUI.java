package GUI;



import Clientx.ClientGUI;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.UndoableEditEvent;
import javax.swing.event.UndoableEditListener;
import javax.swing.text.Element;
import javax.swing.undo.UndoManager;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.InetAddress;

import static java.lang.Thread.sleep;

public class GUI extends Component implements ActionListener {
    JFrame windown;
   public JTextArea jTextArea,lines;
   public  String txtprt=" ",txtAdd=" ";

    boolean WrapOn=false;
    JScrollPane jScrollPane;
    JMenuBar jMenuBar;
    JMenu menuFile,menuEdit,menuColor,menuFomat,menuBuild,Conect_to_server;
    JMenuItem itNew,itOpen,itSave,ítSaveAs,itExit,itUndo,itRedo;

    public String commmand=" ";

    public String getCommmand() {
        return commmand;
    }

    public void setCommmand(String commmand) {
        this.commmand = commmand;
    }

    JMenuItem itWrap,itFontArial,itFontCSMS,itFontTNR,itFontSize8,itFontSize12,ItFontSize16,ItFontSize20,ItFontSize24,ItFontSize28;
    JMenu menuFont,menuFontSize;

    JMenuItem itColor1,itColor2,itColor3;

    public JMenuItem itRun;
    public JMenuItem itfomat;
    JMenuItem itRunandFomat;
    JMenuItem itConnect;
    public JRadioButtonMenuItem ChkbJava;
    public JRadioButtonMenuItem ChkbCSharp;
    public JRadioButtonMenuItem ChkbC_CPP;
    public JRadioButtonMenuItem ChkbPython;

    Image Jtextthem;


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
    OutPutFrame outPutFrame=new OutPutFrame();

    ClientGUI clientGUI=new ClientGUI();
    private InetAddress add;
    JFrame panel;
    public  static  void main(String args[]) throws InterruptedException, IOException {

        new GUI();
    }

    public JFrame getWindown() {
        return windown;
    }

    public void setWindown(JFrame windown) {
        this.windown = windown;
    }

    public String getTxtprt() {
        return txtprt;
    }

    public void setTxtprt(String txtprt) {
        this.txtprt = txtprt;
    }

    public String getTxtAdd() {
        return txtAdd;
    }

    public void setTxtAdd(String txtAdd) {
        this.txtAdd = txtAdd;
    }

    public GUI() throws InterruptedException, IOException {
     connet();
       createWindown();
       createTextarea();
        createLinesTexarea();
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
       windown.setVisible(false);






    }

    public void connet(){
         panel=new JFrame();
        panel.setLayout(new FlowLayout());
        JLabel label=new JLabel("Server Host :");
        JTextField txtAddress=new JTextField(13);
        JLabel label1=new JLabel("Port :");
        JTextField txtPort=new JTextField("1234");
        JButton b =new JButton("Conect");
        b.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource()==b){
                    txtAdd=txtAddress.getText();
                    txtprt=txtPort.getText();
                    panel.setVisible(false);
                    try {

                        clientGUI.Connect(txtAddress.getText(),Integer.parseInt(txtPort.getText()));

                        windown.setVisible(true);
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }


                }
            }
        });
        setTxtprt(txtPort.getText());
        setTxtAdd(txtAddress.getText());
        panel.add(label);
        panel.add(txtAddress);
        panel.add(label1);
        panel.add(txtPort);
        panel.add(b);
        panel.setSize(320,100);
        panel.setVisible(true);
        panel.setLocationRelativeTo(null);
        panel.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

    }

    public  void createWindown() throws InterruptedException {

        windown=new JFrame("Editor Check And Excute Code");
        windown.setSize(800,600);
        windown.setIconImage(EditorIcon.getImage());
        windown.setLocationRelativeTo(null);
        windown.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);




    }

    public  void createLinesTexarea(){
        jScrollPane = new JScrollPane();
        jTextArea = new JTextArea();
        lines = new JTextArea("1");
        lines.setForeground(Color.RED);
        lines.setFont(new Font("Arial",Font.BOLD,16));
        lines.setBackground(Color.LIGHT_GRAY);
        lines.setEditable(false);
        //  Code to implement line numbers inside the JTextArea
        jTextArea.getDocument().addDocumentListener(new DocumentListener() {
            public String getText() {
                int caretPosition = jTextArea.getDocument().getLength();
                Element root = jTextArea.getDocument().getDefaultRootElement();
                String text = "1" + System.getProperty("line.separator");
                for(int i = 2; i < root.getElementIndex(caretPosition) + 2; i++) {
                    text += i + System.getProperty("line.separator");
                }
                return text;
            }
            @Override
            public void changedUpdate(DocumentEvent de) {
                lines.setText(getText());
            }
            @Override
            public void insertUpdate(DocumentEvent de) {
                lines.setText(getText());
            }
            @Override
            public void removeUpdate(DocumentEvent de) {
                lines.setText(getText());
            }
        });
        jScrollPane.getViewport().add(jTextArea);
        jScrollPane.setRowHeaderView(lines);
        windown.add(jScrollPane);

    }
    public void createPanelOutput(){
        JPanel output = new JPanel();

        output.setSize(800,400);
        output.setVisible(true);

      windown.add(output);
    }


    public void createTextarea(){
        jTextArea =new JTextArea();
        jTextArea.setTabSize(30);

        jTextArea.getDocument().addUndoableEditListener(new UndoableEditListener() {
            @Override
            public void undoableEditHappened(UndoableEditEvent e) {
                undoManager.addEdit(e.getEdit());
            }
        });





        jScrollPane=new JScrollPane(jTextArea,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        windown.add(jTextArea);
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


        ChkbJava=new JRadioButtonMenuItem("Java");
        ChkbJava.addActionListener(this);
        ChkbJava.setActionCommand("Java");
        menuBuild.add(ChkbJava);

        ChkbCSharp=new JRadioButtonMenuItem("C#");
        ChkbCSharp.addActionListener(this);
        ChkbCSharp.setActionCommand("CS");
        menuBuild.add(ChkbCSharp);

        ChkbC_CPP=new JRadioButtonMenuItem("C++");
        ChkbC_CPP.addActionListener(this);
        ChkbC_CPP.setActionCommand("C++");
        menuBuild.add(ChkbC_CPP);

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
//        Conect_to_server=new JMenu("Connect Server");
//        jMenuBar.add(Conect_to_server);

    }
    public void createMenuConnect(){
        itConnect=new JMenuItem("Enter Port");
        itConnect.addActionListener(this);
        itConnect.setActionCommand("Enter Port");
        Conect_to_server.add(itConnect);
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
        itColor1=new JMenuItem("Blue smooth");
        itColor1.addActionListener(this);
        itColor1.setActionCommand("White");
        menuColor.add(itColor1);

        itColor2=new JMenuItem("Dark green");
        itColor2.addActionListener(this);
        itColor2.setActionCommand("Black");
        menuColor.add(itColor2);

        itColor3=new JMenuItem("Purple Rise");
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


    public String Covert(String t){

        t= t.replace("\n", "").replace(" ", "");
        System.out.println(t);
        return t;
    }
    public String CheckSelected( )  {
        String sms=" ";
        if (ChkbJava.isSelected()){
            sms="JavaIsSelected";
        } else if (ChkbPython.isSelected()) {
            sms="PythonIsSelected";
        } else if (ChkbC_CPP.isSelected()) {
            sms="CPPsSelected";
        } else if (ChkbCSharp.isSelected()) {
            sms="CSharpIsSelected";
        }


//            System.out.println("hello ne !");

        return sms;
    }
    private void itfomatMouseClicked (MouseEvent evt){
        JOptionPane.showMessageDialog(GUI.this,"hello");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String message=" ";
        commmand=e.getActionCommand();
        setCommmand(commmand);
        System.out.println(getCommmand()+"get comand");
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
            case  "CS":function_build.ChangeCSharpProgramLanguage();
                break;
            case  "C++":function_build.ChangeC_CPProgramLanguage();
                break;
            case  "Python":function_build.ChangePythonProgramLanguage();
                break;
            case  "Run":
            //JOptionPane.showMessageDialog(windown,"hello");

               //System.out.println(commmand);
                 message= jTextArea.getText();
             //   System.out.println(Covert(message));
                outPutFrame.area.setText(" ");
                if (!ChkbJava.isSelected()&&!ChkbPython.isSelected()&&!ChkbC_CPP.isSelected()&!ChkbCSharp.isSelected()){
                    JOptionPane.showMessageDialog(windown,"Please choose programing language need execute","Error choose programming language",0);
                    return;
                }
                else if (message.isEmpty()&&commmand.equals("Run") ){
                    JOptionPane.showMessageDialog(windown,"Please save file before Run and Enter some thing your input","Error",0);
                    return;
                }
               else if ( function_file.fileName==null){
                    JOptionPane.showMessageDialog(windown,"Please save file before Run","Error",0);
                    return;
                }

                else {
                    if (ChkbJava.isSelected()&&commmand.equals("Run")) {
                        try {
                            clientGUI.send(message,commmand, function_file.fileName.substring(0, function_file.fileName.lastIndexOf('.')),CheckSelected());
                        } catch (IOException ex) {
                            throw new RuntimeException(ex);
                        }
                        outPutFrame.f.setVisible(true);
                        outPutFrame.area.setForeground(Color.RED);
                        outPutFrame.area.setFont(new Font("Arial", Font.BOLD,18));
                        outPutFrame.area.setText("Result: \n\n"+clientGUI.recivece());

                    }else if (ChkbPython.isSelected()&&commmand.equals("Run")) {
                        try {
                            clientGUI.send(message,commmand, function_file.fileName.substring(0, function_file.fileName.lastIndexOf('.')),CheckSelected());
                        } catch (IOException ex) {
                            throw new RuntimeException(ex);
                        }
                        outPutFrame.f.setVisible(true);
                        outPutFrame.area.setForeground(Color.RED);
                        outPutFrame.area.setFont(new Font("Arial", Font.BOLD,14));
                        outPutFrame.area.setText("Result: \n\n"+clientGUI.recivece());

                    }
                    else if (ChkbC_CPP.isSelected()&&commmand.equals("Run")) {
                        try {
                            clientGUI.send(message,commmand, function_file.fileName.substring(0, function_file.fileName.lastIndexOf('.')),CheckSelected());
                        } catch (IOException ex) {
                            throw new RuntimeException(ex);
                        }
                        outPutFrame.f.setVisible(true);
                        outPutFrame.area.setForeground(Color.RED);
                        outPutFrame.area.setFont(new Font("Arial", Font.BOLD,14));
                        outPutFrame.area.setText("Result: \n\n"+clientGUI.recivece());

                    }
                    else if (ChkbCSharp.isSelected()&&commmand.equals("Run")) {
                        try {
                            clientGUI.send(message,commmand, function_file.fileName.substring(0, function_file.fileName.lastIndexOf('.')),CheckSelected());
                        } catch (IOException ex) {
                            throw new RuntimeException(ex);
                        }
                        outPutFrame.f.setVisible(true);
                        outPutFrame.area.setForeground(Color.RED);
                        outPutFrame.area.setFont(new Font("Arial", Font.BOLD,14));
                        outPutFrame.area.setText("Result: \n\n"+clientGUI.recivece());

                    }
                }
                break;
            case  "Fomat":
               // System.out.println(commmand);
                 message= jTextArea.getText();
                System.out.println(message);
                if (!ChkbJava.isSelected()&&!ChkbPython.isSelected()&&!ChkbC_CPP.isSelected()&&!ChkbCSharp.isSelected()){
                    JOptionPane.showMessageDialog(windown,"Please choose programing language need execute","Error choose programming language",0);
                    return;
                }else if (message.isEmpty()&&commmand.equals("Fomat")){
                    JOptionPane.showMessageDialog(windown,"Nothing to fomat","Error",0);
                    return;
                }
                else {
                    if (ChkbJava.isSelected()&&commmand.equals("Fomat")) {
                        try {
                            clientGUI.send(message,commmand, function_file.fileName,CheckSelected());
                        } catch (IOException ex) {
                            throw new RuntimeException(ex);
                        }
                        jTextArea.setText(clientGUI.recivece());
                    } else if (ChkbPython.isSelected()) {
                        try {
                            clientGUI.send(message,commmand, function_file.fileName,CheckSelected());
                        } catch (IOException ex) {
                            throw new RuntimeException(ex);
                        }
                        jTextArea.setText(clientGUI.recivece());
                    } else if (ChkbCSharp.isSelected()) {
                        try {
                            clientGUI.send(message,commmand, function_file.fileName,CheckSelected());
                        } catch (IOException ex) {
                            throw new RuntimeException(ex);
                        }
                        jTextArea.setText(clientGUI.recivece());
                    }
                    else if (ChkbC_CPP.isSelected()) {
                        try {
                            clientGUI.send(message,commmand, function_file.fileName,CheckSelected());
                        } catch (IOException ex) {
                            throw new RuntimeException(ex);
                        }
                        jTextArea.setText(clientGUI.recivece());
                    }

                }
                break;

                }




        }

    }



