package Server;

import GUI.GUI;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.Socket;
import java.net.URL;

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
            String clientMessage="", serverMessage="",command=" ",clientMessage1="";
            while(!clientMessage.equals(" ")){
                clientMessage=inStream.readUTF();
               // clientMessage1=inStream.readUTF();
                System.out.println("Day la "+clientMessage1);
                System.out.println("Day la comand ben gui "+gui.itfomat.getActionCommand());
               // System.out.println("From Client-" +serverClient.getInetAddress()+ " :"+clientMessage);
             //   squre = Integer.parseInt(clientMessage) * Integer.parseInt(clientMessage);
                if(gui.ChkbJava.isSelected()&&gui.itfomat.getActionCommand().equals("Fomat")){
                    serverMessage= FormaterJavaCode(clientMessage);
                } else if (gui.ChkbPython.isSelected()&&gui.itfomat.getActionCommand().equals("Fomat")) {
                    serverMessage= FormaterPythonCode(clientMessage);
                } else if (gui.ChkbPhp.isSelected()&&gui.itfomat.getActionCommand().equals("Fomat")) {
                    serverMessage= FormaterJavaScriptCode(clientMessage);
                }
                else if (gui.ChkbJs.isSelected()&&gui.itfomat.getActionCommand().equals("Fomat")) {
                    serverMessage= FormaterC_PlusCode(clientMessage);
                }
//                else if (gui.ChkbJava.isSelected()&&gui.commmand.equals("Run")) {
//                    serverMessage=validatecodeJava(clientMessage);
//                }

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

    public String validatecodeJava(String code){

        String clientId = "8b3aa75339a0dfb71f8f2b4a3d0a8e29"; //Replace with your client ID
        String clientSecret = "68477e8c080bce7fc5dbdc579fcfadd4fbdc1169a62c36d8e0868f0670cb9915"; //Replace with your client Secret

        String language = "java";
        String versionIndex = "0";

        try {
            URL url = new URL("https://api.jdoodle.com/v1/execute");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoOutput(true);
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");

            String input = "{\"clientId\": \"" + clientId + "\",\"clientSecret\":\"" + clientSecret + "\",\"script\":\"" + code +
                    "\",\"language\":\"" + language + "\",\"versionIndex\":\"" + versionIndex + "\"} ";

            System.out.println(input);

            OutputStream outputStream = connection.getOutputStream();
            outputStream.write(input.getBytes());
            outputStream.flush();

            if (connection.getResponseCode() != HttpURLConnection.HTTP_OK) {
                throw new RuntimeException("Please check your inputs : HTTP error code : "+ connection.getResponseCode());
            }

            BufferedReader bufferedReader;
            bufferedReader = new BufferedReader(new InputStreamReader(
                    (connection.getInputStream())));

            String output;
            code=" ";
            System.out.println("Output from JDoodle .... \n");
            while ((output = bufferedReader.readLine()) != null) {
                code=code+output;
                System.out.println(output);
            }

            connection.disconnect();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    return code;
    }
    //
}
