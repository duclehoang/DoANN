package Server;

import GUI.GUI;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

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
            String clientMessage="", serverMessage="";
            while(!clientMessage.equals(" ")){
                clientMessage=inStream.readUTF();
               // System.out.println("From Client-" +serverClient.getInetAddress()+ " :"+clientMessage);
             //   squre = Integer.parseInt(clientMessage) * Integer.parseInt(clientMessage);
                if(gui.ChkbJava.isSelected()){
                    serverMessage= FormaterJavaCode(clientMessage);
                } else if (gui.ChkbPython.isSelected()) {
                    serverMessage= FormaterPythonCode(clientMessage);
                } else if (gui.ChkbPhp.isSelected()) {
                    serverMessage= FormaterJavaScriptCode(clientMessage);
                }
                else if (gui.ChkbJs.isSelected()) {
                    serverMessage= FormaterC_PlusCode(clientMessage);
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
    //
}
