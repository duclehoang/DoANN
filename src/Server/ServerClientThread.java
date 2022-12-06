package Server;

import GUI.GUI;
import GUI.Function_file;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.*;
import java.net.Socket;

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
            String clientMessage="", serverMessage="",command=" ",clientMessage1="",clientMessage2=" ", clientMessage3=" ";

            while(!clientMessage.equals(" ")){
                clientMessage=inStream.readUTF();
                clientMessage1=inStream.readUTF();
                clientMessage2=inStream.readUTF();
                clientMessage3=inStream.readUTF();


                System.out.println(clientMessage3+ " form server");

                System.out.println("Day la "+clientMessage1);
              //  System.out.println("Day la comand ben gui "+gui.itRun.getActionCommand());
              //  System.out.println("Day la file name ben gui "+clientMessage2);
               // System.out.println("From Client-" +serverClient.getInetAddress()+ " :"+clientMessage);
             //   squre = Integer.parseInt(clientMessage) * Integer.parseInt(clientMessage);
                System.out.println("java is se: "+ gui.ChkbJava.isSelected());
                System.out.println("python is se: "+ gui.ChkbPython.isSelected());
                if(clientMessage3.equals("JavaIsSelected")&&gui.itfomat.getActionCommand().equals(clientMessage1)){
                    System.out.println("java fomater");
                    serverMessage= FormaterJavaCode(clientMessage);
                } else if (clientMessage3.equals("PythonIsSelected")&&gui.itfomat.getActionCommand().equals(clientMessage1)) {
                    serverMessage= FormaterPythonCode(clientMessage);
                    System.out.println("Python fomater");

                } else if (clientMessage3.equals("CSharpIsSelected")&&gui.itfomat.getActionCommand().equals(clientMessage1)) {
                    System.out.println("Day la fomater C#");
                    serverMessage= FormaterCSharpCode(clientMessage);
                }
                else if (clientMessage3.equals("CPPsSelected")&&gui.itfomat.getActionCommand().equals(clientMessage1)) {
                    serverMessage= FormaterC_PlusCode(clientMessage);
                    System.out.println("C++ fomater");
                }
                else if (clientMessage3.equals("JavaIsSelected")&&gui.itRun.getActionCommand().equals(clientMessage1)) {
                  //  System.out.println("vbnm");
                    System.out.println("Java validate");
                    serverMessage=validatecodeJava(clientMessage,clientMessage2);
                }else if (clientMessage3.equals("PythonIsSelected")&&gui.itRun.getActionCommand().equals(clientMessage1)) {
                      System.out.println("Python validate");
                    serverMessage=validatecodePython(clientMessage);
                }else if (clientMessage3.equals("CPPsSelected")&&gui.itRun.getActionCommand().equals(clientMessage1)) {
                    System.out.println("C++ validate");
                    serverMessage=validatecodeCPP(clientMessage);
                }
                else if (clientMessage3.equals("CSharpIsSelected")&&gui.itRun.getActionCommand().equals(clientMessage1)) {
                    System.out.println("CSharp validate");
                    serverMessage=validatecodeCSharp(clientMessage);
                }
                else if (clientMessage3.equals("JavaIsSelected")&&gui.itRunandFomat.getActionCommand().equals(clientMessage1)) {
                    System.out.println("Java fomater and validate");
                    String serverMessage1=FormaterJavaCode(clientMessage);
                   serverMessage=validatecodeJava(serverMessage1,clientMessage2);
                    outStream.writeUTF(serverMessage1);
                    outStream.flush();

                }
                else if (clientMessage3.equals("PythonIsSelected")&&gui.itRunandFomat.getActionCommand().equals(clientMessage1)) {
                    System.out.println("Python fomater and validate");
                    String serverMessage1=FormaterPythonCode(clientMessage);
                    serverMessage=validatecodePython(serverMessage1);
                    outStream.writeUTF(serverMessage1);
                    outStream.flush();

                }
                else if (clientMessage3.equals("CPPsSelected")&&gui.itRunandFomat.getActionCommand().equals(clientMessage1)) {
                    System.out.println("C++ fomater and validate");
                    String serverMessage1=FormaterC_PlusCode(clientMessage);
                    serverMessage=validatecodeCPP(serverMessage1);
                    outStream.writeUTF(serverMessage1);
                    outStream.flush();

                }
                else if (clientMessage3.equals("CSharpIsSelected")&&gui.itRunandFomat.getActionCommand().equals(clientMessage1)) {
                    System.out.println("C# fomater and validate");
                    String serverMessage1=FormaterCSharpCode(clientMessage);
                    serverMessage=validatecodeCSharp(serverMessage1);
                    outStream.writeUTF(serverMessage1);
                    outStream.flush();

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

        Document doc = Jsoup.connect("https://www.10bestdesign.com/dirtymarkup/api/js").ignoreContentType(true).
                data("code",code)
                .data("indent","4")
                .data("line-length","100")
                .data("brace-style","expand")
                .data("spaces-in-parenthesis","true")
                .data("break-chained-methods","false")
                .data("commas-at-beginning","false")
                .data("keep-array-indentation","true")
                .data("preserve-empty-lines","false")
                .post();

        JSONObject jsonObject=new JSONObject(doc.text());
        String  tmp= (String) jsonObject.get("clean");
        code=" ";
        code=tmp;
        return code;
    }

    public String FormaterPythonCode(String code) throws IOException {
        Document doc = Jsoup.connect("https://tools.tutorialspoint.com/format_javascript.php").ignoreContentType(true).
                data("code",code).post();
        JSONObject jsonObject=new JSONObject((doc.body().addClass("ace_content")).text());
        String  tmp= (String) jsonObject.get("code");
        code=" ";
        code=tmp;
        //  System.out.println(code);
        return code;

    }

    public String FormaterC_PlusCode(String code) throws IOException {
        Document doc = Jsoup.connect("http://format.krzaq.cc/").ignoreContentType(true).
                data("code",code)
                .data("style","file")
                .post();


        String  tmp= doc.body().getElementById("data").text();
        code=" ";
        code=tmp;
        return code;
    }

    public String FormaterCSharpCode(String code) throws IOException {
         Document doc = Jsoup.connect("https://www.10bestdesign.com/dirtymarkup/api/js").ignoreContentType(true).
                data("code",code)
                .data("indent","4")
                .data("line-length","100")
                .data("brace-style","expand")
                .data("spaces-in-parenthesis","true")
                .data("break-chained-methods","false")
                .data("commas-at-beginning","false")
                .data("keep-array-indentation","true")
                .data("preserve-empty-lines","false")
                .post();

        JSONObject jsonObject=new JSONObject(doc.text());
        String  tmp= (String) jsonObject.get("clean");
        code=" ";
        code=tmp;
        return code;
    }

    public String validatecodeJava(String code,String filename) throws IOException {

        Function_file function_file=new Function_file(gui);
        Document doc = Jsoup.connect("https://try.w3schools.com/try_java.php?x=0.36483332864662255").ignoreContentType(true)
                .data("code", code)

                .post();

        String tmp=doc.body().select("pre").text();
        code=" ";
        code=tmp;
        return code;
    }

    public String validatecodePython(String code) throws IOException {
        Function_file function_file=new Function_file(gui);
        Document doc = Jsoup.connect("https://try.w3schools.com/try_python.php?x=0.6193688305771519").ignoreContentType(true)
                .data("code", code)

                .post();

        String tmp=doc.body().select("pre").text();
        code=" ";
        code=tmp;
        return code;

    }

    public String validatecodeCPP(String code) throws IOException {
        Function_file function_file=new Function_file(gui);
        Document doc = Jsoup.connect("https://try.w3schools.com/try_cpp.php?x=0.6199164972341491").ignoreContentType(true)
                .data("code", code)

                .post();

        String tmp=doc.body().select("pre").text();
        code=" ";
        code=tmp;
        return code;

    }

    public String validatecodeCSharp(String code) throws IOException {
        Function_file function_file=new Function_file(gui);
        Document doc = Jsoup.connect("https://try.w3schools.com/try_cs.php?x=0.19700449728298142").ignoreContentType(true)
                .data("code", code)

                .post();

        String tmp=doc.body().select("pre").text();
        code=" ";
        code=tmp;
        return code;

    }
    //
}
