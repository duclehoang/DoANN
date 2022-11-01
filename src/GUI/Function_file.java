package GUI;

import java.awt.*;
import java.io.*;

public class Function_file {
    ClientGUI gui;
    String fileName,fileAddress;


    public Function_file(ClientGUI gui){
        this.gui=gui;

    }
    public void newFile(){
        gui.jTextArea.setText("");
        gui.windown.setTitle("New");
        fileName=null;
        fileAddress=null;
    }
    public void openFile(){
        FileDialog fileDialog=new FileDialog(gui.windown,"Open",FileDialog.LOAD);
        fileDialog.setVisible(true);
        if (fileDialog.getFile()!=null){
            fileName=fileDialog.getFile();
            fileAddress=fileDialog.getDirectory();
            gui.windown.setTitle("filename");

        }
        try {
            BufferedReader bufferedReader=new BufferedReader(new FileReader(fileAddress + fileName));
            gui.jTextArea.setText("");
            String line=null;
            while ((line=bufferedReader.readLine())!=null){
                gui.jTextArea.append(line + "\n");
            }
            bufferedReader.close();

        }catch (IOException e){
            System.out.println("FILE NOT OPEND!");

        }

    }
    public void saveFile(){
        if(fileName==null){
            saveAs();
        }
        else {
            try{
                FileWriter fileWriter=new FileWriter(fileAddress + fileName);
                fileWriter.write(gui.jTextArea.getText());
                gui.windown.setTitle(fileName );
                fileWriter.close();

            }catch (IOException e){

                System.out.println("SOMETHING WORONG");
            }
        }


    }

    public void saveAs(){
        FileDialog fileDialog=new FileDialog(gui.windown,"Save",FileDialog.SAVE);
        fileDialog.setVisible(true);
        if (fileDialog.getFile()!=null){
            fileName=fileDialog.getFile();
            fileAddress=fileDialog.getDirectory();
            gui.windown.setTitle(fileName);

        }
        try {
            FileWriter fileWriter=new FileWriter(fileAddress + fileName);
            fileWriter.write(gui.jTextArea.getText());
            fileWriter.close();
        }
        catch (IOException e){
            System.out.println("SOMETHING WORONG");

        }
    }
    public void exit(){
        System.exit(0);
    }

}
