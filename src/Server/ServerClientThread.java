package Server;

import GUI.GUI;
import GUI.Function_file;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.Socket;
import java.net.URL;

import static org.fusesource.jansi.Ansi.Color.RED;
import static org.fusesource.jansi.Ansi.ansi;

public class ServerClientThread extends Thread {
    Socket serverClient;
    int clientNo;
    int squre;
    GUI gui=new GUI();
    public ServerClientThread(Socket inSocket, int counter) throws IOException, InterruptedException {
        serverClient = inSocket;
        clientNo=counter;
    }
    public void run(){
        try{
            DataInputStream inStream = new DataInputStream(serverClient.getInputStream());
            DataOutputStream outStream = new DataOutputStream(serverClient.getOutputStream());
            String clientMessage="", serverMessage="",command=" ",clientMessage1="",clientMessage2=" ";
            while(!clientMessage.equals(" ")){
                clientMessage=inStream.readUTF();
                clientMessage1=inStream.readUTF();
                clientMessage2=inStream.readUTF();
                System.out.println("Day la "+clientMessage1);
                System.out.println("Day la comand ben gui "+gui.itRun.getActionCommand());
                System.out.println("Day la file name ben gui "+clientMessage2);
               // System.out.println("From Client-" +serverClient.getInetAddress()+ " :"+clientMessage);
             //   squre = Integer.parseInt(clientMessage) * Integer.parseInt(clientMessage);
                if(gui.ChkbJava.isSelected()&&gui.itfomat.getActionCommand().equals(clientMessage1)){
                    System.out.println("java fomater");
                    serverMessage= FormaterJavaCode(clientMessage);
                } else if (gui.ChkbPython.isSelected()&&gui.itfomat.getActionCommand().equals(clientMessage1)) {
                    serverMessage= FormaterPythonCode(clientMessage);
                } else if (gui.ChkbPhp.isSelected()&&gui.itfomat.getActionCommand().equals(clientMessage1)) {
                    serverMessage= FormaterJavaScriptCode(clientMessage);
                }
                else if (gui.ChkbJs.isSelected()&&gui.itfomat.getActionCommand().equals(clientMessage1)) {
                    serverMessage= FormaterC_PlusCode(clientMessage);
                }
                else if (gui.ChkbJava.isSelected()&&gui.itRun.getActionCommand().equals(clientMessage1)) {
                  //  System.out.println("vbnm");
                    serverMessage=validatecodeJava(clientMessage,clientMessage2);
                }

                outStream.writeUTF(serverMessage);
                outStream.flush();
               // System.out.println(serverMessage);
            }
            inStream.close();
            outStream.close();
            serverClient.close();
        }catch(Exception ex){
            //System.out.println(ex);
        }
    }
    public String FormaterJavaCode(String code) throws IOException {
        Document doc = Jsoup.connect("https://tools.tutorialspoint.com/format_javascript.php").ignoreContentType(true).
                data("code",code).post();
        JSONObject jsonObject=new JSONObject((doc.body().addClass("ace_content")).text());
        String  tmp= (String) jsonObject.get("code");
        code=" ";
        code=tmp;
      //  System.out.println(code);
        return code;
    }

    public String FormaterPythonCode(String code) throws IOException {
        Document doc = Jsoup.connect(" https://tools.tutorialspoint.com/format_python.php").ignoreContentType(true).
                data("code",code).post();
        JSONObject jsonObject=new JSONObject((doc.body().addClass("ace_content")).text());
        String  tmp= (String) jsonObject.get("code");
        code=" ";
        code=tmp;
        //  System.out.println(code);
        return code;
    }

    public String FormaterC_PlusCode(String code) throws IOException {
        Document doc = Jsoup.connect("https://tools.tutorialspoint.com/format_c.php").ignoreContentType(true).
                data("code",code).post();
        JSONObject jsonObject=new JSONObject((doc.body().addClass("ace_content")).text());
        String  tmp= (String) jsonObject.get("code");
        code=" ";
        code=tmp;
        //  System.out.println(code);
        return code;
    }

    public String FormaterJavaScriptCode(String code) throws IOException {
        Document doc = Jsoup.connect("https://www.10bestdesign.com/dirtymarkup/api/js").ignoreContentType(true).
                data("code",code).post();
        JSONObject jsonObject=new JSONObject(doc.text());
        String  tmp= (String) jsonObject.get("clean");
        code=" ";
        code=tmp;
        //  System.out.println(code);
        return code;
    }

    public String validatecodeJava(String code,String filename) throws IOException {
        Function_file function_file=new Function_file(gui);
        String fname=function_file.fileName;
      //  System.out.println("day la anme "+function_file.getFilename());
        Document doc = Jsoup.connect("https://compiler.javatpoint.com/opr/run.jsp").ignoreContentType(true)
                .data("val", code)
                .data("filename",filename)
                .data("args"," ")
                .data("classname"," ").post();

        String tmp="// "+doc.body().select("div").first().text()+"\nResult:\n\n"+doc.body().select("p+p+div").text();
        code=" ";
        code=tmp;

        return code;
    }
    //
}
